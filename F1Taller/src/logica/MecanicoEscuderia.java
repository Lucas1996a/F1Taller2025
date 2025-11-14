package logica;

/**
 * Modela la relación laboral entre un {@link Mecanico} y una {@link Escuderia}.
 * Esta clase de asociación registra el período de tiempo (desde/hasta)
 * durante el cual el mecánico trabajó o está trabajando para la escudería.
 *
 * @author Lucas 
 */
public class MecanicoEscuderia {

    /** La fecha (como String) en que el mecánico comenzó su contrato con la escudería. */
    private String desdeFecha;
    /** La fecha (como String) en que el mecánico finalizó su contrato. Puede ser nula si el contrato está activo. */
    private String hastaFecha;

    /** El objeto {@link Mecanico} involucrado en esta relación. */
    private Mecanico mecanico;
    /** El objeto {@link Escuderia} involucrada in esta relación. */
    private Escuderia escuderia;

    /**
     * Constructor por defecto.
     */
    public MecanicoEscuderia() {
    }

    /**
     * Constructor parametrizado para crear una relación Mecanico-Escuderia completa.
     *
     * @param desdeFecha La fecha de inicio del contrato (ej: "2020-01-01").
     * @param hastaFecha La fecha de fin del contrato (ej: "2024-12-31" o null).
     * @param mecanico El {@link Mecanico} a asociar.
     * @param escuderia La {@link Escuderia} a asociar.
     */
    public MecanicoEscuderia(String desdeFecha, String hastaFecha, Mecanico mecanico, Escuderia escuderia) {
        this.desdeFecha = desdeFecha;
        this.hastaFecha = hastaFecha;
        this.mecanico = mecanico;
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
     * Obtiene el mecánico asociado a esta relación.
     * @return El objeto {@link Mecanico}.
     */
    public Mecanico getMecanico() {
        return mecanico;
    }

    /**
     * Establece el mecánico para esta relación.
     * @param mecanico El nuevo {@link Mecanico}.
     */
    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
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