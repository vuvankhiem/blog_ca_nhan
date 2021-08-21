package com.khiem.blog_ca_nhan.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandleAuthenticationController {
    @GetMapping("/dang-nhap")
    public String dangNhap(){
        return "us/sign-in";
    }
}
