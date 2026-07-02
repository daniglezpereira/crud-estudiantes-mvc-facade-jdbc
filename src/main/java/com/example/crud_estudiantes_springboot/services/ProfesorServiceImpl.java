package com.example.crud_estudiantes_springboot.services;

import org.springframework.stereotype.Service;

import com.example.crud_estudiantes_springboot.entities.Profesor;
import com.example.crud_estudiantes_springboot.repository.ProfesorRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAllWithFacultad();
    }

    @Override
    public Profesor getProfesorById(int id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public void deleteProfesor(int id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public void deleteProfesor(Profesor profesor) {
        profesorRepository.delete(profesor);
    }

    @Override
    public Profesor updateProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}