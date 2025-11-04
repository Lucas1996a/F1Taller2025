public class Auto {

    private String modelo;
    private String motor;
   
    private Escuderia escuderia;
    private List<AutoPiloto> autoPiloto = new ArrayList<>();



    public Auto() {
    }


    public Auto(String modelo, String motor, Escuderia escuderia, List<AutoPiloto> autoPiloto) {
        this.modelo = modelo;
        this.motor = motor;
        this.escuderia = new ArrayList<>();
        this.autoPiloto = new ArrayList<>();
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

    public List<AutoPiloto> getAutoPiloto() {
        return this.autoPiloto;
    }

    public void setAutoPiloto(List<AutoPiloto> autoPiloto) {
        this.autoPiloto = autoPiloto;
    }


    
}
