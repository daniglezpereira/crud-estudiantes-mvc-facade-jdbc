package com.example.crud_estudiantes_springboot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {

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

    // 📩 CORREOS (1 estudiante → muchos correos)
    @OneToMany(mappedBy = "estudiante",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<Correo> correos = new HashSet<>();

    // 📞 TELÉFONOS (si los tienes también)
    @OneToMany(mappedBy = "estudiante",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<Telefono> telefonos = new HashSet<>();

    private String foto;
}