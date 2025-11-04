public class Piloto extends Persona {

    private int numeroCompetencia;
    private int victorias;
    private int polePosition;
    private int vueltasRápidas;
    private int podios;


    private List<AutoPiloto> autoPiloto;
    private List<PilotoEscuderia> pilotoEscuderia;


    public Piloto() {
    }


    public Piloto(String dni, String nombre, String apellido, Pais pais, int numeroCompetencia, int victorias, int polePosition, int vueltasRápidas, int podios, List<AutoPiloto> autoPiloto, List<PilotoEscuderia> pilotoEscuderia) {
        super(dni, nombre, apellido, pais);
        this.numeroCompetencia = numeroCompetencia;
        this.victorias = victorias;
        this.polePosition = polePosition;
        this.vueltasRápidas = vueltasRápidas;
        this.podios = podios;
        this.autoPiloto = new ArrayList<>();
        this.pilotoEscuderia = new ArrayList<>();
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

    public void setVueltasRápidas(int vueltasRápidas) {
        this.vueltasRápidas = vueltasRápidas;
    }

    public int getPodios() {
        return this.podios;
    }

    public void setPodios(int podios) {
        this.podios = podios;
    }

    public List<AutoPiloto> getAutoPiloto() {
        return this.autoPiloto;
    }

    public void setAutoPiloto(List<AutoPiloto> autoPiloto) {
        this.autoPiloto = autoPiloto;
    }

    public List<PilotoEscuderia> getPilotoEscuderia() {
        return this.pilotoEscuderia;
    }

    public void setPilotoEscuderia(List<PilotoEscuderia> pilotoEscuderia) {
        this.pilotoEscuderia = pilotoEscuderia;
    }







    
}
