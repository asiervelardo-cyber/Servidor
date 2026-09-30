public class Libro {
    //Creamos los atributos de libro
    public int id;
    public String titulo;
    public String autor;
    public int anioPublicacion;

    //Creamos el constructor de libro para poder operar con ellos
    public Libro(int id, String titulo, String autor, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }
}
