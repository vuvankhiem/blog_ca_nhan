package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IPostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDetailServiceImpl implements IPostDetailService {
    @Autowired
    private IPostDAO postDAO;


    @Override
    public Post findByPostID(int postID) {
        return postDAO.findByPostId(postID);
    }

    @Override
    public void updatePostView(Post post) {
        post.setPostViews(post.getPostViews()+1);
        postDAO.save(post);
    }
}
