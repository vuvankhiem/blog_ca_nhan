package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Contact;
import com.khiem.blog_ca_nhan.Service.User.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/lien-lac")
    public String lienlac(@ModelAttribute("contact") Contact contact){

        return "us/contact";
    }

    @PostMapping("/lien-lac")
    public String themlienlac(@Valid @ModelAttribute("contact") Contact contact,
                              BindingResult bindingResult,
                              Model model){
        if(bindingResult.hasErrors()){
            return "us/contact";
        }

        contactService.saveContact(contact);
        return "redirect:trang-chu";
    }

}
