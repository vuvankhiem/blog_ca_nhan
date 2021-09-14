package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Comment;
import com.khiem.blog_ca_nhan.Entities.Post;

import java.util.List;

public interface IPostDetailService {
     public Post findByPostID(int postID);

     public void updatePostView(Post post);

     public List<Comment> findCommentsByPostId(int postId);

     public List<Comment> findReplysByPostId(int postId);
}
