public class Mecanico extends Persona {

    private Especialidad especialidad; 
    private int añosExperiencia;



    public Mecanico() {
    }


    public Mecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int añosExperiencia) {
        super(dni, nombre, apellido, pais);
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
    }


    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getAñosExperiencia() {
        return this.añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }


    
}
