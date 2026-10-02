package maquinaria;

import personal.Mecanico;

public class Locomotora implements InyectableMecanico {
    private String matricula;
    private int potenciaMotor;
    private int anioFabricacion;
    private Mecanico mecanico;

    public Locomotora(String matricula, int potenciaMotor, int anioFabricacion) {
        this.matricula = matricula;
        this.potenciaMotor = potenciaMotor;
        this.anioFabricacion = anioFabricacion;
    }

    public void inyectarMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }
}
