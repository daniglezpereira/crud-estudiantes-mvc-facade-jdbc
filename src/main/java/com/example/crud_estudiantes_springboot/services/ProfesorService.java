package com.example.crud_estudiantes_springboot.services;

import java.util.List;
import com.example.crud_estudiantes_springboot.entities.Profesor;

public interface ProfesorService {

    List<Profesor> getAllProfesores();

    void saveProfesor(Profesor profesor);

    Profesor findById(int id);

    void deleteById(int id);
}