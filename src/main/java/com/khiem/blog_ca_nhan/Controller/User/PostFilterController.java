package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Service.User.IPostFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostFilterController {

    @Autowired
    private IPostFilterService postFilterService;

    @RequestMapping("/tim-kiem")
    public String timKiem(Model model,
                             @RequestParam(name = "page", required = false, defaultValue = "1") int p,
                             @RequestParam(name = "s", required = false) String search) {
        if (p < 1) {
            p = 1;
        }
        Pageable pageable = PageRequest.of(p - 1, 8);
        Page<Post> page = postFilterService.findAllByPostTitleContaining(search, pageable);
        model.addAttribute("txt","Tìm kiếm : "+search);
        model.addAttribute("pt", "&s=" + search);
        model.addAttribute("value", search);
        model.addAttribute("filter","tim-kiem");
        model.addAttribute("page", p);
        model.addAttribute("listP", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        return "us/post-filter";

    }

    @RequestMapping("/danh-muc")
    public String danhMuc(Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int p,
                          @RequestParam(name = "c", required = false) String category) {
        if (p < 1) {
            p = 1;
        }
        Pageable pageable = PageRequest.of(p - 1, 8);
        Page<Post> page = postFilterService.findAllByCategory_CategoryName(category,pageable);
        model.addAttribute("txt","Danh mục : "+category);
        model.addAttribute("pt", "&c=" + category);
        model.addAttribute("filter","danh-muc");
        model.addAttribute("page", p);
        model.addAttribute("listP", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        return "us/post-filter";

    }
}
