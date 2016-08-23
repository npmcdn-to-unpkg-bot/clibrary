package com.coller.clibrary.service.impl;

import com.coller.clibrary.entity.Authority;
import com.coller.clibrary.entity.User;
import com.coller.clibrary.exception.UserExistsException;
import com.coller.clibrary.repository.UserRepository;
import com.coller.clibrary.service.AuthorityService;
import com.coller.clibrary.service.UserService;
import com.coller.clibrary.vo.AuthorityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserDetailsService userDetailsService;

    public UserDetails addUser(User user, List<Authority> authorities) throws UserExistsException {
        if (null != userRepository.findUserByUsername(user.getUsername())){
            throw new UserExistsException("User " + user.getUsername() + " already exists");
        }
        userRepository.save(new User(user.getUsername(), encoder.encode(user.getPassword())));
        authorityService.addAuthority(new Authority(user.getUsername(), AuthorityLevel.ROLE_USER));
        return userDetailsService.loadUserByUsername(user.getUsername());
    }
}
