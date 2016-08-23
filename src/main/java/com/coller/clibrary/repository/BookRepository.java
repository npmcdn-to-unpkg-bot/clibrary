package com.coller.clibrary.repository;

import com.coller.clibrary.entity.Book;
import com.coller.clibrary.entity.Keyword;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
}
