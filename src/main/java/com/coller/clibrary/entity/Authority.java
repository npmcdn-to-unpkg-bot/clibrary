package com.coller.clibrary.entity;

import com.coller.clibrary.vo.AuthorityLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AUTHORITIES")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    @NotNull
    private String username;

    @Size(max = 45)
    @NotNull
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private String authority;

    @Transient
    private AuthorityLevel authorityLevel;

    protected Authority(){}

    public Authority(String username, AuthorityLevel authorityLevel){
        this.username = username;
        this.authorityLevel = authorityLevel;
        this.authority = authorityLevel.name();
    }

    private void setAuthorityLevel(AuthorityLevel authorityLevel){
        this.authorityLevel = authorityLevel;
        this.authority = authorityLevel.name();
    }
}
