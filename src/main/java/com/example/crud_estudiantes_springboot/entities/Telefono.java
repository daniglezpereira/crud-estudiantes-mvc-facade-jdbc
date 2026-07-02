package com.example.crud_estudiantes_springboot.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefonos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "estudiantes_id")
    private Estudiante estudiante;
}