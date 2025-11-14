package logica;

/**
 * Representa el resultado final de un participante (una combinación {@link AutoPiloto})
 * en una {@link Carrera} específica.
 * Almacena la posición final, el tiempo, y si el participante
 * logró la vuelta rápida o un podio.
 *
 * @author Lucas (Preservado del original)
 */
public class ResultadoCarrera {

    /** La carrera a la que pertenece este resultado. */
    private Carrera carrera;
    /** El participante (combinación Auto-Piloto) que logró este resultado. */
    private AutoPiloto piloto;
    /** La posición en la que el piloto finalizó la carrera (1, 2, ...). */
    private int posicionFinal;
    /** El tiempo total de carrera o la diferencia (ej: "1:30:45.000", "+1 Lap", "DNF"). */
    private String tiempoFinal;
    /** Indica si este participante logró la vuelta rápida (true) o no (false). */
    private boolean vueltaRapida;
    /** Indica si este participante finalizó en el podio (true) o no (false). */
    private boolean podio;

    /**
     * Constructor por defecto.
     */
    public ResultadoCarrera() {

    }

    /**
     * Constructor parametrizado para crear un resultado de carrera completo.
     *
     * @param carrera La {@link Carrera} a la que se asocia el resultado.
     * @param piloto El {@link AutoPiloto} participante.
     * @param posicionFinal La posición final en la carrera.
     * @param tiempoFinal El tiempo final o estado (ej: 'DNF').
     * @param vueltaRapida {@code true} si logró la vuelta rápida.
     * @param podio {@code true} si terminó en el podio.
     */
    public ResultadoCarrera(Carrera carrera, AutoPiloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida, boolean podio) {
        this.carrera = carrera;
        this.piloto = piloto;
        this.posicionFinal = posicionFinal;
        this.tiempoFinal = tiempoFinal;
        this.vueltaRapida = vueltaRapida;
        this.podio = podio;
    }

    /**
     * Obtiene la carrera de este resultado.
     * @return El objeto {@link Carrera} asociado.
     */
    public Carrera getCarrera() {
        return this.carrera;
    }

    /**
     * Establece la carrera para este resultado.
     * @param carrera El nuevo objeto {@link Carrera}.
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Obtiene el participante (AutoPiloto) de este resultado.
     * @return El participante {@link AutoPiloto}.
     */
    public AutoPiloto getAutoPiloto() {
        return this.piloto;
    }

    /**
     * Establece el participante (AutoPiloto) para este resultado.
     * @param piloto El nuevo participante {@link AutoPiloto}.
     */
    public void setAutoPiloto(AutoPiloto piloto) {
        this.piloto = piloto;
    }

    /**
     * Obtiene la posición final del participante.
     * @return La posición final (entero).
     */
    public int getPosicionFinal() {
        return this.posicionFinal;
    }

    /**
     * Establece la posición final del participante.
     * @param posicionFinal La nueva posición final.
     */
    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    /**
     * Obtiene el tiempo final del participante.
     * @return El tiempo final como String.
     */
    public String getTiempoFinal() {
        return this.tiempoFinal;
    }

    /**
     * Establece el tiempo final del participante.
     * @param tiempoFinal El nuevo tiempo final (ej: "1:30:00.123" o "DNF").
     */
    public void setTiempoFinal(String tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    /**
     * Verifica si el participante obtuvo la vuelta rápida (convención 'is').
     * @return {@code true} si logró la vuelta rápida, {@code false} en caso contrario.
     */
    public boolean isVueltaRapida() {
        return this.vueltaRapida;
    }

    /**
     * Obtiene si el participante obtuvo la vuelta rápida (convención 'get').
     * @return {@code true} si logró la vuelta rápida, {@code false} en caso contrario.
     */
    public boolean getVueltaRapida() {
        return this.vueltaRapida;
    }

    /**
     * Establece el estado de vuelta rápida.
     * @param vueltaRapida {@code true} si logró la vuelta rápida.
     */
    public void setVueltaRapida(boolean vueltaRapida) {
        this.vueltaRapida = vueltaRapida;
    }

    /**
     * Verifica si el participante obtuvo podio (convención 'is').
     * @return {@code true} si logró podio, {@code false} en caso contrario.
     */
    public boolean isPodio() {
        return this.podio;
    }

    /**
     * Obtiene si el participante obtuvo podio (convención 'get').
     * @return {@code true} si logró podio, {@code false} en caso contrario.
     */
    public boolean getPodio() {
        return this.podio;
    }

    /**
     * Establece el estado de podio.
     * @param podio {@code true} si logró podio.
     */
    public void setPodio(boolean podio) {
        this.podio = podio;
    }

}