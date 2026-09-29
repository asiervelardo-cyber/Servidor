public class Main {
    public static void main(String[] args) {
        Configurador instancia = Configurador.obtenerInstancia();
        instancia.establecerConfiguracion("hola");
        String variable =  instancia.obtenerConfiguracion();
        System.out.println(variable);
    }
}
