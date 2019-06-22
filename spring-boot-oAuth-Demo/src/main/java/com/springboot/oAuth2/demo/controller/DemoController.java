package com.springboot.oAuth2.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @GetMapping("/user")
    public Principal getUser(Principal principal){
        return principal;
    }
}
