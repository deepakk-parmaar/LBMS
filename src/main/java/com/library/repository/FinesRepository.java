package com.library.repository;

import com.library.model.Fines;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FinesRepository extends JpaRepository<Fines, Long> {

}
