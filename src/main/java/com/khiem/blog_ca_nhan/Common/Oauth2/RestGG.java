package com.khiem.blog_ca_nhan.Common.Oauth2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestGG {
    @Autowired
    private Environment env;

    public String getToken(String code) throws IOException , ClientProtocolException {
        String response = Request.Post(env.getProperty("google.link.get.token"))
                .bodyForm(Form.form()
                        .add("client_id",env.getProperty("google.app.id"))
                        .add("client_secret", env.getProperty("google.app.secret"))
                        .add("redirect_uri",env.getProperty("google.redirect.uri"))
                        .add("code",code)
                        .add("grant_type", "authorization_code")
                        .build()
                )
                .execute()
                .returnContent()
                .asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response).get("access_token");
        return jsonNode.textValue();
    }

    public GooglePojo getUserInfo(final String accessToken) throws IOException {
        String link = env.getProperty("google.link.get.user_info")+accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        GooglePojo googlePojo = objectMapper.readValue(response,GooglePojo.class);
        return googlePojo;
    }

    public UserDetails userDetails(GooglePojo googlePojo){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorityList.add(authority);
        UserDetails userDetails = new User(googlePojo.getEmail(), "",true,true,true,true,authorityList);
        return userDetails;
    }
}
