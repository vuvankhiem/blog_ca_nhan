package com.khiem.blog_ca_nhan.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String postDate;
    private int postViews;
    private String postTitle;
    private String postContent;
    private String postImage;
    private boolean postSlide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_categoryID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_accountID")
    private Account account;

}
