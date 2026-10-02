package personal;

public class Mecanico {
    //Creamos la clase Mecanico con sus aributos, constructor y metodos para obtener los atributos
    private String nombreCompleto;
    private int numeroTlf;
    private String especialidad; //frenos o hidraulica

    public Mecanico(String nombreCompleto, int telefono, String especialidad) {
        this.nombreCompleto = nombreCompleto;
        this.numeroTlf = telefono;
        this.especialidad = especialidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getTelefono() {
        return numeroTlf;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
