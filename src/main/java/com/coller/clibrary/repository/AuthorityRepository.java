package com.coller.clibrary.repository;

import com.coller.clibrary.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findAuthorityByUsernameAndAuthority(String username, String authority);
    List<Authority> findAuthorityByUsername(String username);
}
