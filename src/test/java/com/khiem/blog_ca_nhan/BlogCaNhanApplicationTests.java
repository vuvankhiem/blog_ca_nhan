package com.khiem.blog_ca_nhan;

import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.Entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogCaNhanApplicationTests {

    @Autowired
    IPostDAO postDAO;
    @Test
    void contextLoads() {

    }

}
