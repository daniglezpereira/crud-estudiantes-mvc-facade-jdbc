package com.example.crud_estudiantes_springboot.services;

import java.util.List;
import com.example.crud_estudiantes_springboot.entities.Estudiante;

public interface EstudianteService {

    List<Estudiante> getAllEstudiantes();

    void saveEstudiante(Estudiante estudiante);

    Estudiante findById(Long id);

    void deleteById(Long id);

    Estudiante buscarPorId(Long id);
    
}