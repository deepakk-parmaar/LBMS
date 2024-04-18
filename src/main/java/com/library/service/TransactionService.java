package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.repository.TransactionRepository;
import com.library.model.Transaction_log;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction_log> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void saveTransaction(Transaction_log transaction) {
        transactionRepository.save(transaction);
    }

    // You can add more methods here as needed
}
