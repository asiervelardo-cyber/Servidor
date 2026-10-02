package maquinaria;

class Vagon {
    //Creamos la clase Vagon con sus aributos, constructor(con controlador de que la carga no supere el maximo) y metodos para obtener los atributos
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
