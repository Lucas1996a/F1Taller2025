public class Mecanico extends Persona {

    private Especialidad especialidad; 
    private int añosExperiencia;

    private List<Escuderia> escuderia = new ArrayList<>();


    public Mecanico() {
    }



    public Mecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int añosExperiencia, List<Escuderia> escuderia) {
        super(dni, nombre, apellido, pais);
        this.especialidad = especialidad;
        this.añosExperiencia = añosExperiencia;
        this.escuderia = ArrayList<>();
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

    public List<Escuderia> getEscuderia() {
        return this.escuderia;
    }

    public void setEscuderia(List<Escuderia> escuderia) {
        this.escuderia = escuderia;
    }

    

    
}
