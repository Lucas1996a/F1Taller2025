package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un auto de carreras dentro del sistema.
 * Almacena información sobre el modelo, motor, la escudería a la que pertenece
 * y las relaciones con los pilotos que lo han conducido.
 */
public class Auto {

    private String modelo;
    private String motor;

    private Escuderia escuderia;
    private List<AutoPiloto> autoPilotos;

    /**
     * Constructor por defecto.
     * Inicializa la lista de 'autoPilotos' como un ArrayList vacío.
     */
    public Auto() {
        this.autoPilotos = new ArrayList<>();
    }

    /**
     * Constructor parametrizado para crear una instancia completa de Auto.
     *
     * @param modelo El nombre del modelo del auto (ej: "W15").
     * @param motor El fabricante del motor (ej: "Mercedes").
     * @param escuderia La escudería a la que pertenece este auto.
     * @param autoPilotos Una lista preexistente de relaciones AutoPiloto.
     */
    public Auto(String modelo, String motor, Escuderia escuderia, List<AutoPiloto> autoPilotos) {
        this.modelo = modelo;
        this.motor = motor;
        this.escuderia = escuderia;
        this.autoPilotos = autoPilotos;
    }

    /**
     * Obtiene el modelo del auto.
     * @return El nombre del modelo.
     */
    public String getModelo() {
        return this.modelo;
    }

    /**
     * Establece el modelo del auto.
     * @param modelo El nuevo nombre del modelo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el fabricante del motor del auto.
     * @return El nombre del fabricante del motor.
     */
    public String getMotor() {
        return this.motor;
    }

    /**
     * Establece el fabricante del motor del auto.
     * @param motor El nuevo fabricante del motor.
     */
    public void setMotor(String motor) {
        this.motor = motor;
    }

    /**
     * Obtiene la escudería a la que pertenece el auto.
     * @return El objeto Escuderia asociado.
     */
    public Escuderia getEscuderia() {
        return this.escuderia;
    }

    /**
     * Asigna o cambia la escudería del auto.
     * @param escuderia La nueva escudería a asociar.
     */
    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }

    /**
     * Agrega una relación Auto-Piloto a la lista interna del auto.
     * Esta relación vincula este auto con un piloto específico.
     *
     * @param a La relación AutoPiloto a agregar.
     */
    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    /**
     * Obtiene la lista completa de relaciones Auto-Piloto asociadas a este auto.
     * @return Una lista de objetos AutoPiloto.
     */
    public List<AutoPiloto> getAutoPilotos() {
        return this.autoPilotos;
    }

    /**
     * Reemplaza la lista de relaciones Auto-Piloto existente por una nueva.
     * @param autoPilotos La nueva lista de AutoPiloto.
     */
    public void setAutoPiloto(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }

    /**
     * Sobrescribe el método toString() para una representación más clara del objeto.
     * Es útil para mostrar el auto en componentes UI como ComboBoxes.
     *
     * @return El modelo del auto como String.
     */
    @Override
    public String toString() {
        return this.getModelo();
    }
}