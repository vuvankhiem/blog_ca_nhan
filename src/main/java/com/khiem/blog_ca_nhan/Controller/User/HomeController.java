package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private IHomeService homeService;


    @RequestMapping(value = {"/", "/trang-chu"})
    public String trangchu(Model model,
                           HttpSession session,
                           @RequestParam(name = "page", required = false, defaultValue = "1") int p

    ) {
        if (p < 1) {
            p = 1;
        }
        Pageable pageable = PageRequest.of(p - 1, 8);
        Page<Post> page = homeService.findByOrderByPostIdDesc(pageable);
        model.addAttribute("postList", page.getContent());
        model.addAttribute("postSile", homeService.findByPostSlide());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("page", p);
        session.setAttribute("postPopular", homeService.findTop3ByOrderByPostViewsDesc());
        session.setAttribute("postLastest", homeService.findTop4ByOrderByPostIdDesc());
        session.setAttribute("categoryDTO", homeService.findCategoryDtoList());
        session.setAttribute("category", homeService.findAllCategories());
        session.setAttribute("tagList",homeService.findAll());

        return "us/index";
    }
}


