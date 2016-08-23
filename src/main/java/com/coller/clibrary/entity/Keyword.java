package com.coller.clibrary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Keyword {

    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    @JsonBackReference
    private List<Book> books;

    protected Keyword(){}

    public Keyword(String keyword){
        this.keyword = keyword;
    }
}
