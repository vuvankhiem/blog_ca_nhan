package com.khiem.blog_ca_nhan.DAO;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagDAO extends JpaRepository<Tag,Integer> {
    @Query("select t from Tag t join fetch t.postList where t.tagName=?1")
    public Tag findByTagName(String tagName);

    public List<Tag> findAll();
}
