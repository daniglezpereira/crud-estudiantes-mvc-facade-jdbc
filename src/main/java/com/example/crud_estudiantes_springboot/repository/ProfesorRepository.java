package com.example.crud_estudiantes_springboot.repository;

import com.example.crud_estudiantes_springboot.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

    @Query("SELECT p FROM Profesor p JOIN FETCH p.facultad")
    List<Profesor> findAllWithFacultad();

    Optional<Profesor> findById(int id);

    void deleteById(int id);
}