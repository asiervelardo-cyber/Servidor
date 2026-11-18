package com.example.u4p2.apartado1.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.RequiredTypes;

import java.math.BigDecimal;

@Entity
@Table(name = "recursos")
@Getter
@Setter
@NoArgsConstructor
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12, unique = true)
    private String codigoRecurso;

    @Column(length = 80)
    private String nombre;

    @Column(length = 250, nullable = false)
    private String descripcion;

    @Column(precision = 8, scale = 2)
    private BigDecimal costeReposicion;

    @Column()
    private boolean prestable;
}
