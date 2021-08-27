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
    public void saveAccount(Account account) {

        accountDAO.save(account);
    }

    @Override
    public Account findByEmailOrUsername(String username,String email) {
        return accountDAO.findByEmailOrUsername(username,email);
    }
}
