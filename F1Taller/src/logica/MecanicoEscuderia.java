/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Lucas
 */
public class MecanicoEscuderia {
    
    
    private String desdeFecha;
    private String hastaFecha;

    private Mecanico mecanico;
    private Escuderia escuderia;

    public MecanicoEscuderia() {
    }

    public MecanicoEscuderia(String desdeFecha, String hastaFecha, Mecanico mecanico, Escuderia escuderia) {
        this.desdeFecha = desdeFecha;
        this.hastaFecha = hastaFecha;
        this.mecanico = mecanico;
        this.escuderia = escuderia;
    }

    public String getDesdeFecha() {
        return this.desdeFecha;
    }

    public void setDesdeFecha(String desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    public String getHastaFecha() {
        return this.hastaFecha;
    }

    public void setHastaFecha(String hastaFecha) {
        this.hastaFecha = hastaFecha;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Escuderia getEscuderia() {
        return this.escuderia;
    }

    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }
}



