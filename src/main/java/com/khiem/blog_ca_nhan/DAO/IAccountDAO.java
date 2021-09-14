package com.khiem.blog_ca_nhan.DAO;

import com.khiem.blog_ca_nhan.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDAO extends JpaRepository<Account,Integer> {

    public Account findTopByUsernameOrEmail(String username,String email);

    @Query("select a from Account  a where  a.email=?1 and a.auth_provider=?2")
    public Account findByEmailAndAuth_provider(String email,String auth_provider);

    public Account findByEmail(String email);

    public Account findByUsername(String username);
}
