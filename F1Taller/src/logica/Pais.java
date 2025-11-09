package logica;


import java.util.ArrayList;
import java.util.List;

public class Pais {

    private int idPais;
    private String descripcion;

    private List<Escuderia> escuderias;
    private List<Circuito> circuitos;
    private List<Carrera> carreras;
    private List<Persona> personas;

    public Pais() {
        this.escuderias = new ArrayList<>();
        this.circuitos = new ArrayList<>();
        this.carreras = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public Pais(int idPais, String descripcion, List<Escuderia> escuderias, List<Circuito> circuitos, List<Carrera> carreras, List<Persona> personas) {
        this.idPais = idPais;
        this.descripcion = descripcion;
        this.escuderias = escuderias;
        this.circuitos = circuitos;
        this.carreras = carreras;
        this.personas = personas;
    }

    public int getIdPais() {
        return this.idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Escuderia> getEscuderia() {
        return this.escuderias;
    }

    public void setEscuderia(List<Escuderia> escuderias) {
        this.escuderias = escuderias;
    }

    public void agregarEscuderia(Escuderia e) {
        this.escuderias.add(e);
    }

    public List<Circuito> getCircuito() {
        return this.circuitos;
    }

    public void setCircuito(List<Circuito> circuitos) {
        this.circuitos = circuitos;
    }

    public void agregarCircuito(Circuito c) {
        this.circuitos.add(c);
    }

    public List<Carrera> getCarrera() {
        return this.carreras;
    }

    public void setCarrera(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void agregarCarrera(Carrera c) {
        this.carreras.add(c);
    }

    public List<Persona> getPersona() {
        return this.personas;
    }

    public void setPersona(List<Persona> persona) {
        this.personas = persona;
    }

    public void agregarPersona(Persona p) {
        this.personas.add(p);
    }
    
    @Override
    public String toString() {
    // Muestra la descripción (ej: "Argentina") en lugar de "logica.Pais@123abc"
    return this.descripcion;
}
}
