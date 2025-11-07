package logica;


import java.util.ArrayList;
import java.util.List;

public class Piloto extends Persona {

    private int numeroCompetencia;
    private int victorias;
    private int polePosition;
    private int vueltasRápidas;
    private int podios;

    private List<AutoPiloto> autoPilotos;
    private List<PilotoEscuderia> pilotoEscuderias;

    public Piloto() {
        this.autoPilotos = new ArrayList<>();
        this.pilotoEscuderias = new ArrayList<>();
    }

    public Piloto(String dni, String nombre, String apellido, Pais pais, int numeroCompetencia, int victorias, int polePosition, int vueltasRápidas, int podios, List<AutoPiloto> autoPilotos, List<PilotoEscuderia> pilotoEscuderias) {
        super(dni, nombre, apellido, pais);
        this.numeroCompetencia = numeroCompetencia;
        this.victorias = victorias;
        this.polePosition = polePosition;
        this.vueltasRápidas = vueltasRápidas;
        this.podios = podios;
        this.autoPilotos = autoPilotos;
        this.pilotoEscuderias = pilotoEscuderias;
    }

    public int getNumeroCompetencia() {
        return this.numeroCompetencia;
    }

    public void setNumeroCompetencia(int numeroCompetencia) {
        this.numeroCompetencia = numeroCompetencia;
    }

    public int getVictorias() {
        return this.victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getPolePosition() {
        return this.polePosition;
    }

    public void setPolePosition(int polePosition) {
        this.polePosition = polePosition;
    }

    public int getVueltasRápidas() {
        return this.vueltasRápidas;
    }

    public void setVueltasRapidas(int vueltasRápidas) {
        this.vueltasRápidas = vueltasRápidas;
    }

    public int getPodios() {
        return this.podios;
    }

    public void setPodios(int podios) {
        this.podios = podios;
    }

    public void agregarAutoPiloto(AutoPiloto a) {
        this.autoPilotos.add(a);
    }

    public List<AutoPiloto> getAutoPiloto() {
        return this.autoPilotos;
    }

    public void setAutoPiloto(List<AutoPiloto> autoPilotos) {
        this.autoPilotos = autoPilotos;
    }

    public List<PilotoEscuderia> getPilotoEscuderias() {
        return this.pilotoEscuderias;
    }

    public void setPilotoEscuderias(List<PilotoEscuderia> pilotoEscuderias) {
        this.pilotoEscuderias = pilotoEscuderias;
    }

    public void agregarPilotoEscudria(PilotoEscuderia p) {
        this.pilotoEscuderias.add(p);
    }

}
