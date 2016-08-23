package com.coller.clibrary.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String callNumber;
    private String isbn;
    @NotNull
    private String title;
    private String description;

    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinTable(
            name="BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    )
    private List<Author> authors;

    @ManyToMany(targetEntity = Keyword.class, cascade = CascadeType.ALL)
    @JoinTable(
            name="BOOK_KEYWORD",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "KEYWORD_ID", referencedColumnName = "ID")
    )
    private List<Keyword> keywords;

    @OneToMany(mappedBy = "book")
    private List<LogEntry> logEntries;

    protected Book(){}

    public Book(String callNumber, String isbn, String title, String description){
        this.callNumber = callNumber;
        this.isbn = isbn;
        this.title = title;
        this.description = description;
    }
}
