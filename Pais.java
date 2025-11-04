public class Pais {

    private int idPais;
    private String descripcion;

    private List<Escuderia> escuderia;
    private List<Circuito> circuito;
    private List<Carrera> carrera;
    private List<Persona> persona;



    public Pais() {
    }


    public Pais(int idPais, String descripcion, List<Escuderia> escuderia, List<Circuito> circuito, List<Carrera> carrera, List<Persona> persona) {
        this.idPais = idPais;
        this.descripcion = descripcion;
        this.escuderia = new ArrayList<>();
        this.circuito = new ArrayList<>();
        this.carrera = new ArrayList<>();
        this.persona = new ArrayList<>();
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
        return this.escuderia;
    }

    public void setEscuderia(List<Escuderia> escuderia) {
        this.escuderia = escuderia;
    }

    public List<Circuito> getCircuito() {
        return this.circuito;
    }

    public void setCircuito(List<Circuito> circuito) {
        this.circuito = circuito;
    }

    public List<Carrera> getCarrera() {
        return this.carrera;
    }

    public void setCarrera(List<Carrera> carrera) {
        this.carrera = carrera;
    }

    public List<Persona> getPersona() {
        return this.persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }



    
}
