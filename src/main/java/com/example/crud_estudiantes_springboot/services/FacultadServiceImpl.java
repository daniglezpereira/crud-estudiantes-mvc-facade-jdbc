package com.example.crud_estudiantes_springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_estudiantes_springboot.entities.Facultad;
import com.example.crud_estudiantes_springboot.repository.FacultadRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacultadServiceImpl implements FacultadService {

    private final FacultadRepository facultadRepository;

    @Override
    public Facultad saveFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public List<Facultad> getAllFacultades() {
        return facultadRepository.findAll();
    }

    @Override
    public Facultad getFacultadById(int id) {
        return facultadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada con id: " + id));
    }
}