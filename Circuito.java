public class Circuito {

    private String nombre;
    private int longitud;



    public Circuito() {
    }


    public Circuito(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
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

    
}
