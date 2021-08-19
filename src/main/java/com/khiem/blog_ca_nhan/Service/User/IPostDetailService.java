package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Post;

public interface IPostDetailService {
    public Post findByPostID(int postID);
    public void updatePostView(Post post);
}
