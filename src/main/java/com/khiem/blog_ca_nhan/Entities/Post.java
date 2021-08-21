package com.khiem.blog_ca_nhan.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_categoryID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_accountID")
    private Account account;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
    joinColumns = {@JoinColumn(name = "post_postID")},
    inverseJoinColumns = {@JoinColumn(name = "tag_tagID")})
    List<Tag> tagList = new ArrayList<>();

}
