package com.example.u4p2.apartado2.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "material_aula")
@Getter
@Setter
@NoArgsConstructor

public class MaterialAula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 12, unique = true)
    private String referencia;

    @Column(length = 80)
    private String nombre_material;

    @Column(length = 250, nullable = true)
    private String descripcion;

    @Column(precision = 8, scale = 2)
    private BigDecimal coste_reposicion;

    @Column()
    private short unidades;
}
