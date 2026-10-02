package personal;

public class JefeEstacion {
    //Creamos la clase JefeEstacion con sus aributos, constructor y metodos para obtener los atributos
    private String nombreCompleto;
    private String DNI;

    public JefeEstacion(String nombreCompleto, String dni) {
        this.nombreCompleto = nombreCompleto;
        this.DNI = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDni() {
        return DNI;
    }
}
