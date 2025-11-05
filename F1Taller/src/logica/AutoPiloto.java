package logica;


import java.util.ArrayList;
import java.util.List;

public class AutoPiloto {

    private String fechaAsignacion;

    private List<Carrera> Carrera;
    private Piloto piloto;
    private Auto auto;

    public AutoPiloto() {
        this.Carrera = new ArrayList<>();
    }

    public AutoPiloto(String fechaAsignacion, List<Carrera> Carrera, Piloto piloto, Auto auto) {
        this.fechaAsignacion = fechaAsignacion;
        this.Carrera = Carrera;
        this.piloto = piloto;
        this.auto = auto;
    }

    public String getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public List<Carrera> getCarrera() {
        return this.Carrera;
    }

    public void agregarCarrera(Carrera c) {
        this.Carrera.add(c);
    }

    public void setCarrera(List<Carrera> Carrera) {
        this.Carrera = Carrera;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Auto getAuto() {
        return this.auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

}
