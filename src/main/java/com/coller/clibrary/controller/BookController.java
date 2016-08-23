package com.coller.clibrary.controller;

import com.coller.clibrary.entity.Book;
import com.coller.clibrary.service.BookService;
import com.coller.clibrary.ui.LibraryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("/list")
    public ResponseEntity<LibraryResponse> fetchAllBooks(){
        return new ResponseEntity<LibraryResponse>(bookService.fetchAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<LibraryResponse> addBook(@RequestBody Book book){
        return new ResponseEntity<LibraryResponse>(bookService.addBook(book), HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{keyword}")
    public ResponseEntity<LibraryResponse> getBookByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<LibraryResponse>(bookService.fetchBooksByKeywords(new String[]{keyword}), HttpStatus.OK);
    }

    @RequestMapping(value = "/search/[{keywords}]")
    public ResponseEntity<LibraryResponse> getBooksByKeywords(@PathVariable String[] keywords){
        return new ResponseEntity<LibraryResponse>(bookService.fetchBooksByKeywords(keywords), HttpStatus.OK);
    }
}
