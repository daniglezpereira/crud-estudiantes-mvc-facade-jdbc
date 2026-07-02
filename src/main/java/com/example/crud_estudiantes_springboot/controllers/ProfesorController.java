package com.example.crud_estudiantes_springboot.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.crud_estudiantes_springboot.entities.Profesor;
import com.example.crud_estudiantes_springboot.services.FacultadService;
import com.example.crud_estudiantes_springboot.services.ProfesorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private static final Logger LOG = Logger.getLogger("ProfesorController");

    private final ProfesorService profesorService;
    private final FacultadService facultadService;

    @GetMapping("/listar")
    public String listarProfesores(Model model) {

        model.addAttribute("profesores", profesorService.getAllProfesores());

        return "listadoProfesores";
    }

    // Creación de profesor
    @GetMapping("/alta")
    public String mostrarFormularioAlta(Model model, @ModelAttribute Profesor profesor) {

        model.addAttribute("facultades", facultadService.getAllFacultades());

        return "altaModificacionProfesores";
    }

    // Guardar profesor
    @PostMapping("/persistir")
    public String procesarFormularioAltaModificacion(
            @Valid @ModelAttribute Profesor profesor,
            BindingResult result,
            Model model,
            @RequestParam(name = "file", required = false) MultipartFile file) {

        if (result.hasErrors()) {

            model.addAttribute("facultades", facultadService.getAllFacultades());

            return "altaModificacionProfesores";
        }

        // Guardar foto
        if (file != null && !file.isEmpty()) {

            Path rutaRelativa = Paths.get("src/main/resources/static/imagenes");
            String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + file.getOriginalFilename());

            try {
                byte[] bytesFotoRecibida = file.getBytes();
                Files.write(rutaCompleta, bytesFotoRecibida);
                profesor.setFoto(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        LOG.info("Objeto profesor recibido");
        LOG.info(profesor.toString());

        profesorService.saveProfesor(profesor);

        return "redirect:/profesores/listar";
    }

    // Detalles de un profesor
    @GetMapping("/details/{id}")
    public String mostrarDetalles(Model model,
            @PathVariable(name = "id", required = true) int profesorId) {

        model.addAttribute("profesor", profesorService.getProfesorById(profesorId));

        return "detallesProfesor";
    }

    // Modificación de un profesor
    @GetMapping("/update/{id}")
    public String updateProfesor(Model model,
            @PathVariable(name = "id", required = true) int idProfesor) {

        Profesor profesor = profesorService.getProfesorById(idProfesor);

        model.addAttribute("profesor", profesor);
        model.addAttribute("facultades", facultadService.getAllFacultades());

        return "altaModificacionProfesores";
    }

    // Eliminar un profesor
    @GetMapping("/delete/{idProfesor}")
    public String deleteProfesor(Model model, @PathVariable int idProfesor) {

        Profesor profesorEliminar = profesorService.getProfesorById(idProfesor);

        if (profesorEliminar.getFoto() != null) {

            Path rutaRelativa = Paths.get("src/main/resources/static/imagenes/"
                    + profesorEliminar.getFoto());

            if (Files.exists(rutaRelativa)) {
                try {
                    Files.delete(rutaRelativa);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        profesorService.deleteProfesor(profesorEliminar);

        return "redirect:/profesores/listar";
    }
}