package logica;


import java.util.ArrayList;
import java.util.List;

public class Carrera {

    private String fechaRealizacion;
    private int numeroVueltas;
    private String horaRealizacion;

    private Circuito circuito;
    private Pais pais;
    private List<AutoPiloto> autoPilotos;

    public Carrera() {
        this.autoPilotos = new ArrayList<>();
    }

    public Carrera(String fechaRealizacion, int numeroVueltas, String horaRealizacion, Circuito circuito, Pais pais, List<AutoPiloto> autoPilotos) {
        this.fechaRealizacion = fechaRealizacion;
        this.numeroVueltas = numeroVueltas;
        this.horaRealizacion = horaRealizacion;
        this.circuito = circuito;
        this.pais = pais;
        this.autoPilotos = autoPilotos;
    }

    public String getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public int getNumeroVueltas() {
        return this.numeroVueltas;
    }

    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    public String getHoraRealizacion() {
        return this.horaRealizacion;
    }

    public void setHoraRealizacion(String horaRealizacion) {
        this.horaRealizacion = horaRealizacion;
    }

    public Circuito getCircuito() {
        return this.circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    public List<AutoPiloto> getAutoPilotos() {
        return this.autoPilotos;
    }

    public void setAutoPilotos(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }
    
    @Override
    public String toString() {
    String nombreCarrera;
    
    // Primero, intenta obtener el nombre desde el Circuito (que es más específico)
    if (getCircuito() != null && getCircuito().getPais() != null) {
        nombreCarrera = "GP " + getCircuito().getPais().getDescripcion();
    
    // Si no tiene circuito, intenta obtenerlo desde el País (por si acaso)
    } else if (getPais() != null) {
        nombreCarrera = "GP " + getPais().getDescripcion();
        
    // Si no tiene ninguno, muestra un texto genérico
    } else {
        nombreCarrera = "Carrera (Datos Incompletos)";
    }
    
    return nombreCarrera + " (" + this.fechaRealizacion + ")";
}

}
