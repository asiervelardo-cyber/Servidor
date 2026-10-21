package org.example.u3_ejercicio_p3p4;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class SolicitudForm {
    //ya
    @NotBlank(message = "Escribe tu nombre.")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres.")
    private String nombre;
    //ya
    @NotBlank(message = "Escribe una dirección de correo.")
    @Email(message = "Introduce una dirección de correo válida.")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres.")
    private String correo;
    //ya
    @NotBlank(message = "Selecciona un material.")
    @Pattern(regexp = "PORTATIL|MICRO|CAMARA",
            message = "Selecciona uno de los materiales disponibles.")
    private String material;
    //ya
    @NotBlank(message = "Selecciona las unidades.")
    @Min(value = 1, message = "El valor minimo es 1.")
    @Max(value = 3, message = "El valor maximo es 3.")
    private String unidades;
    //ya
    @NotBlank(message = "Escribe la fecha de recogida")
    private String fechaRecogida;
    //ya
    @NotBlank(message = "Escribe la fecha de devolucion")
    private String fechaDevolucion;
    //ya
    @NotBlank(message = "Motivo del préstamo.")
    @Size(min = 30, max = 400,
            message = "El motivo debe tener entre 30 y 400 caracteres.")
    private String motivo;
    //ya
    @AssertTrue(message = "Debes aceptar las normas para continuar.")
    private boolean aceptaNormas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getfechaRecogida() {
        return fechaRecogida;
    }

    public void setfechaRecogida(String fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getfechaDevolucion() {
        return fechaDevolucion;
    }

    public void setfechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isAceptaNormas() {
        return aceptaNormas;
    }

    public void setAceptaNormas(boolean aceptaNormas) {
        this.aceptaNormas = aceptaNormas;
    }
}
