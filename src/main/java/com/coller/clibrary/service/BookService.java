package com.coller.clibrary.service;


import com.coller.clibrary.entity.Book;
import com.coller.clibrary.ui.LibraryResponse;

import java.util.List;

public interface BookService {

    LibraryResponse fetchAllBooks();
    LibraryResponse addBook(Book addBookRequest);
    LibraryResponse fetchBooksByKeywords(String[] keywords);
}
