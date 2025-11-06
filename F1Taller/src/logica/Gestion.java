/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import java.util.ArrayList;

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
        this.listaAutos = new ArrayList<>();
        this.listaEscuderias = new ArrayList<>();
        this.listaCircuitos = new ArrayList<>();
        this.listaPilotos = new ArrayList<>();
        this.listaMecanicos = new ArrayList<>();
        this.listaPais = new ArrayList<>();
    }
    
    
    public void crearAutos(String modelo, String motor){
        Auto nuevoAuto = new Auto(modelo, motor);
        this.listaMecanicos.add(nuevoAuto);
        System.out.println("Auto Registrado: " + modelo + motor);
       
        
        
    }
    
    public void crearEscuderias(String nombre, Pais pais, ArrayList<Piloto> listaPilotos, ArrayList<Mecanico> listaMecanicos, ArrayList<Auto> listaAutos ){
        Escuderia nuevaEscuderia = new Escuderia(nombre, pais, listaPilotos, listaMecanicos, listaAutos);
        this.listaEscuderias.add(nuevaEscuderia);
        System.out.println("¡NUEVA ESCUDERÍA REGISTRADA: " + nombre);
    }
    
    
    public void crearCircuitos(String nombre, int longitud, Pais pais, ArrayList<Carrera> listaCarreras){
        Circuito nuevo = new Circuito(nombre, longitud, pais, listaCarreras);
        this.listaCircuitos(nuevo);
        System.out.println("Circuito registrado: " + nombre);
      
    }
    
    public void crearPilotos(String dni, String nombre, String apellido, Pais pais, int numero, int victorias, int pole, int vueltasRapidas, int podios){
        Piloto nuevo = new Piloto(dni, nombre, apellido, pais, numero, victorias, pole, vueltasRapidas, podios);
        this.listaPilotos(nuevo);
        System.out.println("Piloto registrado: " + nombre);
        
    }
    
    public void crearMecanico(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int experiencia, ArrayList<Escueria> listaEscuderias){
        Mecanico nuevo = new Mecanico(dni, nombre, apellido, pais, especialidad, experiencia, listaEscuderias);
        this.listaMecanicos(nuevo);
        System.out.println("Mecánico registrado: " + nombre);
        
    }
    
    
          
    
    
    
   
      
    
    
}
