package com.example.u4ejercicio_p3.Controlador;

import java.util.List;

import com.example.u4ejercicio_p3.Dto.NuevaCancionFormulario;
import com.example.u4ejercicio_p3.Modelo.Cancion;
import com.example.u4ejercicio_p3.Servicio.CancionServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Recibe las peticiones del navegador. Solo habla con el servicio, nunca con el repositorio.
@Controller
public class CancionController {
    // Deben coincidir con los valores que admite el @Pattern del formulario
    private static final List<String> TIPOS = List.of("Sencillo", "Album", "EP");
    private static final List<String> GENERO = List.of("Rock", "Pop", "Rap", "Trap", "Reggueton", "Country", "Hip Hop", "Electronica", "Jazz", "R&B", "Clasica", "Metal", "Otro");

    private final CancionServicio cancionServicio;

    // Inyección por constructor: Spring nos pasa el servicio ya creado
    public CancionController(CancionServicio cancionServicio) {
        this.cancionServicio = cancionServicio;
    }

    @GetMapping("/")
    public String index(Model model) {
        prepararPagina(model, new NuevaCancionFormulario());
        return "index";
    }

    @PostMapping("/cancion")
    public String registrarCancion(@Valid @ModelAttribute("nuevaCancion") NuevaCancionFormulario formulario, BindingResult resultado, RedirectAttributes redirectAttributes, Model model) {
        // Si hay errores se vuelve a mostrar la página con lo que escribió el usuario
        if (resultado.hasErrors()) {
            prepararPagina(model, formulario);
            return "index";
        }

        cancionServicio.registrar(formulario.getTitulo(), formulario.getTipo(), formulario.getGenero(), formulario.getDuracion(), formulario.getValoracion(), formulario.getAutor(), formulario.getFecha(), formulario.isRecomendada());

        // Redirigir tras guardar evita que al recargar se envíe el formulario otra vez
        redirectAttributes.addFlashAttribute("mensajeExito", "Canción añadida a la lista.");
        return "redirect:/";
    }

    // Reúne lo que necesita la vista; se usa tanto al abrir la página como cuando hay errores
    private void prepararPagina(Model model, NuevaCancionFormulario formulario) {
        List<Cancion> canciones = cancionServicio.obtenerTodos();
        model.addAttribute("canciones", canciones);
        model.addAttribute("nuevaCancion", formulario);
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("generos", GENERO);
    }
}
