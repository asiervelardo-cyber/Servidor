public class Configurador {
    //Creamos la variable configuración y sus métodos de obtener y establecer
    private static String configuracion;

    public String obtenerConfiguracion(){
        return configuracion;
    }

    public void establecerConfiguracion(String configuracion){
        this.configuracion = configuracion;
    }

    //Creamos el Confiigurador privado
    private Configurador() {}

    //Creamos la instancia de tipo configurador y el metodo para obtenerla
    private static final Configurador INSTANCIA = new Configurador();

    public static Configurador obtenerInstancia() {
        return INSTANCIA;
    }


}
