import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    //Creamos el arrayList para guardar los libros
    ArrayList<Libro> libros = new ArrayList<Libro>();

    //Modificamos las funciones de LibroDAO.java para usarlas en Manin.java
    @Override
    public List<Libro> obtenerLibros() {
        return libros;
    }

    @Override
    public Libro obtenerPorId(int id) {
        for(Libro li: libros){
            if (li.id == id){
                return li;
            }
        }
        return null;
    }

    @Override
    public void agregar(Libro libro) {
        libros.add(libro);
    }

    @Override
    public void actualizar(Libro libro) {
        if(libro != null){
            eliminar(libro.id);
            libros.add(libro);
        }
    }

    @Override
    public void eliminar(int id) {
        if(obtenerPorId(id) != null) {
            libros.remove(obtenerPorId(id));
        }
    }
}