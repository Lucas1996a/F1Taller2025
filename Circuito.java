public class Circuito {

    private String nombre;
    private int longitud;

    private Pais pais;
    private List <Carrera> carrera;


    public Circuito() {
    }



    public Circuito(String nombre, int longitud, Pais pais, List<Carrera> carrera) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.pais = pais;
        this.carrera = new ArrayList<>();
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
        return this.carrera;
    }

    public void setCarrera(List<Carrera> carrera) {
        this.carrera = carrera;
    }

    

    
}
