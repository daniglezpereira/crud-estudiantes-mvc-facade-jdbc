package com.example.crud_estudiantes_springboot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profesores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Column(name = "primer_apellido")
    private String primerApellido;

    @NotBlank
    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @NotBlank
    @Column(name = "genero")
    private String genero;

    @NotNull
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @NotNull
    @Column(name = "salario")
    private Double salario;

    private String foto;

    // Muchos profesores pertenecen a UNA facultad
    @ManyToOne
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;
}