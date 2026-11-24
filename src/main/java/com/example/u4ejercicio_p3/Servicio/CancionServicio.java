package com.example.u4ejercicio_p3.Servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.u4ejercicio_p3.Modelo.Cancion;
import com.example.u4ejercicio_p3.Repositorio.CancionRepositorio;

// Lógica de negocio: prepara los datos entre el controlador y el repositorio.
// Por defecto solo lectura; el método que guarda abre su propia transacción.
@Service
@Transactional(readOnly = true)
public class CancionServicio {

    private final CancionRepositorio cancionRepositorio;

    public CancionServicio(CancionRepositorio cancionRepositorio) {
        this.cancionRepositorio = cancionRepositorio;
    }

    public List<Cancion> obtenerTodos() {
        return cancionRepositorio.findAllOrdenadosPorTitulo();
    }

    @Transactional
    public Cancion registrar(
            String titulo,
            String tipo,
            List<String> genero,
            String duracion,
            BigDecimal valoracion,
            String autor,
            LocalDate fecha,
            boolean recomendada) {
        Cancion cancion = new Cancion();
        cancion.setTitulo(titulo.trim());
        cancion.setTipo(tipo);
        // Une los géneros marcados en un texto: "Rock, Pop"
        cancion.setGenero(genero == null || genero.isEmpty() ? null : String.join(", ", genero));
        cancion.setDuracion(aSegundos(duracion));
        cancion.setValoracion(valoracion);
        cancion.setAutor(autor);
        cancion.setFecha(fecha);
        cancion.setRecomendada(recomendada);

        return cancionRepositorio.save(cancion);
    }

    // "3:35" → 215. El formato ya viene validado desde el formulario.
    private Integer aSegundos(String duracion) {
        String[] partes = duracion.trim().split(":");
        return Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1]);
    }
}
