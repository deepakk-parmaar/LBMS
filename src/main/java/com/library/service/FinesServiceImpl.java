package com.library.service;

import com.library.model.Fines;
import com.library.repository.FinesRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FinesServiceImpl implements FinesService {
    private final FinesRepository finesRepository;

    public FinesServiceImpl(FinesRepository finesRepository) {
        this.finesRepository = finesRepository;
    }

    @Override
    public void save(Fines fines) {
        finesRepository.save(fines);
    }

    @Override
    public List<Fines> getAllFines() {
        return finesRepository.findAll();
    }

    @Override
    public void deleteFines(Long finesId) {
        finesRepository.deleteById(finesId);
    }

}
