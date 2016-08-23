package com.coller.clibrary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    @JsonBackReference
    private Book book;

    protected LogEntry(){}

    public LogEntry(Book book, String log){
        this.book = book;
        this.log = log;
    }
}
