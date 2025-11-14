package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una pista o circuito de carreras.
 * Almacena el nombre del circuito, su longitud en metros,
 * el {@link Pais} donde se ubica y un historial de las {@link Carrera}
 * que se han disputado en él.
 */
public class Circuito {

    /** El nombre oficial del circuito (ej: "Circuit de Barcelona-Catalunya"). */
    private String nombre;
    /** La longitud total de una vuelta al circuito, expresada en metros. */
    private int longitud;

    /** El objeto Pais que indica la ubicación geográfica del circuito. */
    private Pais pais;
    /** Lista de todas las carreras que han tenido lugar en este circuito. */
    private List<Carrera> carreras;

    /**
     * Constructor por defecto.
     * Inicializa la lista de 'carreras' como un ArrayList vacío.
     */
    public Circuito() {
        this.carreras = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear un Circuito con todos sus datos.
     *
     * @param nombre El nombre del circuito.
     * @param longitud La longitud del circuito en metros.
     * @param pais El {@link Pais} donde se encuentra.
     * @param carreras Una lista preexistente de {@link Carrera} disputadas aquí.
     */
    public Circuito(String nombre, int longitud, Pais pais, List<Carrera> carreras) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.pais = pais;
        this.carreras = carreras;
    }

    /**
     * Obtiene el nombre del circuito.
     * @return El nombre del circuito.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre del circuito.
     * @param nombre El nuevo nombre para el circuito.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la longitud del circuito.
     * @return La longitud en metros.
     */
    public int getLongitud() {
        return this.longitud;
    }

    /**
     * Establece la longitud del circuito.
     * @param longitud La nueva longitud en metros.
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    /**
     * Obtiene el país donde se ubica el circuito.
     * @return El objeto {@link Pais}.
     */
    public Pais getPais() {
        return this.pais;
    }

    /**
     * Establece el país donde se ubica el circuito.
     * @param pais El nuevo {@link Pais} de ubicación.
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * Obtiene la lista de carreras disputadas en este circuito.
     * @return Una lista de objetos {@link Carrera}.
     */
    public List<Carrera> getCarrera() {
        return this.carreras;
    }

    /**
     * Reemplaza la lista de carreras existente por una nueva.
     * @param carreras La nueva lista de {@link Carrera}.
     */
    public void setCarrera(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    /**
     * Agrega una carrera al historial de este circuito.
     * @param c La {@link Carrera} a agregar.
     */
    public void agregarCarreras(Carrera c) {
        this.carreras.add(c);
    }

    /**
     * Sobrescribe el método toString() para mostrar el nombre del circuito.
     * Útil para ComboBoxes y representaciones en UI.
     *
     * @return El nombre del circuito como String.
     */
    @Override
    public String toString() {
        return this.nombre;
    }
}