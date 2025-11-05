package logica;


import java.util.ArrayList;
import java.util.List;

public class Escuderia {

    private String nombre;
    private Pais pais;

    private List<PilotoEscuderia> pilotoEscuderias;
    private List<Mecanico> mecanicos;
    private List<Auto> autos;

    public Escuderia() {
        this.autos = new ArrayList<>();
        this.mecanicos = new ArrayList<>();
        this.pilotoEscuderias = new ArrayList<>();
    }

    public Escuderia(String nombre, Pais pais, List<PilotoEscuderia> pilotoEscuderias, List<Mecanico> mecanicos, List<Auto> autos) {
        this.nombre = nombre;
        this.pais = pais;
        this.autos = autos;
        this.pilotoEscuderias = pilotoEscuderias;
        this.mecanicos = mecanicos;

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPaís() {
        return this.pais;
    }

    public void setPaís(Pais pais) {
        this.pais = pais;
    }

    public List<PilotoEscuderia> getPilotoEscuderia() {
        return this.pilotoEscuderias;
    }

    public void setPilotoEscuderia(List<PilotoEscuderia> pilotoEscuderias) {
        this.pilotoEscuderias = pilotoEscuderias;
    }

    public void agregarPiloto(PilotoEscuderia p) {
        this.pilotoEscuderias.add(p);
    }

    public List<Mecanico> getMecánicos() {
        return this.mecanicos;
    }

    public void setMecánicos(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public void agregarMecanico(Mecanico m) {
        this.mecanicos.add(m);
    }

    public List<Auto> getAutos() {
        return this.autos;
    }

    public void setAuto(List<Auto> autos) {
        this.autos = autos;
    }

    public void agregarAuto(Auto a) {
        this.autos.add(a);
    }

}
