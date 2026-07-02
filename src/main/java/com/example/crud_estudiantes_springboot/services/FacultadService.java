package com.example.crud_estudiantes_springboot.services;

import java.util.List;
import com.example.crud_estudiantes_springboot.entities.Facultad;

public interface FacultadService {

    List<Facultad> getAllFacultades();

    Facultad findById(int id);
}