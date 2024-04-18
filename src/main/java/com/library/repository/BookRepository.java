package com.library.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByCategory(String category);

    List<Book> findByTitleContaining(String keyword);
    
}
