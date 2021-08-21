package com.khiem.blog_ca_nhan.DAO;

import com.khiem.blog_ca_nhan.Entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IPostDAO extends JpaRepository<Post, Integer>{
    public Page<Post> findByOrderByPostIdDesc(Pageable pageable);

    @Query("select p from  Post p where p.postSlide=true ")
    public List<Post> findAllByPostSlide();

    public List<Post> findTop3ByOrderByPostViewsDesc();

    public List<Post> findTop4ByOrderByPostIdDesc();

    public long countPostByCategory_CategoryId(int categoryId);

    public Page<Post> findAllByCategory_CategoryName(String categoryName, Pageable pageable);

    public Page<Post> findAllByPostTitleContaining(String postTitle, Pageable pageable);

    @Query("select p from Post p join fetch p.tagList where p.postId=?1")
    public Post findByPostId(int postId);

}
