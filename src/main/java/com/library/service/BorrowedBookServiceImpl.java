package com.library.service;

import com.library.model.BorrowedBook;
import com.library.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository; // Remove Autowired annotation

    @Autowired // Add Autowired annotation to constructor
    public BorrowedBookServiceImpl(BorrowedBookRepository borrowedBookRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public void save(BorrowedBook borrowedBook) {
        borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }

    @Override
    public BorrowedBook getBorrowedBookById(Long borrowedBookId) {
        return borrowedBookRepository.findById(borrowedBookId).orElse(null);
    }
    // Implement other methods as needed

    @Override
    public List<BorrowedBook> getBooksbyUserId(int userId) {
        return borrowedBookRepository.findByUserId(userId);
    }
}
