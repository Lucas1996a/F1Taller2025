package logica;


public class Persona {

    private String dni;
    private String nombre;
    private String apellido;

    private Pais pais;

    public Persona() {
    }

    public Persona(String dni, String nombre, String apellido, Pais pais) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
    
    @Override
    public boolean equals(Object obj) {
        // 1. Si es el mismo objeto exacto, es igual
        if (this == obj) {
            return true;
        }
        // 2. Si el otro es nulo o no es de la misma clase, es diferente
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        // 3. Convertimos el objeto y comparamos
        final Persona other = (Persona) obj;

        // 4. Comparamos los DNIs de forma segura
        // (Si this.dni es null, comprueba si other.dni también es null)
        if (this.dni == null) {
            return other.dni == null;
        }
        
        // 5. Si this.dni no es null, usamos el .equals() de String
        return this.dni.equals(other.dni);
    }

    /**
     * Sobrescribe 'hashCode' (obligatorio si sobrescribes 'equals')
     * Versión "null-safe".
     */
    @Override
    public int hashCode() {
        // Si el dni no es nulo, usa su hashCode. Si es nulo, usa 0.
        return (this.dni != null ? this.dni.hashCode() : 0);
    }

}
