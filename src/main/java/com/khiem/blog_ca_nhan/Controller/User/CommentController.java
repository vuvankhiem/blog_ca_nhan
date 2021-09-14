package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Common.Base.Base;
import com.khiem.blog_ca_nhan.DAO.ICommentDAO;
import com.khiem.blog_ca_nhan.DTO.CommentDTO;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Entities.Comment;
import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/binh-luan")
public class CommentController extends Base{
     @Autowired
     private ICommentService commentService;
     @Autowired
     private ICommentDAO commentDAO;

     @GetMapping("/them")
     public CommentDTO addComment(@RequestParam int p_i,
                                  @RequestParam int c_i,
                                  @RequestParam String a_i,
                                  @RequestParam String commentInput){
          Account account = commentService.findByUsername(a_i);
          Post post = commentService.findById(p_i);
          Comment c = new Comment();
          c.setCommentContent(commentInput.trim());
          c.setCommentDate(super.getDate());
          c.setPost(post);
          c.setAccount(account);
          c.setReply(c_i);
          commentService.addComment(c);

          CommentDTO commentDTO = new CommentDTO();
          commentDTO.setCommentId(c.getCommentId());
          commentDTO.setCommentContent(c.getCommentContent());
          commentDTO.setAvatar(c.getAccount().getAvatar());
          commentDTO.setFullName(c.getAccount().getFullName());
          commentDTO.setCommentDate(c.getCommentDate());
          commentDTO.setPostId(c.getPost().getPostId());
          commentDTO.setReply(c.getReply());

          return commentDTO;

     }




}
