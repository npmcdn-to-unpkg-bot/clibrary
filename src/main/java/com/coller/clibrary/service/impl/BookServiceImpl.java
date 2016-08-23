package com.coller.clibrary.service.impl;

import com.coller.clibrary.entity.Author;
import com.coller.clibrary.entity.Book;
import com.coller.clibrary.entity.Keyword;
import com.coller.clibrary.repository.AuthorRepository;
import com.coller.clibrary.repository.BookRepository;
import com.coller.clibrary.repository.KeywordRepository;
import com.coller.clibrary.repository.LogEntryRepository;
import com.coller.clibrary.service.BookService;
import com.coller.clibrary.ui.LibraryResponse;
import com.coller.clibrary.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    LogEntryRepository logEntryRepository;
    KeywordRepository keywordRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           LogEntryRepository logEntryRepository,
                           KeywordRepository keywordRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.logEntryRepository = logEntryRepository;
        this.keywordRepository = keywordRepository;
    }

    public LibraryResponse fetchAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        LibraryResponse bookResponse = new LibraryResponse();
        if(CollectionUtils.isEmpty(bookList)){
            bookResponse.setError(ErrorMessages.NO_BOOKS_FOUND);
            return bookResponse;
        }
        bookResponse.setData(bookList);
        return bookResponse;
    }

    public LibraryResponse addBook(Book book){
        LibraryResponse libraryResponse = new LibraryResponse();
        if(CollectionUtils.isEmpty(book.getAuthors())){
            libraryResponse.setError(ErrorMessages.AUTHOR_IS_REQUIRED);
            return libraryResponse;
        }
        List<Author> authorList = new ArrayList<Author>();
        for(Author author : book.getAuthors()){
            if(null != author.getId() && author.getId() > 0){
                authorList.add(authorRepository.findOne(author.getId()));
            } else if(null != authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
                authorList.add(authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName()));
            } else {
                authorList.add(new Author(author.getFirstName(), author.getMiddleName(), author.getLastName()));
            }
        }
        book.setAuthors(authorList);
        book = bookRepository.save(book);
        libraryResponse.setData(book);
        return libraryResponse;
    }

    @Override
    public LibraryResponse fetchBooksByKeywords(String[] keywords) {
        List<Book> books = new ArrayList<Book>();
        for(String keyword : keywords){
            List<Keyword> keywordList = keywordRepository.findKeywordByKeywordContainingIgnoreCase(keyword);
            for (Keyword keywordObject : keywordList){
                books.addAll(keywordObject.getBooks());
            }
        }

        List<Book> booksWithoutDuplicates = books.parallelStream().distinct().collect(Collectors.toList());

        if(CollectionUtils.isEmpty(booksWithoutDuplicates)){
            return new LibraryResponse(null, "No books found");
        }
        return new LibraryResponse(booksWithoutDuplicates, null);
    }
}
