package com.khiem.blog_ca_nhan.Service.User;

import com.khiem.blog_ca_nhan.Entities.Account;

public interface IHandleAuthenticationService {
    public Account findByEmailAndAuth_Provider(String email,String auth_provider);
    public void saveAccount(Account account);
    public Account findByUsernameOrEmail(String username,String email);
    public Account findByEmail(String email);
    public Account findByUsername(String username);
}
