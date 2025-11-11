package logica;

import java.util.ArrayList;
import java.util.List;
import logica.Escuderia;

public class Mecanico extends Persona {

    private Especialidad especialidad;
    private int añosExperiencia;

    private List<MecanicoEscuderia> mecanicoEscuderias;
    
    public Mecanico() {
        this.mecanicoEscuderias = new ArrayList<>();
    }

    public Mecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int añosExperiencia, List<MecanicoEscuderia> escuderias) {
        super(dni, nombre, apellido, pais);
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
        this.mecanicoEscuderias = escuderias;
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

    public List<MecanicoEscuderia> getMecanicoEscuderias() {
        return this.mecanicoEscuderias;
    }

    public void setEscuderia(List<MecanicoEscuderia> escuderias) {
        this.mecanicoEscuderias = escuderias;
    }
    
    public void agregarMecanicoEscuderia(MecanicoEscuderia e) {
        this.mecanicoEscuderias.add(e);
    }
    
    @Override
    public String toString(){
        return this.getNombre() + " " + this.getApellido();
    }

}
