package maquinaria;

import personal.Maquinista;

import java.util.ArrayList;

public class Tren {
    private static final int MAX_VAGONES = 5;

    private Locomotora locomotora;
    private ArrayList<Vagon> vagones = new ArrayList<>();
    private Maquinista maquinista;

    public Tren(Locomotora locomotora, Maquinista maquinista) {
        if (locomotora == null || maquinista == null) {
            System.out.println("Error");
        }else{
            this.locomotora = locomotora;
            this.maquinista = maquinista;
        }

    }

    public boolean añadirVagon(int capacidadMaxima, int capacidadActual, String tipoMercancia) {
        if (vagones.size() >= MAX_VAGONES) {
            return false;
        }
        vagones.add(new Vagon(capacidadMaxima, capacidadActual, tipoMercancia));
        return true;
    }

    public Locomotora getLocomotora() {
        return locomotora;
    }

    public Maquinista getMaquinista() {
        return maquinista;
    }

    public int getNumeroVagones() {
        return vagones.size();
    }
}
