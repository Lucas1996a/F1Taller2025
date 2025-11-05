package logica;

import java.util.ArrayList;
import java.util.List;

public class Mecanico extends Persona {

    private Especialidad especialidad;
    private int añosExperiencia;

    private List<Escuderia> escuderias;

    public Mecanico() {
        this.escuderias = new ArrayList<>();
    }

    public Mecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int añosExperiencia, List<Escuderia> escuderias) {
        super(dni, nombre, apellido, pais);
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
        this.escuderias = escuderias;
    }

    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getAñosExperiencia() {
        return this.añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public List<Escuderia> getEscuderias() {
        return this.escuderias;
    }

    public void setEscuderia(List<Escuderia> escuderias) {
        this.escuderias = escuderias;
    }

    public void agregarEscuderia(Escuderia e) {
        this.escuderias.add(e);
    }

}
