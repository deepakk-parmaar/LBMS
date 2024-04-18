package com.library.service;

import com.library.model.BorrowedBook;
import java.util.List;

public interface BorrowedBookService {
    void save(BorrowedBook borrowedBook);

    List<BorrowedBook> getAllBorrowedBooks();

    BorrowedBook getBorrowedBookById(Long borrowedBookId);

    List<BorrowedBook> getBooksbyUserId(int userId);

}
