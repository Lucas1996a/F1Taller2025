public class PilotoEscuderia {

    private String desdeFecha;
    private String hastaFecha;


    private Piloto piloto;
    private Escuderia escudería;


    public PilotoEscuderia() {
    }



    public PilotoEscuderia(String desdeFecha, String hastaFecha, Piloto piloto, Escuderia escudería) {
        this.desdeFecha = desdeFecha;
        this.hastaFecha = hastaFecha;
        this.piloto = piloto;
        this.escudería = escudería;
    }




    public String getDesdeFecha() {
        return this.desdeFecha;
    }

    public void setDesdeFecha(String desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    public String getHastaFecha() {
        return this.hastaFecha;
    }

    public void setHastaFecha(String hastaFecha) {
        this.hastaFecha = hastaFecha;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Escuderia getEscudería() {
        return this.escudería;
    }

    public void setEscudería(Escuderia escudería) {
        this.escudería = escudería;
    }


    
}
