package com.usa.ciclo3.reto4.controller.rest_controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetInfoUser {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/avatar")
    public Map<String,Object> getAvatar(@AuthenticationPrincipal OAuth2User principal){

        return Collections.singletonMap("avatar_url", principal.getAttribute("avatar_url"));
    }
}
