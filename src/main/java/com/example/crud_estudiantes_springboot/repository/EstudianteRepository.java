package com.example.crud_estudiantes_springboot.repository;

import com.example.crud_estudiantes_springboot.entities.Estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

@Query("SELECT e FROM Estudiante e LEFT JOIN FETCH e.correos")
List<Estudiante> findAllWithCorreos();
}