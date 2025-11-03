public class Persona {

    private String dn;
    private String nombre;
    private String apellido;

    private Pais pais;



    public Persona() {
    }


    public Persona(String dn, String nombre, String apellido, Pais pais) {
        this.dn = dn;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
    }



    public String getDn() {
        return this.dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}
