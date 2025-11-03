import java.util.List;
import java.util.ArrayList;

public class Escuderia {

    private String nombre;

    private Pais país;

   
    private List<Mecanico> mecánicos; 

   
    private List<Auto> autos = new ArrayList<>();
    private List<Piloto> pilotos = new ArrayList<>();



    public Escuderia() {
    }



    public Escuderia(String nombre, Pais país, List<Mecanico> mecánicos, List<Auto> autos, List<Piloto> pilotos) {
        this.nombre = nombre;
        this.país = país;
        this.mecánicos = mecánicos;
        this.autos = autos;
        this.pilotos = pilotos;
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

    public List<Mecanico> getMecánicos() {
        return this.mecánicos;
    }

    public void setMecánicos(List<Mecanico> mecánicos) {
        this.mecánicos = mecánicos;
    }

    public List<Auto> getAutos() {
        return this.autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public List<Piloto> getPilotos() {
        return this.pilotos;
    }

    public void setPilotos(List<Piloto> pilotos) {
        this.pilotos = pilotos;
    }



}



