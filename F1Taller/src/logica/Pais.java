package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una nación o país.
 * Esta es una entidad central en el modelo, ya que agrupa:
 * <ul>
 * <li>{@link Escuderia} (por su sede)</li>
 * <li>{@link Circuito} (por su ubicación)</li>
 * <li>{@link Carrera} (por ser anfitrión)</li>
 * <li>{@link Persona} (por nacionalidad)</li>
 * </ul>
 */
public class Pais {

    /** El identificador numérico único para el país. */
    private int idPais;
    /** El nombre del país (ej: "Italia", "Reino Unido"). */
    private String descripcion;

    /** Lista de escuderías cuya sede principal está en este país. */
    private List<Escuderia> escuderias;
    /** Lista de circuitos de carreras ubicados en este país. */
    private List<Circuito> circuitos;
    /** Lista de carreras (Grandes Premios) que se han celebrado en este país. */
    private List<Carrera> carreras;
    /** Lista de personas (pilotos, mecánicos, etc.) que tienen la nacionalidad de este país. */
    private List<Persona> personas;

    /**
     * Constructor por defecto.
     * Inicializa todas las listas internas (escuderias, circuitos, carreras, personas)
     * como ArrayLists vacíos.
     */
    public Pais() {
        this.escuderias = new ArrayList<>();
        this.circuitos = new ArrayList<>();
        this.carreras = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear un País con todos sus datos
     * y relaciones pre-cargadas.
     *
     * @param idPais El ID único del país.
     * @param descripcion El nombre del país.
     * @param escuderias Una lista preexistente de {@link Escuderia}.
     * @param circuitos Una lista preexistente de {@link Circuito}.
     * @param carreras Una lista preexistente de {@link Carrera}.
     * @param personas Una lista preexistente de {@link Persona}.
     */
    public Pais(int idPais, String descripcion, List<Escuderia> escuderias, List<Circuito> circuitos, List<Carrera> carreras, List<Persona> personas) {
        this.idPais = idPais;
        this.descripcion = descripcion;
        this.escuderias = escuderias;
        this.circuitos = circuitos;
        this.carreras = carreras;
        this.personas = personas;
    }

    /**
     * Obtiene el ID único del país.
     * @return El ID del país.
     */
    public int getIdPais() {
        return this.idPais;
    }

    /**
     * Establece el ID único del país.
     * @param idPais El nuevo ID.
     */
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    /**
     * Obtiene el nombre del país.
     * @return La descripción (nombre) del país.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Establece el nombre del país.
     * @param descripcion El nuevo nombre.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la lista de escuderías de este país.
     * @return Una lista de {@link Escuderia}.
     */
    public List<Escuderia> getEscuderia() {
        return this.escuderias;
    }

    /**
     * Reemplaza la lista de escuderías de este país.
     * @param escuderias La nueva lista de {@link Escuderia}.
     */
    public void setEscuderia(List<Escuderia> escuderias) {
        this.escuderias = escuderias;
    }

    /**
     * Agrega una escudería a la lista de este país.
     * @param e La {@link Escuderia} a agregar.
     */
    public void agregarEscuderia(Escuderia e) {
        this.escuderias.add(e);
    }

    /**
     * Obtiene la lista de circuitos de este país.
     * @return Una lista de {@link Circuito}.
     */
    public List<Circuito> getCircuito() {
        return this.circuitos;
    }

    /**
     * Reemplaza la lista de circuitos de este país.
     * @param circuitos La nueva lista de {@link Circuito}.
     */
    public void setCircuito(List<Circuito> circuitos) {
        this.circuitos = circuitos;
    }

    /**
     * Agrega un circuito a la lista de este país.
     * @param c El {@link Circuito} a agregar.
     */
    public void agregarCircuito(Circuito c) {
        this.circuitos.add(c);
    }

    /**
     * Obtiene la lista de carreras de este país.
     * @return Una lista de {@link Carrera}.
     */
    public List<Carrera> getCarrera() {
        return this.carreras;
    }

    /**
     * Reemplaza la lista de carreras de este país.
     * @param carreras La nueva lista de {@link Carrera}.
     */
    public void setCarrera(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    /**
     * Agrega una carrera a la lista de este país.
     * @param c La {@link Carrera} a agregar.
     */
    public void agregarCarrera(Carrera c) {
        this.carreras.add(c);
    }

    /**
     * Obtiene la lista de personas de este país.
     * @return Una lista de {@link Persona}.
     */
    public List<Persona> getPersona() {
        return this.personas;
    }

    /**
     * Reemplaza la lista de personas de este país.
     * @param persona La nueva lista de {@link Persona}. (El parámetro debería llamarse 'personas' para consistencia).
     */
    public void setPersona(List<Persona> persona) {
        this.personas = persona;
    }

    /**
     * Agrega una persona a la lista de este país.
     * @param p La {@link Persona} a agregar.
     */
    public void agregarPersona(Persona p) {
        this.personas.add(p);
    }

    /**
     * Sobrescribe el método toString() para devolver el nombre del país.
     * Esencial para mostrar el país en ComboBoxes y otros elementos de UI.
     *
     * @return El nombre (descripción) del país.
     */
    @Override
    public String toString() {
        return this.descripcion;
    }
}