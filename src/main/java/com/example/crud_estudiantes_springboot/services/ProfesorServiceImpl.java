package com.example.crud_estudiantes_springboot.services;

import com.example.crud_estudiantes_springboot.entities.Profesor;
import com.example.crud_estudiantes_springboot.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository repository;

    @Override
    public List<Profesor> getAllProfesores() {
        return repository.findAll();
    }

    @Override
    public void saveProfesor(Profesor profesor) {
        repository.save(profesor);
    }

    @Override
    public Profesor findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado: " + id));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}