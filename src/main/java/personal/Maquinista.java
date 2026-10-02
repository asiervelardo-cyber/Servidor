package personal;

public class Maquinista {
    //Creamos la clase maquinista con sus atributos, constructor y métodos para obtener los atributos
    private String nombreCompleto;
    private String DNI;
    private double sueldoMensual;
    private String rango;

    public Maquinista(String nombreCompleto, String dni, double sueldoMensual, String rango) {
        this.nombreCompleto = nombreCompleto;
        this.DNI = dni;
        this.sueldoMensual = sueldoMensual;
        this.rango = rango;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDni() {
        return DNI;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }
    public String getRango() {
        return rango;
    }
}
