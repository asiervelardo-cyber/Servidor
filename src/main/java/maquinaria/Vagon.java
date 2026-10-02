package maquinaria;

class Vagon {
    private int cargaMax;
    private int capacidadActual;
    private String tipoMercancia;

    Vagon(int cargaMax, int capacidadActual, String tipoMercancia) {
        if (capacidadActual > cargaMax) {
            System.out.println("Error");
        }else {
            this.cargaMax = cargaMax;
            this.capacidadActual = capacidadActual;
            this.tipoMercancia = tipoMercancia;
        }
    }

    int getcargaMax() {
        return cargaMax;
    }

    int getCapacidadActual() {
        return capacidadActual;
    }

    String getTipoMercancia() {
        return tipoMercancia;
    }
}
