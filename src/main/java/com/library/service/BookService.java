package com.library.service;

import java.util.List;

import com.library.model.Book;

public interface BookService {
    Book saveBook(Book book);

    void deleteBookById(Long id);

    List<Book> getAllBooks();

    boolean existBook(String title);

    Book getBookById(Long id);
}
