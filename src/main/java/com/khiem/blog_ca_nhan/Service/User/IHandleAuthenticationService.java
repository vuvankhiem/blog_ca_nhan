package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Account;

public interface IHandleAuthenticationService {
    public Account findByEmailAndAuth_Provider(String email,String auth_provider);
    public void saveAccount(Account account);
    public Account findByEmailOrUsername(String username,String email);
}
