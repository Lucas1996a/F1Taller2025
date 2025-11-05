
import java.util.ArrayList;
import java.util.List;

public class Circuito {

    private String nombre;
    private int longitud;

    private Pais pais;
    private List<Carrera> carreras;

    public Circuito() {
        this.carreras = new ArrayList<>();
    }

    public Circuito(String nombre, int longitud, Pais pais, List<Carrera> carreras) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.pais = pais;
        this.carreras = carreras;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Carrera> getCarrera() {
        return this.carreras;
    }

    public void setCarrera(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void agregarCarreras(Carrera c) {
        this.carreras.add(c);
    }

}
