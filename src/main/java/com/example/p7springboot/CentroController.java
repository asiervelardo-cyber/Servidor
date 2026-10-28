package com.example.p7springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador MVC: prepara los datos y elige la plantilla. No traduce nada.
@Controller
public class CentroController {

    // Página de inicio (/).
    @GetMapping("/")
    public String inicio(Model model) {
        // El array solo guarda identificadores; los textos de cada actividad
        // están en messages*.properties (claves actividad.<id>.titulo, etc.)
        String[] actividades = {"taller", "concierto", "ruta"};
        model.addAttribute("actividades",actividades);
        // Nombre de la plantilla: templates/inicio.html
        return "inicio";
    }

    // Página de visita (/visita). Sus textos salen de los mensajes, sin datos del modelo.
    @GetMapping("/visita")
    public String visita() {
        return "visita";
    }
}
