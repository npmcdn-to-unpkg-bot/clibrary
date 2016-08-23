package com.coller.clibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/admin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<User> testAdmin(@AuthenticationPrincipal User user){
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping("/anotherOne")
    public ResponseEntity<String> anotherUser(){
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
}
