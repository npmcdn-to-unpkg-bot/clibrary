package com.coller.clibrary.controller;

import com.coller.clibrary.entity.Authority;
import com.coller.clibrary.entity.User;
import com.coller.clibrary.exception.ClibraryException;
import com.coller.clibrary.exception.UserExistsException;
import com.coller.clibrary.service.UserService;
import com.coller.clibrary.vo.AuthorityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) throws ClibraryException{
        UserDetails userDetails = userService.addUser(user, Arrays.asList(new Authority(user.getUsername(), AuthorityLevel.ROLE_USER)));
        return new ResponseEntity<Object>(userDetails, HttpStatus.OK);
    }
}
