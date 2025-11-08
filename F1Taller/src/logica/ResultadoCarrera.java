/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Lucas
 */
public class ResultadoCarrera {
    
    private Carrera carrera;
    private Piloto piloto;
    private int posicionFinal;
    private String tiempoFinal;
    private boolean vueltaRapida;
    private boolean podio;
    
    
    public ResultadoCarrera(Carrera carrera, Piloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida, boolean podio) {
        this.carrera = carrera;
        this.piloto = piloto;
        this.posicionFinal = posicionFinal;
        this.tiempoFinal = tiempoFinal;
        this.vueltaRapida = vueltaRapida;
        this.podio = podio;
    }



    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public int getPosicionFinal() {
        return this.posicionFinal;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public String getTiempoFinal() {
        return this.tiempoFinal;
    }

    public void setTiempoFinal(String tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public boolean isVueltaRapida() {
        return this.vueltaRapida;
    }

    public boolean getVueltaRapida() {
        return this.vueltaRapida;
    }

    public void setVueltaRapida(boolean vueltaRapida) {
        this.vueltaRapida = vueltaRapida;
    }

    public boolean isPodio() {
        return this.podio;
    }

    public boolean getPodio() {
        return this.podio;
    }

    public void setPodio(boolean podio) {
        this.podio = podio;
    }
    
}
