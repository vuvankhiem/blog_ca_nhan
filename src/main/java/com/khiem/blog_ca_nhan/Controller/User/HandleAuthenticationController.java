package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Common.Oauth2.GooglePojo;
import com.khiem.blog_ca_nhan.Common.Oauth2.RestFB;
import com.khiem.blog_ca_nhan.Common.Oauth2.RestGG;
import com.khiem.blog_ca_nhan.DTO.AccountDTO;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Service.User.IHandleAuthenticationService;
import com.restfb.types.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class HandleAuthenticationController {
    static String st_password;
    static String st_username;
    static String st_code = "";
    static String st_fullname;
    static String st_email;

    @Autowired
    private RestFB restFB;

    @Autowired
    private RestGG restGG;

    @Autowired
    private IHandleAuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    //sign in page
    @GetMapping("/dang-nhap")
    public String dangNhap(@RequestParam(required = false) String err,
                           Model model) {
        if (err != null) {
            model.addAttribute("err", "Incorrect username or password !");
        }
        return "us/sign-in";
    }

    //Login with facebook account
    @RequestMapping("/dang-nhap-facebook")
    public String dangNhap_FB(HttpServletRequest request, @RequestParam(name = "code", required = false) String code) throws IOException {

        if (code == null || code.isEmpty()) {
            return "redirect:dang-nhap";
        }
        String accessToken = restFB.getToken(code);
        User user = restFB.getUserInfo(accessToken);
        String email = user.getEmail();
        String auth_provider = "FACEBOOK";
        Account acc = authenticationService.findByEmailAndAuth_Provider(email, auth_provider);
        if (acc == null) {
            Account account = new Account();
            String picture = "https://graph.facebook.com/v3.0/" + user.getId() + "/picture?type=large";
            account.setUsername(user.getName());
            account.setAuth_provider(auth_provider);
            account.setEmail(email);
            account.setFullName(user.getName());
            account.setAvatar(picture);
            authenticationService.saveAccount(account);
        }
        UserDetails userDetails = restFB.userDetails(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:login-success";

    }

    //login with google account
    @RequestMapping("/dang-nhap-google")
    public String dangNhapGoogle(@RequestParam(required = false) String code) throws IOException {

        if (code == null || code.isEmpty()) {
            return "redirect:dang-nhap";
        }
        String accessToken = restGG.getToken(code);
        System.out.println(accessToken);
        GooglePojo googlePojo = restGG.getUserInfo(accessToken);
        String email = googlePojo.getEmail();
        String auth_provider = "GOOGLE";
        Account acc = authenticationService.findByEmailAndAuth_Provider(email, auth_provider);
        if (acc == null) {
            Account account = new Account();
            account.setEmail(email);
            account.setAvatar(googlePojo.getPicture());
            account.setAuth_provider(auth_provider);
            account.setFullName(googlePojo.getName());
            account.setUsername(googlePojo.getName());
            authenticationService.saveAccount(account);
        }
        UserDetails userDetails = restGG.userDetails(googlePojo);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:login-success";
    }


    //sign up
    @GetMapping("/dang-ki")
    public String dangKi_page(@ModelAttribute(name = "account") AccountDTO account,
                              @RequestParam(required = false, defaultValue = "null") String code) {
        Account acc = new Account();
        if (code.equals(st_code)) {
            acc.setEmail(st_email);
            acc.setUsername(st_username);
            acc.setAvatar("/us/images/avatar_common.png");
            acc.setFullName(st_fullname);
            acc.setPassword(st_password);
            authenticationService.saveAccount(acc);
            return "us/sign-in";
        }
        return "us/sign-up";
    }

    @PostMapping("/dang-ki")
    public String dangKi(@Valid @ModelAttribute(name = "account") AccountDTO account,
                         BindingResult bindingResult,
                         Model model,
                         HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "us/sign-up";
        } else {
            Account acc = authenticationService.findByEmailOrUsername(account.getUsername(), account.getEmail());
            if (acc == null) {
                if (account.getPassword().equals(account.getRe_password())) {
                    st_code = RandomStringUtils.randomAlphanumeric(6);
                    st_email = account.getEmail();
                    st_fullname = account.getFullName();
                    st_password = passwordEncoder.encode(account.getPassword());
                    st_username = account.getUsername();
                    String link = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/dang-ki?code=" + st_code;
                    String confirm_link = "Click link : " + link + " để xác thực ";
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(st_email);
                    message.setSubject("Xác thực tài khoản");
                    message.setText(confirm_link);
                    this.mailSender.send(message);
                    model.addAttribute("notify", "Đăng kí thành công. Quý khách vui lòng truy cập vào Gmail và xác thực tài khoản của bạn !");
                    return "us/sign-in";
                } else {
                    model.addAttribute("err", "Mật khẩu không khớp");
                }
            } else {
                model.addAttribute("err", "Tài khoản của bạn đã tồn tại");
            }

        }

        return "us/sign-up";
    }


    //login successful
    @GetMapping("/login-success")
    public String loginSuccess(Principal principal, HttpSession session) {
        String mail = principal.getName();
        Account account = authenticationService.findByEmailOrUsername(mail,mail);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAvatar(account.getAvatar());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setFullName(account.getFullName());
        session.setAttribute("acc", accountDTO);
        if (account.getRole().equals("ADMIN")) {
            return "redirect:admin";
        }
        return "redirect:trang-chu";
    }
}
