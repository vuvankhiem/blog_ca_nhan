package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Post;
import com.khiem.blog_ca_nhan.Entities.Tag;
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

import java.util.List;
import java.util.Set;

@Controller
public class PostFilterController {

    @Autowired
    private IPostFilterService postFilterService;

    @GetMapping("/tim-kiem")
    public String timKiem(Model model,
                             @RequestParam(name = "page", required = false, defaultValue = "1") int p,
                             @RequestParam(name = "s", required = false) String search) {
        if (p < 1) {
            p = 1;
        }
        Pageable pageable = PageRequest.of(p - 1, 8);
        Page<Post> page = postFilterService.findAllByPostTitleContaining(search, pageable);
        model.addAttribute("enable",0);
        model.addAttribute("txt","Tìm kiếm : "+search);
        model.addAttribute("pt", "&s=" + search);
        model.addAttribute("value", search);
        model.addAttribute("filter","tim-kiem");
        model.addAttribute("page", p);
        model.addAttribute("listP", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        return "us/post-filter";

    }

    @GetMapping("/danh-muc")
    public String danhMuc(Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int p,
                          @RequestParam(name = "c", required = false) String category) {
        if (p < 1) {
            p = 1;
        }
        Pageable pageable = PageRequest.of(p - 1, 8);
        Page<Post> page = postFilterService.findAllByCategory_CategoryName(category,pageable);
        model.addAttribute("enable",0);
        model.addAttribute("txt","Danh mục : "+category);
        model.addAttribute("pt", "&c=" + category);
        model.addAttribute("filter","danh-muc");
        model.addAttribute("page", p);
        model.addAttribute("listP", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        return "us/post-filter";

    }
    @GetMapping("/tag")
    public String tag(Model model,
                      @RequestParam(name = "page", required = false, defaultValue = "1") int p,
                      @RequestParam(name = "t",required = false) String tagName){

       List<Post> postList = postFilterService.findAllPostByTagName(tagName);

        int endpage = postList.size()/8;
        if(postList.size()%8!=0){
            ++endpage;
        }
        int start = 8*(p-1);
        int end = start+7;
        model.addAttribute("enable",1);
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("txt","Tag : "+tagName);
        model.addAttribute("pt", "&t=" + tagName);
        model.addAttribute("filter","tag");
        model.addAttribute("page", p);
        model.addAttribute("listP", postList);
        model.addAttribute("totalPage",endpage);
        return "us/post-filter";
    }
}
