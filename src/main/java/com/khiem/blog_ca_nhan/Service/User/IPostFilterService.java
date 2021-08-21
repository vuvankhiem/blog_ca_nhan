package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IPostFilterService {
    public Page<Post> findAllByPostTitleContaining(String postTitle, Pageable pageable);
    public Page<Post> findAllByCategory_CategoryName(String categoryName,Pageable pageable);
    public List<Post> findAllPostByTagName(String tagName);
}
