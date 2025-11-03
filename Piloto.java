public class Piloto extends Persona {

    private int numeroCompetencia;
    private int polePosition;
    private int vueltasRápidas;
    private int podios;


    public Piloto() {
    }



    public Piloto(String dn, String nombre, String apellido, Pais pais, int numeroCompetencia, int polePosition, int vueltasRápidas, int podios) {
        super(dn, nombre, apellido, pais);
        this.numeroCompetencia = numeroCompetencia;
        this.polePosition = polePosition;
        this.vueltasRápidas = vueltasRápidas;
        this.podios = podios;
    }



    public int getNumeroCompetencia() {
        return this.numeroCompetencia;
    }

    public void setNumeroCompetencia(int numeroCompetencia) {
        this.numeroCompetencia = numeroCompetencia;
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


    
}
