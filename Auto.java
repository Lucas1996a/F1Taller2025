public class Auto {

    private String modelo;
    private String motor;

    
    private Escuderia escuderia;



    public Auto() {
    }


    public Auto(String modelo, String motor, Escuderia escuderia) {
        this.modelo = modelo;
        this.motor = motor;
        this.motor = motor;
        this.escuderia = escuderia;
    }



    public Auto(String modelo, String motor) {
        this.modelo = modelo;
        this.motor = motor;
    }

    public Escuderia getEscuderia() {
        return this.escuderia;
    }

    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
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

    
}
