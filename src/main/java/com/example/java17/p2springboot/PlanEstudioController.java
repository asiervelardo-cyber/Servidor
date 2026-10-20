package com.example.java17.p2springboot;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanEstudioController {

    @GetMapping("/")
    public String plan(@RequestParam(defaultValue = "estudiante") String nombre, Model model) {
        List<String> asignaturas = List.of("DIW", "Cliente", "Servidor");
        List<Integer> duraciones = List.of(25, 40, 30);

        model.addAttribute("nombre", nombre);
        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("duraciones", duraciones);
        return "plan";
    }
}
