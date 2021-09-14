package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.khiem.blog_ca_nhan.DAO.IAccountDAO;
import com.khiem.blog_ca_nhan.DAO.ICommentDAO;
import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Entities.Comment;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {
     @Autowired
     private ICommentDAO commentDAO;
     @Autowired
     private IPostDAO postDAO;
     @Autowired
     private IAccountDAO accountDAO;

     @Override
     public void addComment(Comment comment) {
          commentDAO.save(comment);
     }

     @Override
     public Account findByUsername(String username) {
          return accountDAO.findByUsername(username);
     }


     @Override
     public Post findById(int id) {
          return postDAO.findByPostId(id);
     }
}
