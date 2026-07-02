package com.example.crud_estudiantes_springboot.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facultades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "profesores")
@Builder
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    // Muchas facultades con varios profesores
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "facultad")
    private List<Profesor> profesores;
}