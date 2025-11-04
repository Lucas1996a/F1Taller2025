import java.util.List;
import java.util.ArrayList;

public class Escuderia {

    private String nombre;
    private Pais país;

    private List<PilotoEscuderia> pilotoEscuderia;
    private List<Mecanico> mecánicos;
    private List<Auto> auto;


    public Escuderia() {
    }



    public Escuderia(String nombre, Pais país, List<PilotoEscuderia> pilotoEscuderia, List<Mecanico> mecánicos, List<Auto> auto) {
        this.nombre = nombre;
        this.país = país;
        this.pilotoEscuderia = new ArrayList<>();
        this.mecánicos = new ArrayList<>();
        this.auto = new ArrayList<>();
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPaís() {
        return this.país;
    }

    public void setPaís(Pais país) {
        this.país = país;
    }

    public List<PilotoEscuderia> getPilotoEscuderia() {
        return this.pilotoEscuderia;
    }

    public void setPilotoEscuderia(List<PilotoEscuderia> pilotoEscuderia) {
        this.pilotoEscuderia = pilotoEscuderia;
    }

    public List<Mecanico> getMecánicos() {
        return this.mecánicos;
    }

    public void setMecánicos(List<Mecanico> mecánicos) {
        this.mecánicos = mecánicos;
    }

    public List<Auto> getAuto() {
        return this.auto;
    }

    public void setAuto(List<Auto> auto) {
        this.auto = auto;
    }


}



