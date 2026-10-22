package org.example.u3_ejercicio_p3p4;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class SolicitudForm {
    @NotBlank(message = "Escribe tu nombre.")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres.")
    private String nombre;

    @NotBlank(message = "Escribe tu correo electrónico.")
    @Email(message = "Introduce un correo electrónico válido.")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres.")
    private String correo;

    // Que el código exista en el catálogo se comprueba en el controlador,
    // para no duplicar la lista de códigos en una anotación.
    @NotBlank(message = "Selecciona un material.")
    private String material;

    // Integer (y no String): si llega texto no numérico, el binding falla
    // y se usa el mensaje typeMismatch de messages.properties.
    @NotNull(message = "Indica el número de unidades.")
    @Min(value = 1, message = "Las unidades deben ser como mínimo 1.")
    @Max(value = 3, message = "Las unidades deben ser como máximo 3.")
    private Integer unidades;

    // El control <input type="date"> envía yyyy-MM-dd (ISO).
    @NotNull(message = "Indica la fecha de recogida.")
    @FutureOrPresent(message = "La fecha de recogida no puede ser anterior a hoy.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaRecogida;

    @NotNull(message = "Indica la fecha de devolución.")
    @FutureOrPresent(message = "La fecha de devolución no puede ser anterior a hoy.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaDevolucion;

    @NotBlank(message = "Escribe el motivo del préstamo.")
    @Size(min = 30, max = 400, message = "El motivo debe tener entre 30 y 400 caracteres.")
    private String motivo;

    // Una casilla sin marcar no se envía: el valor queda en false y falla la regla.
    @AssertTrue(message = "Debes aceptar las normas para enviar la solicitud.")
    private boolean aceptaNormas;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Integer getUnidades() { return unidades; }
    public void setUnidades(Integer unidades) { this.unidades = unidades; }

    public LocalDate getFechaRecogida() { return fechaRecogida; }
    public void setFechaRecogida(LocalDate fechaRecogida) { this.fechaRecogida = fechaRecogida; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public boolean isAceptaNormas() { return aceptaNormas; }
    public void setAceptaNormas(boolean aceptaNormas) { this.aceptaNormas = aceptaNormas; }
}
