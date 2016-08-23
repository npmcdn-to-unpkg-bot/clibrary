package com.coller.clibrary.service.impl;

import com.coller.clibrary.entity.Authority;
import com.coller.clibrary.entity.User;
import com.coller.clibrary.repository.AuthorityRepository;
import com.coller.clibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        List<Authority> authorityList = authorityRepository.findAuthorityByUsername(username);
        Iterator<Authority> iterator = authorityList.iterator();
        StringBuilder authorityString = new StringBuilder();
        while(iterator.hasNext()){
            authorityString.append(iterator.next().getAuthorityLevel().name());
            if(iterator.hasNext()){
                authorityString.append(",");
            }
        }
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString.toString());
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);
        userDetails.eraseCredentials();
        return userDetails;
    }
}
