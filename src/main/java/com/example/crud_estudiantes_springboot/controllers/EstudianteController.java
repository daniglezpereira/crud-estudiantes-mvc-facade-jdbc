
package com.example.crud_estudiantes_springboot.controllers;

import com.example.crud_estudiantes_springboot.entities.Correo;
import com.example.crud_estudiantes_springboot.entities.Estudiante;
import com.example.crud_estudiantes_springboot.services.EstudianteService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "listadoEstudiantes";
    }

    @GetMapping("/alta")
    public String alta(Model model) {

        Estudiante estudiante = new Estudiante();

        // IMPORTANTE: inicializar la lista
        estudiante.setCorreos(new java.util.HashSet<>());

        // Evita error de binding en el formulario
        estudiante.getCorreos().add(new Correo());

        model.addAttribute("estudiante", estudiante);

        return "formularioEstudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Estudiante estudiante = estudianteService.findById(id);

        // evitar null
        if (estudiante.getCorreos() == null) {
            estudiante.setCorreos(new java.util.HashSet<>());
        }

        if (estudiante.getCorreos().isEmpty()) {
            estudiante.getCorreos().add(new Correo());
        }

        model.addAttribute("estudiante", estudiante);

        return "formularioEstudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        estudianteService.deleteById(id);
        return "redirect:/estudiantes/listar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante,
            @RequestParam("email") String email,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        // 📧 correo
        Correo correo = new Correo();
        correo.setEmail(email);
        correo.setEstudiante(estudiante);

        estudiante.getCorreos().clear();
        estudiante.getCorreos().add(correo);

        // 📷 FOTO
        if (file != null && !file.isEmpty()) {

            try {
                // 1. ruta del proyecto
                Path carpetaUploads = Paths.get(System.getProperty("user.dir"), "uploads");

                // 2. crear carpeta si no existe
                Files.createDirectories(carpetaUploads);

                // 3. nombre del archivo
                String nombreArchivo = file.getOriginalFilename();

                // 4. ruta completa del archivo
                Path rutaCompleta = carpetaUploads.resolve(nombreArchivo);

                // 5. guardar archivo
                file.transferTo(rutaCompleta.toFile());

                // 6. guardar SOLO el nombre en BD
                estudiante.setFoto(nombreArchivo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 💾 guardar todo
        estudianteService.saveEstudiante(estudiante);

        return "redirect:/estudiantes/listar";
    }

    // Ver detalles de cada estudiante con todos los datos y si hay imagenes.

    @GetMapping("/ver/{id}") // 👈 CAMBIA ESTO: Solo "/ver/{id}", porque el "/estudiantes" ya se suma
                             // automáticamente por el @RequestMapping de arriba
    public String verDetalles(@PathVariable("id") Long id, Model model) {

        // 💡 NOTA: En tu método 'editar' usaste 'findById'.
        // Asegúrate de usar el mismo método que tengas en tu Service (findById o
        // buscarPorId)
        Estudiante estudiante = estudianteService.findById(id);

        // Lo mandas al modelo
        model.addAttribute("estudiante", estudiante);

        // Te redirige a la vista
        return "detallesEstudiante";
    }

    // como ver la imagen guardada en cada estudiante
    @GetMapping("/fotos/{id}")
    @ResponseBody
    public ResponseEntity<Resource> verFoto(@PathVariable("id") Long id) {

        // 1. Buscar al estudiante para obtener el nombre de su foto
        Estudiante estudiante = estudianteService.findById(id);

        if (estudiante == null || estudiante.getFoto() == null || estudiante.getFoto().isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no tiene foto o no existe
        }

        try {
            // 2. Localizar el archivo en la carpeta "uploads" usando el nombre guardado
            Path rutaArchivo = Paths.get(System.getProperty("user.dir"), "uploads").resolve(estudiante.getFoto());
            Resource recurso = new UrlResource(rutaArchivo.toUri());

            if (recurso.exists() || recurso.isReadable()) {
                // 3. Devolver los bytes de la imagen con las cabeceras HTTP correctas
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Cambia a png si usas mayoritariamente png
                        .body(recurso);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}