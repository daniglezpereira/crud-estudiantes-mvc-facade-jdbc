package com.example.crud_estudiantes_springboot.repository;

import com.example.crud_estudiantes_springboot.entities.Facultad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultadRepository extends JpaRepository<Facultad, Integer> {

    Optional<Facultad> findById(int id);
}