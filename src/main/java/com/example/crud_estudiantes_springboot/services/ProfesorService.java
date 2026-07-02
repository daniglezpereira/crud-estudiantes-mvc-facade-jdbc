package com.example.crud_estudiantes_springboot.services;

import java.util.List;

import com.example.crud_estudiantes_springboot.entities.Profesor;

public interface ProfesorService {

  
    List<Profesor> getAllProfesores();

    
    Profesor getProfesorById(int id);

   
    Profesor saveProfesor(Profesor profesor);

   
    void deleteProfesor(int id);


    void deleteProfesor(Profesor profesor);

    Profesor updateProfesor(Profesor profesor);
}