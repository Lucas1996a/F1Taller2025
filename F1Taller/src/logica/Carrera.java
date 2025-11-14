package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un evento de carrera individual en el campeonato.
 * Contiene información sobre la fecha, hora, vueltas, el {@link Circuito}
 * y el {@link Pais} donde se realiza.
 * También mantiene una lista de los participantes ({@link AutoPiloto}) 
 * que compitieron en este evento específico.
 */
public class Carrera {

    /** La fecha en que se realizó la carrera (ej: "2025-11-13"). */
    private String fechaRealizacion;
    /** El número total de vueltas programadas para la carrera. */
    private int numeroVueltas;
    /** La hora de inicio de la carrera (ej: "15:00"). */
    private String horaRealizacion;

    /** El objeto Circuito donde se disputó la carrera. */
    private Circuito circuito;
    /** El objeto Pais anfitrión de la carrera. */
    private Pais pais;
    /**
     * Lista de las relaciones Auto-Piloto que participaron en esta carrera.
     * Define exactamente qué piloto usó qué auto en este evento.
     */
    private List<AutoPiloto> autoPilotos;

    /**
     * Constructor por defecto.
     * Inicializa la lista de 'autoPilotos' como un ArrayList vacío.
     */
    public Carrera() {
        this.autoPilotos = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear una Carrera con todos sus detalles.
     *
     * @param fechaRealizacion Fecha de la carrera.
     * @param numeroVueltas Total de vueltas.
     * @param horaRealizacion Hora de inicio.
     * @param circuito El {@link Circuito} de la carrera.
     * @param pais El {@link Pais} anfitrión.
     * @param autoPilotos Una lista preexistente de participantes.
     */
    public Carrera(String fechaRealizacion, int numeroVueltas, String horaRealizacion, Circuito circuito, Pais pais, List<AutoPiloto> autoPilotos) {
        this.fechaRealizacion = fechaRealizacion;
        this.numeroVueltas = numeroVueltas;
        this.horaRealizacion = horaRealizacion;
        this.circuito = circuito;
        this.pais = pais;
        this.autoPilotos = autoPilotos;
    }

    /**
     * Obtiene la fecha de realización de la carrera.
     * @return La fecha como String.
     */
    public String getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    /**
     * Establece la fecha de realización de la carrera.
     * @param fechaRealizacion La nueva fecha.
     */
    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    /**
     * Obtiene el número de vueltas de la carrera.
     * @return El total de vueltas.
     */
    public int getNumeroVueltas() {
        return this.numeroVueltas;
    }

    /**
     * Establece el número de vueltas de la carrera.
     * @param numeroVueltas El nuevo total de vueltas.
     */
    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    /**
     * Obtiene la hora de realización de la carrera.
     * @return La hora como String.
     */
    public String getHoraRealizacion() {
        return this.horaRealizacion;
    }

    /**
     * Establece la hora de realización de la carrera.
     * @param horaRealizacion La nueva hora.
     */
    public void setHoraRealizacion(String horaRealizacion) {
        this.horaRealizacion = horaRealizacion;
    }

    /**
     * Obtiene el circuito donde se corrió la carrera.
     * @return El objeto {@link Circuito}.
     */
    public Circuito getCircuito() {
        return this.circuito;
    }

    /**
     * Asigna el circuito a la carrera.
     * @param circuito El {@link Circuito} a asignar.
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    /**
     * Obtiene el país anfitrión de la carrera.
     * @return El objeto {@link Pais}.
     */
    public Pais getPais() {
        return this.pais;
    }

    /**
     * Asigna el país anfitrión a la carrera.
     * @param pais El {@link Pais} a asignar.
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * Agrega un participante (Auto-Piloto) a esta carrera.
     * @param a La relación {@link AutoPiloto} que participó.
     */
    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    /**
     * Obtiene la lista de todos los participantes (Auto-Piloto) de la carrera.
     * @return Una lista de {@link AutoPiloto}.
     */
    public List<AutoPiloto> getAutoPilotos() {
        return this.autoPilotos;
    }

    /**
     * Reemplaza la lista de participantes de la carrera.
     * @param autoPilotos La nueva lista de {@link AutoPiloto}.
     */
    public void setAutoPilotos(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }

    /**
     * Genera una representación en String de la carrera, ideal para UIs.
     * Intenta formatearlo como "GP [País] (Fecha)".
     *
     * @return Un String formateado que representa la carrera. 
     * Ej: "GP España (2025-05-20)".
     */
    @Override
    public String toString() {
        String nombreCarrera;

        // Primero, intenta obtener el nombre desde el Circuito
        if (getCircuito() != null && getCircuito().getPais() != null) {
            nombreCarrera = "GP " + getCircuito().getPais().getDescripcion();

        // Si no tiene circuito, intenta obtenerlo desde el País (por las dudas)
        } else if (getPais() != null) {
            nombreCarrera = "GP " + getPais().getDescripcion();

        // Si no tiene ninguno, muestra un texto genérico
        } else {
            nombreCarrera = "Carrera (Datos Incompletos)";
        }

        return nombreCarrera + " (" + this.fechaRealizacion + ")";
    }
}