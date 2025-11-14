package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un piloto de carreras, extendiendo la clase {@link Persona}.
 * Almacena información personal básica y, además, estadísticas de su carrera
 * (victorias, poles, etc.) y las relaciones con los autos que ha conducido
 * ({@link AutoPiloto}) y las escuderías en las que ha estado
 * ({@link PilotoEscuderia}).
 */
public class Piloto extends Persona {

    /** El número fijo que utiliza el piloto en la competencia (ej: 44, 1). */
    private int numeroCompetencia;
    /** Contador total de victorias en carreras. */
    private int victorias;
    /** Contador total de pole positions (primer puesto en clasificación). */
    private int polePosition;
    /** Contador total de vueltas rápidas logradas en carrera. */
    private int vueltasRapidas;
    /** Contador total de podios (1er, 2do o 3er puesto). */
    private int podios;

    /**
     * Lista de relaciones {@link AutoPiloto} que detalla
     * cada auto específico que este piloto ha conducido.
     */
    private List<AutoPiloto> autoPilotos;
    /**
     * Lista de relaciones {@link PilotoEscuderia} que detalla
     * el historial de escuderías para las que ha competido.
     */
    private List<PilotoEscuderia> pilotoEscuderias;

    /**
     * Constructor por defecto.
     * Inicializa las listas 'autoPilotos' y 'pilotoEscuderias' como ArrayLists vacíos.
     */
    public Piloto() {
        this.autoPilotos = new ArrayList<>();
        this.pilotoEscuderias = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear un Piloto con todos sus datos.
     *
     * @param dni El documento de identidad del piloto.
     * @param nombre El nombre de pila.
     * @param apellido El apellido.
     * @param pais El {@link Pais} de nacionalidad.
     * @param numeroCompetencia El número de competencia del piloto.
     * @param victorias Total de victorias.
     * @param polePosition Total de pole positions.
     * @param vueltasRapidas Total de vueltas rápidas.
     * @param podios Total de podios.
     * @param autoPilotos Lista preexistente de relaciones {@link AutoPiloto}.
     * @param pilotoEscuderias Lista preexistente de relaciones {@link PilotoEscuderia}.
     */
    public Piloto(String dni, String nombre, String apellido, Pais pais, int numeroCompetencia, int victorias, int polePosition, int vueltasRapidas, int podios, List<AutoPiloto> autoPilotos, List<PilotoEscuderia> pilotoEscuderias) {
        super(dni, nombre, apellido, pais); // Llama al constructor de Persona
        this.numeroCompetencia = numeroCompetencia;
        this.victorias = victorias;
        this.polePosition = polePosition;
        this.vueltasRapidas = vueltasRapidas;
        this.podios = podios;
        this.autoPilotos = autoPilotos;
        this.pilotoEscuderias = pilotoEscuderias;
    }

    /**
     * Obtiene el número de competencia del piloto.
     * @return El número de competencia.
     */
    public int getNumeroCompetencia() {
        return this.numeroCompetencia;
    }

    /**
     * Establece el número de competencia del piloto.
     * @param numeroCompetencia El nuevo número.
     */
    public void setNumeroCompetencia(int numeroCompetencia) {
        this.numeroCompetencia = numeroCompetencia;
    }

    /**
     * Obtiene el total de victorias.
     * @return El número de victorias.
     */
    public int getVictorias() {
        return this.victorias;
    }

    /**
     * Establece el total de victorias.
     * @param victorias El nuevo total.
     */
    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    /**
     * Obtiene el total de pole positions.
     * @return El número de pole positions.
     */
    public int getPolePosition() {
        return this.polePosition;
    }

    /**
     * Establece el total de pole positions.
     * @param polePosition El nuevo total.
     */
    public void setPolePosition(int polePosition) {
        this.polePosition = polePosition;
    }

    /**
     * Obtiene el total de vueltas rápidas.
     * @return El número de vueltas rápidas.
     */
    public int getVueltasRapidas() {
        return this.vueltasRapidas;
    }

    /**
     * Establece el total de vueltas rápidas.
     * @param vueltasRapidas El nuevo total.
     */
    public void setVueltasRapidas(int vueltasRapidas) {
        this.vueltasRapidas = vueltasRapidas;
    }

    /**
     * Obtiene el total de podios.
     * @return El número de podios.
     */
    public int getPodios() {
        return this.podios;
    }

    /**
     * Establece el total de podios.
     * @param podios El nuevo total.
     */
    public void setPodios(int podios) {
        this.podios = podios;
    }

    /**
     * Agrega una relación Auto-Piloto al historial del piloto.
     * @param a La relación {@link AutoPiloto} a agregar.
     */
    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    /**
     * Obtiene la lista de relaciones Auto-Piloto del piloto.
     * @return Una lista de {@link AutoPiloto}.
     */
    public List<AutoPiloto> getAutoPiloto() {
        return this.autoPilotos;
    }

    /**
     * Reemplaza la lista de relaciones Auto-Piloto del piloto.
     * @param autoPilotos La nueva lista de {@link AutoPiloto}.
     */
    public void setAutoPiloto(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }

    /**
     * Obtiene la lista de relaciones Piloto-Escudería del piloto.
     * @return Una lista de {@link PilotoEscuderia}.
     */
    public List<PilotoEscuderia> getPilotoEscuderias() {
        return this.pilotoEscuderias;
    }

    /**
     * Reemplaza la lista de relaciones Piloto-Escudería del piloto.
     * @param pilotoEscuderias La nueva lista de {@link PilotoEscuderia}.
     */
    public void setPilotoEscuderias(List<PilotoEscuderia> pilotoEscuderias) {
        this.pilotoEscuderias = pilotoEscuderias;
    }

    /**
     * Agrega una relación Piloto-Escudería al historial del piloto.
     * @param p La relación {@link PilotoEscuderia} a agregar.
     */
    public void agregarPilotoEscuderia(PilotoEscuderia p) {
        this.pilotoEscuderias.add(p);
    }

    /**
     * Sobrescribe el método toString() para mostrar el nombre completo del piloto.
     * Útil para ComboBoxes y representaciones en UI.
     *
     * @return El nombre y apellido del piloto como String.
     */
    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellido();
    }
}