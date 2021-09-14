package com.khiem.blog_ca_nhan.DTO;

import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
     private int commentId;
     private String commentContent;
     private int reply;
     private String commentDate;
     private String fullName;
     private String avatar;
     private int postId;
}
