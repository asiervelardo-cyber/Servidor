import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibroDAO libros = new LibroDAOImpl();
        libros.agregar(new Libro(1,"Cien años de soledad","Gabriel García Márquez",1967));
        libros.agregar(new Libro(2,"Don Quijote de la Mancha","Miguel de Cervantes",1605));

        System.out.println("Lista de libros:");
        for (Libro libro : libros.obtenerLibros()) {
            leerLibro(libro);
        }

        System.out.println("Libro id = 1: ");
        leerLibro(libros.obtenerPorId(1));

        System.out.println("Actualizar libro id = 2: ");
        leerLibro(libros.obtenerPorId(2));
        libros.actualizar(new Libro(2,"Nuevo libro","Nadie",2026));
        leerLibro(libros.obtenerPorId(2));

        System.out.println("Eliminar libro id = 2: ");
        libros.eliminar(2);
        leerLibro(libros.obtenerPorId(2));

    }
    public static void leerLibro(Libro libro) {
        if(libro!=null){
            System.out.println(libro.id + " - " + libro.titulo + " por " + libro.autor + ", " + libro.anioPublicacion);
        }else{
            System.out.println("Libro no encontrado");
        }
    }
}
