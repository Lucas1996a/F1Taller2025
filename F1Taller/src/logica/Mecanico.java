package logica;

import java.util.ArrayList;

/**
 * Representa a un mecánico del equipo, extendiendo la clase {@link Persona}.
 * Almacena información personal básica, su {@link Especialidad} (ej: MOTOR, CHASIS),
 * sus años de experiencia y un historial de las escuderías
 * en las que ha trabajado ({@link MecanicoEscuderia}).
 */
public class Mecanico extends Persona {

    /** El área de especialización del mecánico, definido por el enum {@link Especialidad}. */
    private Especialidad especialidad;
    /** Número de años que el mecánico lleva trabajando profesionalmente. */
    private int añosExperiencia;

    /**
     * Lista de relaciones {@link MecanicoEscuderia} que detallan
     * las escuderías para las que ha trabajado y los períodos.
     */
    private ArrayList<MecanicoEscuderia> mecanicoEscuderias;

    /**
     * Constructor por defecto.
     * Inicializa la lista 'mecanicoEscuderias' como un ArrayList vacío.
     */
    public Mecanico() {
        this.mecanicoEscuderias = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear un Mecanico con todos sus datos.
     *
     * @param dni El documento de identidad del mecánico.
     * @param nombre El nombre de pila del mecánico.
     * @param apellido El apellido del mecánico.
     * @param pais El {@link Pais} de origen del mecánico.
     * @param especialidad La {@link Especialidad} del mecánico (MOTOR, NEUMATICOS, etc.).
     * @param añosExperiencia El número de años de experiencia.
     * @param escuderias Una lista preexistente de relaciones {@link MecanicoEscuderia}.
     */
    public Mecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int añosExperiencia, ArrayList<MecanicoEscuderia> escuderias) {
        super(dni, nombre, apellido, pais); // Llama al constructor de la superclase Persona
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
        this.mecanicoEscuderias = escuderias;
    }

    /**
     * Obtiene la especialidad del mecánico.
     * @return El enum {@link Especialidad} correspondiente.
     */
    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    /**
     * Establece la especialidad del mecánico.
     * @param especialidad El nuevo valor de {@link Especialidad}.
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene los años de experiencia del mecánico.
     * @return El número entero de años de experiencia.
     */
    public int getAñosExperiencia() {
        return this.añosExperiencia;
    }

    /**
     * Establece los años de experiencia del mecánico.
     * @param añosExperiencia El nuevo número de años de experiencia.
     */
    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    /**
     * Obtiene la lista de relaciones contractuales (Mecanico-Escuderia) del mecánico.
     * @return Una lista de {@link MecanicoEscuderia}.
     */
    public ArrayList<MecanicoEscuderia> getMecanicoEscuderias() {
        return this.mecanicoEscuderias;
    }

    /**
     * Reemplaza la lista de relaciones contractuales del mecánico.
     * @param escuderias La nueva lista de {@link MecanicoEscuderia}.
     */
    public void setEscuderia(ArrayList<MecanicoEscuderia> escuderias) {
        this.mecanicoEscuderias = escuderias;
    }

    /**
     * Agrega una nueva relación contractual (Mecanico-Escuderia) al historial del mecánico.
     * @param e El objeto {@link MecanicoEscuderia} a agregar.
     */
    public void agregarMecanicoEscuderia(MecanicoEscuderia e) {
        this.mecanicoEscuderias.add(e);
    }

    /**
     * Sobrescribe el método toString() para mostrar el nombre completo del mecánico.
     * Útil para ComboBoxes y representaciones en UI.
     *
     * @return El nombre y apellido del mecánico como String.
     */
    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellido();
    }
}