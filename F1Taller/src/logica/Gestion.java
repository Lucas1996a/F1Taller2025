/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import java.util.ArrayList;
import persistencia.GestorPersistencia;
/**
 *
 * @author Lucas
 */
public class Gestion {
    private ArrayList<Auto> listaAutos;
    private ArrayList<Escuderia> listaEscuderias;
    private ArrayList<Circuito> listaCircuitos;
    private ArrayList<Piloto> listaPilotos;
    private ArrayList<Mecanico> listaMecanicos;
    private ArrayList<Pais> listaPais;
    private ArrayList<Carrera> listaCarreras;
    private ArrayList<PilotoEscuderia> listaPilotoEscuderias;
    private ArrayList<ResultadoCarrera> listaResultados;
    
    GestorPersistencia gestorPersistencia = new GestorPersistencia();
    
    /*
    public Gestion(ArrayList<Auto> listaAutos, ArrayList<Escuderia> listaEscuderias, ArrayList<Circuito> listaCircuitos, ArrayList<Piloto> listaPilotos, ArrayList<Mecanicos> listaMecanicos, ArrayList<Pais> listaPais){
        this.listaAutos = listaAutos;
        this.listaEscuderias = listaEscuderias;
        this.listaCircuitos = listaCircuitos;
        this.listaPilotos = listaPilotos;
        this.listaMecanicos = listaMecanicos;
        this.listaPais = listaPais;
    }
 */
    
    public Gestion(){
        this.listaPais = gestorPersistencia.cargarPaises();
        this.listaEscuderias = gestorPersistencia.cargarEscuderias(this.listaPais);
        this.listaPilotos = gestorPersistencia.cargarPilotos(this.listaPais);
        this.listaAutos = gestorPersistencia.cargarAutos(this.listaEscuderias);
        
        if (this.listaPais == null) {
            this.listaPais = new ArrayList<>();
        }
        if (this.listaEscuderias == null) {
            this.listaEscuderias = new ArrayList<>();
        }
        if (this.listaPilotos == null) {
            this.listaPilotos = new ArrayList<>();
        }
        if (this.listaAutos == null) this.listaAutos = new ArrayList<>();
        
        this.listaCircuitos = new ArrayList<>();
        this.listaMecanicos = new ArrayList<>();
        this.listaCarreras = new ArrayList<>();
        this.listaPilotoEscuderias = new ArrayList<>();
    }
    
    
    public void crearAutos(String modelo, String motor, Escuderia escuderia){
        Auto nuevoAuto = new Auto();
        nuevoAuto.setModelo(modelo);
        nuevoAuto.setMotor(motor);
        nuevoAuto.setEscuderia(escuderia);
        this.listaAutos.add(nuevoAuto);
        gestorPersistencia.guardarAuto(nuevoAuto);
        System.out.println("Auto Registrado: " + modelo + motor + escuderia);
       
    }
    
    public void crearEscuderias(String nombre, Pais pais){
        Escuderia nuevaEsc = new Escuderia();
        nuevaEsc.setNombre(nombre);
        nuevaEsc.setPais(pais);
        this.listaEscuderias.add(nuevaEsc);
        System.out.println("¡NUEVA ESCUDERÍA REGISTRADA: " + nombre);
        
        gestorPersistencia.guardarEscuderia(nuevaEsc);
    }
    
    
    public void crearCircuitos(String nombre, int longitud, Pais pais){
        Circuito nuevo = new Circuito();
        nuevo.setNombre(nombre);
        nuevo.setLongitud(longitud);
        nuevo.setPais(pais);
        this.listaCircuitos.add(nuevo);
        System.out.println("Circuito registrado: " + nombre);
      
    }
    
    public void crearPilotos(String dni, String nombre, String apellido, Pais pais, int numero, int victorias, int pole, int vueltasRapidas, int podios){
        Piloto nuevo = new Piloto();
        nuevo.setDni(dni);
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setPais(pais);
        nuevo.setNumeroCompetencia(numero);
        nuevo.setVictorias(victorias);
        nuevo.setPolePosition(pole);
        nuevo.setVueltasRapidas(vueltasRapidas);
        nuevo.setPodios(podios);
        
        this.listaPilotos.add(nuevo);
        System.out.println("Piloto registrado: " + nombre);
        
        gestorPersistencia.guardarPiloto(nuevo);
    }
    
    public void crearMecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int experiencia, ArrayList<Escuderia> escuderias){
        Mecanico nuevo = new Mecanico();
        nuevo.setDni(dni);
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setPais(pais);
        nuevo.setEspecialidad(especialidad);
        nuevo.setAñosExperiencia(experiencia);
        nuevo.setEscuderia(escuderias);
        listaMecanicos.add(nuevo);
        System.out.println("Mecánico registrado: " + nombre);
    }
    
    
    public void crearCarrera(String fecha, int numeroVueltas, String hora, Pais pais){
        Carrera nuevo = new Carrera();
        nuevo.setFechaRealizacion(fecha);
        nuevo.setNumeroVueltas(numeroVueltas);
        nuevo.setHoraRealizacion(hora);
        nuevo.setPais(pais);
        listaCarreras.add(nuevo);
        System.out.println("Carrera registrada: " + fecha + pais);      
    }
    
    public void crearPais(int id, String descrip){
        Pais nuevo = new Pais();
        nuevo.setIdPais(id);
        nuevo.setDescripcion(descrip);
        this.listaPais.add(nuevo);
        gestorPersistencia.guardarPais(nuevo);
        System.out.println("Pais registrado: " + id + descrip);   
    }
    
    public ArrayList<Pais> getListaPais() {
        return this.listaPais;
    }
    
    public ArrayList<Escuderia> getListaEscuderias() {
        return this.listaEscuderias;
    }
    
    public void registrarResultadosCarrera(Carrera carrera, Piloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida){
        
    }
    
    public void gestionarEscuderias(Piloto piloto, Auto auto, Mecanico mecanico, Escuderia escuderia, String desde, String hasta){
       PilotoEscuderia nuevaAsociacion = new PilotoEscuderia();
       nuevaAsociacion.setPiloto(piloto);
       nuevaAsociacion.setAuto(auto);
       nuevaAsociacion.setMecanico(mecanico);
       nuevaAsociacion.setEscuderia(escuderia);
       nuevaAsociacion.setDesdeFecha(desde);
       nuevaAsociacion.setHastaFecha(hasta);
       listaPilotoEscuderias.add(nuevaAsociacion);
       System.out.println("Nueva Asociacion: " + piloto.getNombre() + piloto.getApellido() + auto.getEscuderia());  
    
    }
    
    public void planificarCarrera(String fecha, int numeroVueltas, String hora, Circuito circuito){
        Carrera nueva = new Carrera();
    
        nueva.setFechaRealizacion(fecha);
        nueva.setNumeroVueltas(numeroVueltas);
        nueva.setHoraRealizacion(hora);
        nueva.setCircuito(circuito);
        listaCarreras.add(nueva);
        System.out.println("La Carrera quedo planificada para el : Gran Premio de " + circuito.getPais().getDescripcion() + " en el circuito " + circuito.getNombre() + ", el " + fecha + " a las " + hora);
       
    }
}