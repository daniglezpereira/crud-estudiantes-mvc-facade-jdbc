package com.example.crud_estudiantes_springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//Controlador de la página main 
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/estudiantes/listar";
    }
}