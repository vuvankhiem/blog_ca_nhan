package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Entities.Comment;
import com.khiem.blog_ca_nhan.Entities.Post;

public interface ICommentService {
     public void addComment(Comment comment);
     public Account findByUsername(String username);
     public Post findById(int id);

}
