package com.khiem.blog_ca_nhan.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/gioi-thieu")
    public String gioiThieu(){
        return "us/about";
    }
}
