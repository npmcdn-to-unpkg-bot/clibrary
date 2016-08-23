package com.coller.clibrary.repository;

import com.coller.clibrary.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
