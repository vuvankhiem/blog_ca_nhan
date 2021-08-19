package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.DTO.CategoryDTO;
import com.khiem.blog_ca_nhan.Entities.Category;
import com.khiem.blog_ca_nhan.Entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IHomeService {
    public List<Category> findAllCategories();

    public Page<Post> findByOrderByPostIdDesc(Pageable pageable);

    public List<Post> findByPostSlide();

    public List<Post> findTop3ByOrderByPostViewsDesc();

    public List<Post> findTop4ByOrderByPostIdDesc();



    public List<CategoryDTO> findCategoryDtoList();


}
