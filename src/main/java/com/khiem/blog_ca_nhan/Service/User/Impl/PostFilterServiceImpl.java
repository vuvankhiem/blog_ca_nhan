package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.DAO.ITagDAO;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Entities.Tag;
import com.khiem.blog_ca_nhan.Service.User.IPostFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostFilterServiceImpl implements IPostFilterService {
    @Autowired
    private IPostDAO postDAO;
    @Autowired
    private ITagDAO tagDAO;

    @Override
    public Page<Post> findAllByCategory_CategoryName(String categoryName, Pageable pageable) {
        return postDAO.findAllByCategory_CategoryName(categoryName,pageable);
    }

    @Override
    public List<Post> findAllPostByTagName(String tagName) {
        List<Post> list = tagDAO.findByTagName(tagName).getPostList();
        return list;
    }

    @Override
    public Page<Post> findAllByPostTitleContaining(String postTitle, Pageable pageable) {
        return postDAO.findAllByPostTitleContaining(postTitle,pageable);
    }
}
