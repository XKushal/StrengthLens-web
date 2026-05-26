package org.strengthlens.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2/v1")
public class AuthController {

    @GetMapping("/public")
    public String publicApi(){
        return "This is public api";
    }

    @GetMapping("/private")
    public String privateApi(){
        return "Authenticated";
    }
}
