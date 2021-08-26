package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Common.Oauth2.GooglePojo;
import com.khiem.blog_ca_nhan.Common.Oauth2.RestFB;
import com.khiem.blog_ca_nhan.Common.Oauth2.RestGG;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Service.User.IHandleAuthenticationService;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Controller
public class HandleAuthenticationController {
    @Autowired
    private RestFB restFB;

    @Autowired
    private RestGG restGG;

    @Autowired
    private IHandleAuthenticationService authenticationService;


    @GetMapping("/dang-nhap")
    public String dangNhap(@RequestParam(required = false) String err, Model model){
        if(err!=null){
            model.addAttribute("err","Incorrect username or password !");
        }
        return "us/sign-in";
    }

    //Login with facebook account
    @RequestMapping("/dang-nhap-facebook")
    public String dangNhap_FB(HttpServletRequest request,@RequestParam(name = "code",required = false) String code) throws IOException {

        if(code==null||code.isEmpty()){
            return "redirect:dang-nhap";
        }
        String accessToken = restFB.getToken(code);
        User user = restFB.getUserInfo(accessToken);
        String email = user.getEmail();
        String auth_provider = "FACEBOOK";
        Account account = authenticationService.findByEmailAndAuth_Provider(email,auth_provider);
        if(account==null){
            String picture = "https://graph.facebook.com/v3.0/"+user.getId()+"/picture?type=large";
            authenticationService.saveAccount(email,picture,auth_provider,user.getName());
        }
        UserDetails userDetails = restFB.userDetails(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:login-success";

    }

    //login with google account
    @RequestMapping("/dang-nhap-google")
    public String dangNhapGoogle(@RequestParam(required = false) String code) throws IOException {

        if(code==null||code.isEmpty()){
            return "redirect:dang-nhap";
        }
        String accessToken = restGG.getToken(code);
        System.out.println(accessToken);
        GooglePojo googlePojo = restGG.getUserInfo(accessToken);
        String email = googlePojo.getEmail();
        String auth_provider = "GOOGLE";
        Account account = authenticationService.findByEmailAndAuth_Provider(email,auth_provider);
        if(account==null){
            authenticationService.saveAccount(email,googlePojo.getPicture(),auth_provider,googlePojo.getName());
        }
        UserDetails userDetails = restGG.userDetails(googlePojo);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:login-success";
    }


    @GetMapping("/dang-ki")
    public String dangKi(){

        return "us/sign-up";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Principal principal){
        System.out.println(principal.getName());
        return "redirect:trang-chu";
    }
}
