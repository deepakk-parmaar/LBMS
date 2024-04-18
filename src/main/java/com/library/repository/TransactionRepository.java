package com.library.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.model.Transaction_log;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction_log, Long> {
}
