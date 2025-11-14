package logica;

/**
 * Modela la relación contractual (asiento) entre un {@link Piloto} y una {@link Escuderia}.
 * Esta clase de asociación registra el período de tiempo (desde/hasta)
 * durante el cual el piloto compitió para la escudería.
 */
public class PilotoEscuderia {

    /** La fecha (como String) en que el piloto comenzó su contrato con la escudería. */
    private String desdeFecha;
    /** La fecha (como String) en que el piloto finalizó su contrato. Puede ser nula si el contrato está activo. */
    private String hastaFecha;

    /** El objeto {@link Piloto} involucrado en esta relación. */
    private Piloto piloto;
    /** El objeto {@link Escuderia} involucrada in esta relación. */
    private Escuderia escuderia;

    /**
     * Constructor por defecto.
     */
    public PilotoEscuderia() {
    }

    /**
     * Constructor parametrizado para crear una relación Piloto-Escuderia completa.
     *
     * @param desdeFecha La fecha de inicio del contrato (ej: "2020-01-01").
     * @param hastaFecha La fecha de fin del contrato (ej: "2024-12-31" o null).
     * @param piloto El {@link Piloto} a asociar.
     * @param escuderia La {@link Escuderia} a asociar.
     */
    public PilotoEscuderia(String desdeFecha, String hastaFecha, Piloto piloto, Escuderia escuderia) {
        this.desdeFecha = desdeFecha;
        this.hastaFecha = hastaFecha;
        this.piloto = piloto;
        this.escuderia = escuderia;
    }

    /**
     * Obtiene la fecha de inicio del contrato.
     * @return La fecha de inicio como String.
     */
    public String getDesdeFecha() {
        return this.desdeFecha;
    }

    /**
     * Establece la fecha de inicio del contrato.
     * @param desdeFecha La nueva fecha de inicio.
     */
    public void setDesdeFecha(String desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    /**
     * Obtiene la fecha de finalización del contrato.
     * @return La fecha de fin como String (puede ser null).
     */
    public String getHastaFecha() {
        return this.hastaFecha;
    }

    /**
     * Establece la fecha de finalización del contrato.
     * @param hastaFecha La nueva fecha de fin.
     */
    public void setHastaFecha(String hastaFecha) {
        this.hastaFecha = hastaFecha;
    }

    /**
     * Obtiene el piloto asociado a esta relación.
     * @return El objeto {@link Piloto}.
     */
    public Piloto getPiloto() {
        return this.piloto;
    }

    /**
     * Establece el piloto para esta relación.
     * @param piloto El nuevo {@link Piloto}.
     */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    /**
     * Obtiene la escudería asociada a esta relación.
     * @return El objeto {@link Escuderia}.
     */
    public Escuderia getEscuderia() {
        return this.escuderia;
    }

    /**
     * Establece la escudería para esta relación.
     * @param escuderia La nueva {@link Escuderia}.
     */
    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }
}