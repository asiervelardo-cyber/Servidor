package com.example.u4ejercicio_p3.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

// Datos que llegan del formulario. Es distinto de la entidad y no tiene id,
// así el usuario no puede elegir la clave que genera la base de datos.
@Getter
@Setter
@NoArgsConstructor
public class NuevaCancionFormulario {
    @NotBlank(message = "Escribe el título de la cancion.")
    @Size(max = 120, message = "El título no puede superar los 120 caracteres.")
    private String titulo;

    // Opcional y múltiple: el @Pattern va dentro de List<...> para validar cada género
    private List<@Pattern(regexp = "Rock|Pop|Rap|Trap|Reggueton|Country|Hip Hop|Electronica|Jazz|R&B|Clasica|Metal|Otro",
            message = "Selecciona géneros de los disponibles.") String> genero = new ArrayList<>();

    @Pattern(regexp = "Sencillo|Album|EP",
            message = "Selecciona uno de los tipos disponibles.")
    private String tipo;

    // Se escribe como "3:35" (minutos:segundos); no admite 0:00
    @Pattern(regexp = "^(?!0+:00$)\\d{1,2}:[0-5]\\d$",
            message = "Escribe la duración como minutos:segundos, por ejemplo 3:35.")
    private String duracion;

    @NotNull(message = "Indica una valoración.")
    @DecimalMin(value = "0.0", message = "La valoración mínima es 0.")
    @DecimalMax(value = "10.0", message = "La valoración máxima es 10.")
    private BigDecimal valoracion;

    @NotBlank(message = "Indica el nombre del autor/a.")
    @Size(max = 120, message = "El autor/a no puede superar los 120 caracteres.")
    private String autor;

    // El <input type="date"> envía la fecha como aaaa-mm-dd
    @NotNull(message = "Indica la fecha.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    private boolean recomendada;
}
