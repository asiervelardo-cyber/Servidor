package org.example.u3_ejercicio_p3p4;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SolicitudController {

    private static final Pattern CORREO_SIMPLE =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    @GetMapping("/")
    public String solicitud(Model model) {
        model.addAttribute("materiales", new SolicitudForm());
        return "catalogo";
    }

    @GetMapping("/solicitud/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("solicitud", new SolicitudForm());
        return "solicitud";
    }

    @GetMapping("/solicitud-sin-dto/nueva")
    public String mostrarFormularioSinDto(Model model) {
        anadirValoresSimples(model, "", "", "",0,"","","",false);
        model.addAttribute("errores", Map.of());
        return "solicitud-sin-dto";
    }

    @PostMapping("/enviar")
    public String recibirFormulario(
            @Valid @ModelAttribute("solicitud") SolicitudForm solicitud,
            BindingResult resultadoBinding,
            Model model) {

        if (resultadoBinding.hasErrors()) {
            return "formulario";
        }

        // El mismo objeto de esta petición se añade implícitamente al ModelAttribute
        // y queda disponible para la vista resultado; no se guarda para otra petición.
        return "resultado";
    }

    @PostMapping("/enviar-sin-dto")
    public String recibirFormularioSinDto(
            @RequestParam(name = "nombre", defaultValue = "") String nombre,
            @RequestParam(name = "correo", defaultValue = "") String correo,
            @RequestParam(name = "material", defaultValue = "") String material,
            @RequestParam(name = "unidades", defaultValue = "") Integer unidades,
            @RequestParam(name = "fechaR", defaultValue = "") String fechaR,
            @RequestParam(name = "fechaD", defaultValue = "") String fechaD,
            @RequestParam(name = "motivo", defaultValue = "") String motivo,
            @RequestParam(name = "normas", defaultValue = "") boolean normas,
            Model model) {
        String nombreNormalizado = nombre.trim();
        String correoNormalizado = correo.trim();
        String materialNormalizado = material.trim();
        Integer unidadesNormalizado = unidades;
        String fechaRNormalizado = fechaR.trim();
        String fechaDNormalizado = fechaD.trim();
        String motivoNormalizado = motivo.trim();
        Boolean normasNormalizado = normas;
        Map<String, String> errores = new LinkedHashMap<>();

        if (nombreNormalizado.isEmpty()) {
            errores.put("nombre", "Escribe tu nombre.");
        } else if (nombreNormalizado.length() > 60) {
            errores.put("nombre", "El nombre no puede superar los 60 caracteres.");
        }

        if (!CORREO_SIMPLE.matcher(correoNormalizado).matches()) {
            errores.put("correo", "Introduce un correo con un formato básico válido.");
        } else if (correoNormalizado.length() > 120) {
            errores.put("correo", "El correo no puede superar los 120 caracteres.");
        }

        if (materialNormalizado.isEmpty()) {
            errores.put("material", "El material no puede estar vacio.");
        }else if (!materialNormalizado.equals("PORTATIL")||!materialNormalizado.equals("MICRO")||!materialNormalizado.equals("CAMARA")) {
            errores.put("material", "El material tiene que ser alguno de la lista.");
        }

        if (unidadesNormalizado==null || unidadesNormalizado<=0) {
            errores.put("unidades", "Las unidades no pueden estar vacias.");
        }else if (unidadesNormalizado < 1 || unidadesNormalizado>3) {
            errores.put("unidades", "Las unidades deben ser entre 1 y 3.");
        }

        if(fechaRNormalizado.isEmpty()){
            errores.put("fechaR","La fecha de recogida no puede estar vacia.");
        }

        if(fechaDNormalizado.isEmpty()){
            errores.put("fechaD","La fecha de devolucion no puede estar vacia.");
        }

        if(motivoNormalizado.isEmpty()){
            errores.put("motivo","El motivo no puede estar vacio.");
        }else if(motivoNormalizado.length()>30 || motivoNormalizado.length()<400){
            errores.put("motivo", "El motivo debe estar entre 30 y 400 caracteres.");
        }

        if(!normasNormalizado){
            errores.put("normas","Debe aceptar las normas");
        }

        if (!errores.isEmpty()) {
            anadirValoresSimples(model, nombre, correo, material, unidades, fechaR, fechaD,  motivo, normas);
            model.addAttribute("errores", errores);
            return "formulario-sin-dto";
        }

        model.addAttribute("nombre", nombreNormalizado);
        model.addAttribute("correo", correoNormalizado);
        model.addAttribute("material", materialNormalizado);
        model.addAttribute("unidades", unidadesNormalizado);
        model.addAttribute("fechaR", fechaRNormalizado);
        model.addAttribute("fechaD", fechaDNormalizado);
        model.addAttribute("motivo", motivoNormalizado);
        model.addAttribute("normas", normasNormalizado);
        return "resultado-sin-dto";
    }

    private void anadirValoresSimples(Model model, String nombre, String correo, String material, Integer unidades, String fechaR, String fechaD,  String motivo, boolean normas) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("correo", correo);
        model.addAttribute("material", material);
        model.addAttribute("unidades", unidades);
        model.addAttribute("fechaR", fechaR);
        model.addAttribute("fechaD", fechaD);
        model.addAttribute("motivo", motivo);
        model.addAttribute("normas", normas);
    }
}
