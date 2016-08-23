package com.coller.clibrary.entity;

import com.coller.clibrary.vo.EnableLevel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @NotNull
    private String username;

    @Size(max = 100)
    @NotNull
    private String password;

    @NotNull
    @Column(name = "enabled")
    private EnableLevel enableLevel;

    protected User(){}

    public User(String username, String password){
        this(username, password, EnableLevel.ENABLED);
    }

    public User(String username, String password, EnableLevel enableLevel){
        this.username = username;
        this.password = password;
        this.enableLevel = enableLevel;
    }
}
