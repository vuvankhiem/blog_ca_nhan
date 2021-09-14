package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IPostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostDetailController {

    @Autowired
    private IPostDetailService postDetailService;

    @RequestMapping("/bai-viet/{id}")
    public String postDetail(Model model,
                             @PathVariable int id,
                             @RequestParam(required = false,defaultValue = "0") int up_view){
        Post post = postDetailService.findByPostID(id);
        model.addAttribute("postDetail",post);
        model.addAttribute("list_cmt",postDetailService.findCommentsByPostId(id));
        model.addAttribute("list_reply",postDetailService.findReplysByPostId(id));
        if(up_view==1){
            postDetailService.updatePostView(post);
        }
        return "us/blog-single";
    }
}
