package com.khiem.blog_ca_nhan.DTO;

import com.khiem.blog_ca_nhan.Entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    Set<Post> postSet = new HashSet<>();
}
