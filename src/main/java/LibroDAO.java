import java.util.List;

public interface LibroDAO {
    //Creamos las funciones vacias para implementarlas en LibroDAOImpl.java
    List<Libro> obtenerLibros();
    Libro obtenerPorId(int id);
    void agregar(Libro libro);
    void actualizar(Libro libro);
    void eliminar(int id);
}
