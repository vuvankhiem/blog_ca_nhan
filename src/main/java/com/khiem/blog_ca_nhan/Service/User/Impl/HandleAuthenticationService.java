package com.khiem.blog_ca_nhan.Service.User.Impl;

import com.khiem.blog_ca_nhan.DAO.IAccountDAO;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.khiem.blog_ca_nhan.Service.User.IHandleAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandleAuthenticationService implements IHandleAuthenticationService {
    @Autowired
    private IAccountDAO accountDAO;
    @Override
    public Account findByEmailAndAuth_Provider(String email,String auth_provider) {
        return accountDAO.findByEmailAndAuth_provider(email,auth_provider);
    }

    @Override
    public void saveAccount(String email, String picture, String auth_provider, String name) {
        Account account = new Account();
        account.setActive(true);
        account.setFullName(name);
        account.setRole("USER");
        account.setEmail(email);
        account.setAvatar(picture);
        account.setAuth_provider(auth_provider);
        account.setUsername(name);
        accountDAO.save(account);
    }
}
