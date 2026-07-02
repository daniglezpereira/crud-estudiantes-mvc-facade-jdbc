package com.example.crud_estudiantes_springboot.services;

import com.example.crud_estudiantes_springboot.entities.Facultad;
import com.example.crud_estudiantes_springboot.repository.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService {

    private final FacultadRepository repository;

    @Override
    public List<Facultad> getAllFacultades() {
        return repository.findAll();
    }

    @Override
    public Facultad findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada: " + id));
    }
}