public class Main {
    public static void main(String[] args) {
        //Creamos una instancia, y le asignamos un valor a la variable configuración que está dentro de instancia, para mostrarlo por consola
        Configurador instancia = Configurador.obtenerInstancia();
        instancia.establecerConfiguracion("hola");
        String variable =  instancia.obtenerConfiguracion();
        System.out.println(variable);
    }
}
