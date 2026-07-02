package com.example.crud_estudiantes_springboot.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "correos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Correo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "estudiantes_id")
    private Estudiante estudiante;
}