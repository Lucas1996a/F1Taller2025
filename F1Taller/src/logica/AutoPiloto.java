package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Modela la relación de asignación entre un {@link Piloto} y un {@link Auto}.
 * Esta clase actúa como una tabla de unión (junction) entre Piloto y Auto,
 * especificando la fecha de asignación y registrando las carreras
 * que el piloto disputó con ese auto específico.
 */
public class AutoPiloto {

    /** Fecha (en formato String) en la que el piloto fue asignado a este auto. */
    private String fechaAsignacion;

    /**
     * Lista de carreras en las que el piloto compitió usando este auto
     * bajo esta asignación específica.
     * (Nota: El nombre de la variable empieza con mayúscula por el código original).
     */
    private List<Carrera> Carrera;
    /** El piloto involucrado en esta asignación. */
    private Piloto piloto;
    /** El auto involucrado en esta asignación. */
    private Auto auto;

    /**
     * Constructor por defecto.
     * Inicializa la lista de {@link Carrera} como un ArrayList vacío.
     */
    public AutoPiloto() {
        this.Carrera = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear una asignación Auto-Piloto completa.
     *
     * @param fechaAsignacion La fecha de inicio de la asignación (ej: "2024-03-01").
     * @param Carrera Una lista preexistente de carreras para esta asignación.
     * @param piloto El objeto Piloto a asignar.
     * @param auto El objeto Auto a asignar.
     */
    public AutoPiloto(String fechaAsignacion, List<Carrera> Carrera, Piloto piloto, Auto auto) {
        this.fechaAsignacion = fechaAsignacion;
        this.Carrera = Carrera;
        this.piloto = piloto;
        this.auto = auto;
    }

    /**
     * Obtiene la fecha en que se realizó esta asignación.
     * @return La fecha de asignación como String.
     */
    public String getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    /**
     * Establece la fecha de asignación.
     * @param fechaAsignacion La nueva fecha de asignación.
     */
    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * Obtiene la lista de carreras asociadas a esta asignación.
     * @return Una lista de objetos {@link Carrera}.
     */
    public List<Carrera> getCarrera() {
        return this.Carrera;
    }

    /**
     * Agrega una carrera a la lista de esta asignación.
     * @param c La {@link Carrera} a agregar.
     */
    public void agregarCarrera(Carrera c) {
        this.Carrera.add(c);
    }

    /**
     * Reemplaza la lista de carreras existente por una nueva.
     * @param Carrera La nueva lista de {@link Carrera}.
     */
    public void setCarrera(List<Carrera> Carrera) {
        this.Carrera = Carrera;
    }

    /**
     * Obtiene el piloto de esta asignación.
     * @return El objeto {@link Piloto} asociado.
     */
    public Piloto getPiloto() {
        return this.piloto;
    }

    /**
     * Establece o cambia el piloto de esta asignación.
     * @param piloto El nuevo {@link Piloto} a asociar.
     */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    /**
     * Obtiene el auto de esta asignación.
     * @return El objeto {@link Auto} asociado.
     */
    public Auto getAuto() {
        return this.auto;
    }

    /**
     * Establece o cambia el auto de esta asignación.
     * @param auto El nuevo {@link Auto} a asociar.
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    /**
     * Genera una representación en String de la asignación,
     * combinando el apellido del piloto y el modelo del auto.
     * Muy útil para mostrar en listas o ComboBoxes.
     *
     * @return Un String con el formato "Apellido (Modelo)".
     * Ejemplo: "Hamilton (W15)".
     */
    @Override
    public String toString() {
        // Asegurarse de que piloto y auto no sean nulos antes de llamar a sus métodos
        // (Buena práctica para evitar NullPointerException, aunque se omite aquí por brevedad)
        return getPiloto().getApellido() + " (" + getAuto().getModelo() + ")";
    }
}