package com.library.model;

import java.time.LocalDate;

public class BoughtBook {
    private int user_id;
    private int book_id;
    private LocalDate date;
    private String paymentMethod;

    public BoughtBook() {
        // Default constructor
    }

    public BoughtBook(int user_id, int book_id, LocalDate date, String paymentMethod) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getBookId() {
        return book_id;
    }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
