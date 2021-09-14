package com.khiem.blog_ca_nhan.Common.Oauth2;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khiem.blog_ca_nhan.Entities.Account;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestFB {
    @Autowired
    private Environment env;

    public String getToken(final String code) throws IOException {
        String link = String.format(
                env.getProperty("facebook.link.get.token"),
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret"),
                env.getProperty("facebook.redirect.uri"),
                code);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response).get("access_token");
        return jsonNode.textValue();
    }

    public User getUserInfo(final String accessToken){
        FacebookClient client = new DefaultFacebookClient(accessToken,
                env.getProperty("facebook.app.secret"), Version.LATEST);
        return client.fetchObject("me",User.class,Parameter.with("fields","id,name,picture,email"));
    }


    public UserDetails userDetails(Account account){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+account.getRole());
        authorityList.add(authority);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getUsername(), "", true,true,true,true,authorityList);
        return userDetails;
    }


}
