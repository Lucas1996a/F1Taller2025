public class AutoPiloto {

    private String fechaAsignacion;

    private Carrera carrera; 
    private Piloto piloto;
    private Auto auto;




    public AutoPiloto() {
    }




    public AutoPiloto(String fechaAsignacion, Carrera carrera, Piloto piloto, Auto auto) {
        this.fechaAsignacion = fechaAsignacion;
        this.carrera = carrera;
        this.piloto = piloto;
        this.auto = auto;
    }



    public String getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Auto getAuto() {
        return this.auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }


    
}
