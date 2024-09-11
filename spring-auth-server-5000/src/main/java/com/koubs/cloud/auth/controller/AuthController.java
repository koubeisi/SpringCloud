package com.koubs.cloud.auth.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KouBeisi
 * @since 2021/6/17
 */
@RestController
public class AuthController {

    @GetMapping("/userinfo")
    public Map<String,Object> userInfo(OAuth2Authentication authentication){
        Map<String,Object> userInfo = new HashMap<>(8);
        userInfo.put("user",authentication.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(authentication.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
}
