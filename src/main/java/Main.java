import maquinaria.*;
import personal.*;

public class Main {
    public static void main(String[] args) {
        Mecanico mec = new Mecanico("Ana Ruiz", 600123456,"Frenos");
        Maquinista maq = new Maquinista("Luis Pérez", "12345678A", 2400, "Senior");

        Locomotora loc = new Locomotora("LOC-001", 3000, 2015);
        loc.inyectarMecanico(mec);

        Tren tren = new Tren(loc, maq);
        tren.añadirVagon(20000, 15000, "Carbón");
    }
}
