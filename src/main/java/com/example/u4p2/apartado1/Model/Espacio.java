package com.example.u4p2.apartado1.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.RequiredTypes;

@Entity
@Table(name = "espacio")
@Getter
@Setter
@NoArgsConstructor

public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, unique = true)
    private String codigoEspacio;

    @Column(length = 60)
    private String nombre;

    @Column()
    private Integer aforo;

    @Column()
    private Integer planta;

    @Column()
    private boolean accesible;
}
