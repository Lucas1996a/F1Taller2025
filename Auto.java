
import java.util.ArrayList;
import java.util.List;

public class Auto {

    private String modelo;
    private String motor;

    private Escuderia escuderia;
    private List<AutoPiloto> autoPilotos;

    public Auto() {
        this.autoPilotos = new ArrayList<>();
    }

    public Auto(String modelo, String motor, Escuderia escuderia, List<AutoPiloto> autoPilotos) {
        this.modelo = modelo;
        this.motor = motor;
        this.escuderia = escuderia;
        this.autoPilotos = autoPilotos;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return this.motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Escuderia getEscuderia() {
        return this.escuderia;
    }

    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }

    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    public List<AutoPiloto> getAutoPilotos() {
        return this.autoPilotos;
    }

    public void setAutoPiloto(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }

}
