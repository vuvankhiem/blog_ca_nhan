package com.khiem.blog_ca_nhan.Service;

import com.khiem.blog_ca_nhan.DAO.IAccountDAO;
import com.khiem.blog_ca_nhan.Entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountDAO accountDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDAO.findTopByUsernameOrEmail(username,username);
        if(account!=null){
            List<GrantedAuthority> authorityList = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+account.getRole());
            authorityList.add(authority);
            return  new org.springframework.security.core.userdetails.User(username, account.getPassword(), true, true, true, account.isActive(), authorityList);
        }else{
            new UsernameNotFoundException("Not found!");
        }
        return null;

    }
}
