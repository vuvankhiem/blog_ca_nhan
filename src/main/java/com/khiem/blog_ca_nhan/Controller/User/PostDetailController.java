package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IPostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostDetailController {

    @Autowired
    private IPostDetailService postDetailService;

    @RequestMapping("/bai-viet/{id}")
    public String postDetail(Model model,
                             @PathVariable int id){
        Post post = postDetailService.findByPostID(id);
        model.addAttribute("postDetail",post);
        postDetailService.updatePostView(post);
        return "us/blog-single";
    }
}
