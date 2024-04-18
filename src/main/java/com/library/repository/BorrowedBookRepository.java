package com.library.repository;

import com.library.model.BorrowedBook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

    List<BorrowedBook> findByUserId(int userId);
    // No need to declare findAll() here, it's already provided by JpaRepository

}
