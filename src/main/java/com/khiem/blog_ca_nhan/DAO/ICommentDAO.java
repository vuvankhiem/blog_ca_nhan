package com.khiem.blog_ca_nhan.DAO;

import com.khiem.blog_ca_nhan.Entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentDAO extends JpaRepository<Comment,Integer> {

     @Query("select c from Comment c where c.reply > 0 and c.post.postId=?1")
     public List<Comment> findReplys(int postId);

     @Query("select c from Comment c where c.reply = 0 and c.post.postId=?1 order by c.commentId desc ")
     public List<Comment> findComments(int postId);

     @Query("select c from Comment c where c.post.postId=?1")
     public List<Comment> findAllByPostId(int postId);

     public List<Comment> findTop3ByOrderByCommentIdDesc();

     public Page<Comment> findByReplyOrderByCommentIdDesc(int commentId, Pageable pageable);

}
