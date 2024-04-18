package com.library.service;

import java.util.List;

import com.library.model.Fines;

public interface FinesService {
    void save(Fines fines);
    List<Fines> getAllFines();
    void deleteFines(Long finesId);
}
