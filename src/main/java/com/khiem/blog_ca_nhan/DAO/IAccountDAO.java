package com.khiem.blog_ca_nhan.DAO;

import com.khiem.blog_ca_nhan.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDAO extends JpaRepository<Account,Integer> {
    @Query("select a from Account a where a.username=:us or a.email=:mail")
    public Account findByEmailOrUsername(@Param("us") String user,@Param("mail") String email);

    @Query("select a from Account  a where  a.email=?1 and a.auth_provider=?2")
    public Account findByEmailAndAuth_provider(String email,String auth_provider);
}
