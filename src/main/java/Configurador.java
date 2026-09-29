public class Configurador {
    private static String configuracion;

    private Configurador() {}

    private static final Configurador INSTANCIA = new Configurador();

    public static Configurador obtenerInstancia() {
        return INSTANCIA;
    }

    public String obtenerConfiguracion(){
        return configuracion;
    }

    public void establecerConfiguracion(String configuracion){
        this.configuracion = configuracion;
    }
}
