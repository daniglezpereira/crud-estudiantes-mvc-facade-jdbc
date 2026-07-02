package com.example.crud_estudiantes_springboot.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.crud_estudiantes_springboot.model.Genero;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "profesores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "facultad")
@Builder
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre no puede estar vacio")
    @NotBlank(message = "El nombre no puede contener solamente espacios en blanco")
    @Size(min = 2, max = 30, message = "El nombre tiene que estar entre 2 y 30 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s)?)+$",
            message = "La primera letra en mayusculas y solo letras de la A a la Z")
    private String nombre;

    @NotNull(message = "El primer apellido no puede estar vacio")
    @NotBlank(message = "El primer apellido no puede contener solamente espacios en blanco")
    @Size(min = 2, max = 30, message = "El primer apellido tiene que estar entre 2 y 30 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s)?)+$",
            message = "La primera letra en mayusculas y solo letras de la A a la Z")
    private String primerApellido;

    @Size(max = 45, message = "El segundo apellido no puede superar los 45 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s)?)*$",
            message = "La primera letra en mayusculas y solo letras de la A a la Z")
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "La fecha de alta tiene que ser igual o anterior a la fecha actual")
    private LocalDate fechaAlta;

    @NotNull(message = "El salario no puede estar vacio")
    private BigDecimal salario;

    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Facultad facultad;
}