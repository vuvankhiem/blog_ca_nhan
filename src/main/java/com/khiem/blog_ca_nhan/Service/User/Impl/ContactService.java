package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.khiem.blog_ca_nhan.DAO.IContactDAO;
import com.khiem.blog_ca_nhan.Entities.Contact;
import com.khiem.blog_ca_nhan.Service.User.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {

    @Autowired
    private IContactDAO contactDAO;

    @Override
    public void saveContact(Contact contact) {
        contactDAO.save(contact);
    }
}
