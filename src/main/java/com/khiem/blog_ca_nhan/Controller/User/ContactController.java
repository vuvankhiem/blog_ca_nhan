package com.khiem.blog_ca_nhan.Controller.User;

import com.khiem.blog_ca_nhan.Entities.Contact;
import com.khiem.blog_ca_nhan.Service.User.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/lien-lac")
    public String lienlac(@ModelAttribute("contact") Contact contact){

        return "us/contact";
    }

    @PostMapping("/lien-lac")
    public String themlienlac(@ModelAttribute("contact") Contact contact,Model model){
        model.addAttribute("response","resp");
        contactService.saveContact(contact);
        return "us/contact";
    }

}
