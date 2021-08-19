package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khiem.blog_ca_nhan.DAO.ICategoryDAO;
import com.khiem.blog_ca_nhan.DAO.IPostDAO;
import com.khiem.blog_ca_nhan.DTO.CategoryDTO;
import com.khiem.blog_ca_nhan.Entities.Category;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IHomeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService implements IHomeService {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Autowired
    private IPostDAO postDAO;

    @Override
    public List<Category> findAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public Page<Post> findByOrderByPostIdDesc(Pageable pageable) {
        return postDAO.findByOrderByPostIdDesc(pageable);
    }

    @Override
    public List<Post> findByPostSlide() {
        return  postDAO.findAllByPostSlide();
    }

    @Override
    public List<Post> findTop3ByOrderByPostViewsDesc() {
        return postDAO.findTop3ByOrderByPostViewsDesc();
    }

    @Override
    public List<Post> findTop4ByOrderByPostIdDesc() {
        return postDAO.findTop4ByOrderByPostIdDesc();
    }




    @Override
    public List<CategoryDTO> findCategoryDtoList() {
        List<Category> list = categoryDAO.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category: list
             ) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setPostQuantity(postDAO.countPostByCategory_CategoryId(category.getCategoryId()));
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;

    }




}
