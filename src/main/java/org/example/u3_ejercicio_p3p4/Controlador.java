package org.example.u3_ejercicio_p3p4;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;

@org.springframework.stereotype.Controller
public class Controlador {

    // Catálogo en memoria. Cada fila: {código, nombre, descripción}.
    private static final String[][] CATALOGO = {
            {"PORTATIL", "Ordenador portátil", "Ordenador portátil; incluye un cargador y una funda."},
            {"MICRO", "Kit de microcontrolador", "Kit de microcontrolador; incluye una placa y un cable USB."},
            {"CAMARA", "Cámara de documentación", "Cámara de documentación; incluye un soporte de mesa."}
    };

    @GetMapping("/")
    public String solicitud(Model model) {
        model.addAttribute("materiales", CATALOGO);
        return "catalogo";
    }

    @GetMapping("/materiales/{codigo}")
    public String ficha(@PathVariable("codigo") String codigo,
                        @RequestParam(name = "turno", defaultValue = "general") String turno,
                        Model model) {
        String[] material = buscarMaterial(codigo);
        if (material == null) {
            // 404 real: Spring Boot elegirá templates/error/404.html.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado");
        }
        model.addAttribute("material", material);
        model.addAttribute("turno", turno);
        return "material";
    }

    @GetMapping("/solicitud/nueva")
    public String nuevaSolicitud(Model model) {
        model.addAttribute("solicitud", new SolicitudForm());
        model.addAttribute("materiales", CATALOGO);
        return "solicitud";
    }

    // BindingResult debe ir justo después del DTO validado.
    @PostMapping("/solicitudes")
    public String enviarSolicitud(@Valid @ModelAttribute("solicitud") SolicitudForm solicitud,
                                  BindingResult resultado,
                                  Model model) {

        // El código debe existir en el catálogo (no basta con confiar en el <select>).
        String[] material = null;
        if (!resultado.hasFieldErrors("material")) {
            material = buscarMaterial(solicitud.getMaterial());
            if (material == null) {
                resultado.rejectValue("material", "material.desconocido",
                        "Selecciona uno de los materiales del catálogo.");
            }
        }

        // Regla entre dos campos: solo se comprueba si ambas fechas se enlazaron bien.
        if (!resultado.hasFieldErrors("fechaRecogida") && !resultado.hasFieldErrors("fechaDevolucion")
                && solicitud.getFechaRecogida().isAfter(solicitud.getFechaDevolucion())) {
            resultado.rejectValue("fechaDevolucion", "fechas.orden",
                    "La fecha de devolución no puede ser anterior a la de recogida.");
        }

        if (resultado.hasErrors()) {
            // Se vuelve a renderizar el formulario: hay que reponer las opciones del selector.
            model.addAttribute("materiales", CATALOGO);
            return "solicitud";
        }

        long[] calculo = calcularDias(solicitud);
        model.addAttribute("materialNombre", material[1]);
        model.addAttribute("dias", calculo[0]);
        model.addAttribute("diasUnidad", calculo[1]);
        // "solicitud" ya está en el modelo gracias a @ModelAttribute; no se guarda en ningún sitio.
        return "resultado";
    }

    /** Busca un material por código; devuelve la fila del array o null si no existe. */
    private String[] buscarMaterial(String codigo) {
        for (String[] fila : CATALOGO) {
            if (fila[0].equals(codigo)) {
                return fila;
            }
        }
        return null;
    }

    /**
     * Devuelve {días del préstamo, días-unidad}. Los días cuentan recogida y devolución
     * (por eso el +1). Se usa long para evitar desbordamientos al multiplicar.
     */
    private long[] calcularDias(SolicitudForm solicitud) {
        long dias = ChronoUnit.DAYS.between(solicitud.getFechaRecogida(), solicitud.getFechaDevolucion()) + 1;
        long diasUnidad = dias * solicitud.getUnidades();
        return new long[]{dias, diasUnidad};
    }
}
