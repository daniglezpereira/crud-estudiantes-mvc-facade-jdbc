package com.example.crud_estudiantes_springboot.services;

import com.example.crud_estudiantes_springboot.entities.Estudiante;
import com.example.crud_estudiantes_springboot.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;  

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository repository;

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return repository.findAllWithCorreos();
    }

    @Override
    public void saveEstudiante(Estudiante estudiante) {
        repository.save(estudiante);
    }

    @Override
    public Estudiante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    //Implementar método para ver todos los estudiantes con sus datos y si hay imagenes.
    @Override
    public Estudiante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + id));
    }

}