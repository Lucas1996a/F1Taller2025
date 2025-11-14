package logica;

/**
 * Clase base abstracta que representa a un individuo.
 * Contiene información personal fundamental como DNI, nombre, apellido
 * y el {@link Pais} de nacionalidad.
 * Esta clase sirve como superclase para tipos más específicos como
 * {@link Piloto} y {@link Mecanico}.
 */
public class Persona { // Nota: Considera hacerla 'abstract' si nunca crearás una 'Persona' genérica

    /** Documento Nacional de Identidad (u otro identificador único). */
    private String dni;
    /** Nombre de pila de la persona. */
    private String nombre;
    /** Apellido de la persona. */
    private String apellido;

    /** El {@link Pais} de origen o nacionalidad de la persona. */
    private Pais pais;

    /**
     * Constructor por defecto.
     */
    public Persona() {
    }

    /**
     * Constructor parametrizado para crear una Persona con sus datos básicos.
     *
     * @param dni El identificador único (DNI).
     * @param nombre El nombre de pila.
     * @param apellido El apellido.
     * @param pais El {@link Pais} de nacionalidad.
     */
    public Persona(String dni, String nombre, String apellido, Pais pais) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
    }

    /**
     * Obtiene el DNI de la persona.
     * @return El DNI como String.
     */
    public String getDni() {
        return this.dni;
    }

    /**
     * Establece el DNI de la persona.
     * @param dni El nuevo DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre de pila de la persona.
     * @return El nombre.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre de pila de la persona.
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido de la persona.
     * @return El apellido.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * Establece el apellido de la persona.
     * @param apellido El nuevo apellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el país de nacionalidad de la persona.
     * @return El objeto {@link Pais}.
     */
    public Pais getPais() {
        return this.pais;
    }

    /**
     * Establece el país de nacionalidad de la persona.
     * @param pais El nuevo {@link Pais}.
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * Compara esta Persona con otro objeto para determinar si son iguales.
     * La igualdad se basa **únicamente en el atributo DNI**.
     *
     * @param obj El objeto a comparar.
     * @return {@code true} si el objeto es una Persona y tiene el mismo DNI
     * (incluyendo si ambos DNIs son {@code null}), {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        // 1. Si es el mismo objeto exacto, es igual
        if (this == obj) {
            return true;
        }
        // 2. Si el otro es nulo o no es de la misma clase, es diferente
        // (Nota: getClass() es estricto, instanceof permitiría subclases)
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 3. Convertimos el objeto y comparamos
        final Persona other = (Persona) obj;

        // 4. Comparamos los DNIs de forma segura (null-safe)
        // (Si this.dni es null, comprueba si other.dni también es null)
        if (this.dni == null) {
            return other.dni == null;
        }

        // 5. Si this.dni no es null, usamos el .equals() de String
        return this.dni.equals(other.dni);
    }

    /**
     * Genera un código hash para la Persona, basado en el DNI.
     * Es obligatorio sobrescribir hashCode() si se sobrescribe equals(),
     * para asegurar que objetos iguales tengan el mismo hashCode.
     *
     * @return El código hash del DNI, o 0 si el DNI es {@code null}.
     */
    @Override
    public int hashCode() {
        // Si el dni no es nulo, usa su hashCode. Si es nulo, usa 0.
        return (this.dni != null ? this.dni.hashCode() : 0);
    }
}