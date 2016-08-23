package com.coller.clibrary.service;

import com.coller.clibrary.entity.Authority;
import com.coller.clibrary.entity.User;
import com.coller.clibrary.exception.UserExistsException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails addUser(User user, List<Authority> authorities) throws UserExistsException;
}
