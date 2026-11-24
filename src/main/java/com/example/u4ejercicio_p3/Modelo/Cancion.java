package com.example.u4ejercicio_p3.Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Entidad JPA: Hibernate crea la tabla "canciones" a partir de esta clase
@Entity
@Table(name = "canciones")
@Getter
@Setter
@NoArgsConstructor
public class Cancion {

    // El id lo genera MariaDB (AUTO_INCREMENT)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 120)
    private String titulo;

    // Sencillo, EP o Album
    @Column(name = "tipo", nullable = false, length = 40)
    private String tipo;

    // Varios géneros en una sola columna, separados por comas (sin relaciones entre tablas)
    @Column(name = "genero", length = 200)
    private String genero;

    // Guardada en segundos; en la página se muestra como m:ss
    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "valoracion", nullable = false)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal valoracion;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "recomendada")
    private boolean recomendada;
}
