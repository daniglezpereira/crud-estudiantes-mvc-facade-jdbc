package com.example.crud_estudiantes_springboot;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.crud_estudiantes_springboot.entities.Facultad;
import com.example.crud_estudiantes_springboot.entities.Profesor;
import com.example.crud_estudiantes_springboot.model.Genero;
import com.example.crud_estudiantes_springboot.services.FacultadService;
import com.example.crud_estudiantes_springboot.services.ProfesorService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesSpringbootApplication implements CommandLineRunner {

	private final FacultadService facultadService;
	private final ProfesorService profesorService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Crear registros de ejemplo en la base de datos, lo
		// cual nos permite comprobar que la aplicación funciona correctamente,
		// concretamente la capa de servicio y la capa de persistencia.

		// Solo cargar Profesores de ejemplo si todavia no existe ninguno,
		// para no duplicar los datos cada vez que arranca la aplicacion
		if (profesorService.getAllProfesores().isEmpty()) {

			// Recuperamos las facultades que ya existen en la base de datos
			List<Facultad> facultades = facultadService.getAllFacultades();

			Facultad facultadInformatica = facultades.stream()
					.filter(f -> f.getNombre().equalsIgnoreCase("Informatica"))
					.findFirst().orElse(facultades.get(0));

			Facultad facultadMedicina = facultades.stream()
					.filter(f -> f.getNombre().equalsIgnoreCase("Medicina"))
					.findFirst().orElse(facultades.get(1));

			Facultad facultadDerecho = facultades.stream()
					.filter(f -> f.getNombre().equalsIgnoreCase("Derecho"))
					.findFirst().orElse(facultades.get(2));

			Facultad facultadEconomia = facultades.stream()
					.filter(f -> f.getNombre().equalsIgnoreCase("Economia"))
					.findFirst().orElse(facultades.get(3));

			Facultad facultadIngenieria = facultades.stream()
					.filter(f -> f.getNombre().equalsIgnoreCase("Ingenieria"))
					.findFirst().orElse(facultades.get(4));

			/* Profesores de la Facultad de Informatica */

			Profesor profesor1 = Profesor.builder()
					.nombre("Antonio")
					.primerApellido("Garcia")
					.segundoApellido("Lopez")
					.genero(Genero.HOMBRE)
					.fechaAlta(LocalDate.of(2020, 9, 1))
					.salario(new BigDecimal("2200.00"))
					.facultad(facultadInformatica)
					.build();

			Profesor profesor2 = Profesor.builder()
					.nombre("Laura")
					.primerApellido("Sanchez")
					.segundoApellido("Ortega")
					.genero(Genero.MUJER)
					.fechaAlta(LocalDate.of(2019, 3, 12))
					.salario(new BigDecimal("2450.00"))
					.facultad(facultadInformatica)
					.build();

			/* Profesores de la Facultad de Medicina */

			Profesor profesor3 = Profesor.builder()
					.nombre("Carlos")
					.primerApellido("Martinez")
					.segundoApellido("Diaz")
					.genero(Genero.HOMBRE)
					.fechaAlta(LocalDate.of(2018, 11, 20))
					.salario(new BigDecimal("2800.00"))
					.facultad(facultadMedicina)
					.build();

			Profesor profesor4 = Profesor.builder()
					.nombre("Elena")
					.primerApellido("Torres")
					.segundoApellido("Molina")
					.genero(Genero.MUJER)
					.fechaAlta(LocalDate.of(2021, 2, 15))
					.salario(new BigDecimal("2950.00"))
					.facultad(facultadMedicina)
					.build();

			/* Profesores de la Facultad de Derecho */

			Profesor profesor5 = Profesor.builder()
					.nombre("Javier")
					.primerApellido("Romero")
					.segundoApellido("Navarro")
					.genero(Genero.HOMBRE)
					.fechaAlta(LocalDate.of(2017, 6, 5))
					.salario(new BigDecimal("2350.00"))
					.facultad(facultadDerecho)
					.build();

			Profesor profesor6 = Profesor.builder()
					.nombre("Cristina")
					.primerApellido("Alonso")
					.segundoApellido("Vega")
					.genero(Genero.MUJER)
					.fechaAlta(LocalDate.of(2022, 9, 1))
					.salario(new BigDecimal("2500.00"))
					.facultad(facultadDerecho)
					.build();

			/* Profesores de la Facultad de Economia */

			Profesor profesor7 = Profesor.builder()
					.nombre("David")
					.primerApellido("Jimenez")
					.segundoApellido("Castro")
					.genero(Genero.HOMBRE)
					.fechaAlta(LocalDate.of(2016, 10, 10))
					.salario(new BigDecimal("2600.00"))
					.facultad(facultadEconomia)
					.build();

			Profesor profesor8 = Profesor.builder()
					.nombre("Patricia")
					.primerApellido("Gil")
					.segundoApellido("Serrano")
					.genero(Genero.MUJER)
					.fechaAlta(LocalDate.of(2020, 4, 22))
					.salario(new BigDecimal("2750.00"))
					.facultad(facultadEconomia)
					.build();

			/* Profesores de la Facultad de Ingenieria */

			Profesor profesor9 = Profesor.builder()
					.nombre("Miguel")
					.primerApellido("Ortiz")
					.segundoApellido("Ramos")
					.genero(Genero.HOMBRE)
					.fechaAlta(LocalDate.of(2019, 1, 30))
					.salario(new BigDecimal("2900.00"))
					.facultad(facultadIngenieria)
					.build();

			Profesor profesor10 = Profesor.builder()
					.nombre("Beatriz")
					.primerApellido("Herrera")
					.segundoApellido("Iglesias")
					.genero(Genero.MUJER)
					.fechaAlta(LocalDate.of(2021, 8, 17))
					.salario(new BigDecimal("2650.00"))
					.facultad(facultadIngenieria)
					.build();

			profesorService.saveProfesor(profesor1);
			profesorService.saveProfesor(profesor2);
			profesorService.saveProfesor(profesor3);
			profesorService.saveProfesor(profesor4);
			profesorService.saveProfesor(profesor5);
			profesorService.saveProfesor(profesor6);
			profesorService.saveProfesor(profesor7);
			profesorService.saveProfesor(profesor8);
			profesorService.saveProfesor(profesor9);
			profesorService.saveProfesor(profesor10);
		}
	}
}