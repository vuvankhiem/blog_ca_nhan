package com.khiem.blog_ca_nhan;

import com.khiem.blog_ca_nhan.DAO.IAccountDAO;
import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.DAO.ITagDAO;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Entities.Tag;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.khiem.blog_ca_nhan"})
public class BlogCaNhanApplication implements CommandLineRunner{

    @Autowired
    IAccountDAO accountDAO;

    public static void main(String[] args) {
        SpringApplication.run(BlogCaNhanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
