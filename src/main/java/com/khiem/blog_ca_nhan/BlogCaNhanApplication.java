package com.khiem.blog_ca_nhan;

import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.khiem.blog_ca_nhan"})
public class BlogCaNhanApplication{

    @Autowired
    IPostDAO postDAO;
    public static void main(String[] args) {
        SpringApplication.run(BlogCaNhanApplication.class, args);
    }


}
