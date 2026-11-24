package com.example.u4ejercicio_p3.Repositorio;

import java.util.List;

import com.example.u4ejercicio_p3.Modelo.Cancion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA genera la implementación; save(), findAll()... vienen ya hechos
public interface CancionRepositorio extends JpaRepository<Cancion, Long> {
    // Consulta JPQL: trabaja con la clase Cancion, no con el nombre de la tabla
    @Query("SELECT c FROM Cancion c ORDER BY c.titulo ASC")
    List<Cancion> findAllOrdenadosPorTitulo();
}
