package logica;


import java.util.ArrayList;
import java.util.List;

public class Escuderia {

    private String nombre;
    private Pais pais;

    private List<PilotoEscuderia> pilotoEscuderias;
    private List<MecanicoEscuderia> mecanicoEscuderias;
    private List<Auto> autos;

    public Escuderia() {
        this.autos = new ArrayList<>();
        this.mecanicoEscuderias = new ArrayList<>();
        this.pilotoEscuderias = new ArrayList<>();
    }

    public Escuderia(String nombre, Pais pais, List<PilotoEscuderia> pilotoEscuderias, List<MecanicoEscuderia> mecanicos, List<Auto> autos) {
        this.nombre = nombre;
        this.pais = pais;
        this.autos = autos;
        this.pilotoEscuderias = pilotoEscuderias;
        this.mecanicoEscuderias = mecanicos;

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<PilotoEscuderia> getPilotoEscuderia() {
        return this.pilotoEscuderias;
    }

    public void setPilotoEscuderia(List<PilotoEscuderia> pilotoEscuderias) {
        this.pilotoEscuderias = pilotoEscuderias;
    }
    
    public void agregarPilotoEscuderia(PilotoEscuderia p) {
        this.pilotoEscuderias.add(p);
    }

    public List<MecanicoEscuderia> getMecanicos() {
        return this.mecanicoEscuderias;
    }

    public void setMecanicos(List<MecanicoEscuderia> mecanicos) {
        this.mecanicoEscuderias = mecanicos;
    }

    public void agregarMecanicoEscuderia(MecanicoEscuderia m) {
        this.mecanicoEscuderias.add(m);
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
    
    @Override
    public String toString() {
        return this.nombre;
    }

}
