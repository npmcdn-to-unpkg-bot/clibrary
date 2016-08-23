package com.coller.clibrary.service.impl;

import com.coller.clibrary.entity.Authority;
import com.coller.clibrary.repository.AuthorityRepository;
import com.coller.clibrary.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
