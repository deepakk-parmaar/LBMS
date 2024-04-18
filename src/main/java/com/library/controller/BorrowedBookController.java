package com.library.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.model.BorrowedBook;
import com.library.service.BorrowedBookService;

@Controller
public class BorrowedBookController {

    private final BorrowedBookService borrowedBookService;

    public BorrowedBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    // Handler method for returning a book
    @PostMapping("/libraryuser/returnBook")
    public String returnBook(@RequestParam("borrowedBookId") Long borrowedBookId) {
        // Retrieve the borrowed book from the service
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBookById(borrowedBookId);

        // Update the 'returned' attribute to true
        borrowedBook.setReturned(true);

        // Save the updated borrowed book
        borrowedBookService.save(borrowedBook);

        // Redirect to the page displaying borrowed books
        return "redirect:/libraryuser/returnBook";
    }
}
