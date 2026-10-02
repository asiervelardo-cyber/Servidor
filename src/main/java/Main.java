import maquinaria.*;
import personal.*;

public class Main {
    public static void main(String[] args) {
        //Añadimos objetos para comprobar que funcione correctamente
        Mecanico mec = new Mecanico("Pepe", 111111111,"Frenos");
        Maquinista maq = new Maquinista("Jose", "12345678A", 1000, "Jefe");

        Locomotora loc = new Locomotora("ABC-123", 5000, 2026);
        loc.inyectarMecanico(mec);

        Tren tren = new Tren(loc, maq);
        tren.añadirVagon(10000, 1000, "Piedras");
    }
}
