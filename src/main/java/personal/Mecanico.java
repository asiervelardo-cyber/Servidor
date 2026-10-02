package personal;

public class Mecanico {
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
