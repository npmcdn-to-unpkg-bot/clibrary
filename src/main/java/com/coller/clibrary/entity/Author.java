package com.coller.clibrary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;

    protected Author(){}

    public Author(String firstName, String middleName, String lastName){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}
