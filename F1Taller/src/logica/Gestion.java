/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import java.util.ArrayList;
import persistencia.GestorPersistencia;
import java.util.Collections;
import java.util.Comparator;
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
    private ArrayList<AutoPiloto> listaAutoPilotos;
    
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
        this.listaCircuitos = gestorPersistencia.cargarCircuitos(this.listaPais);
        this.listaMecanicos = gestorPersistencia.cargarMecanicos(this.listaPais);
        
        
        if (this.listaPais == null) this.listaPais = new ArrayList<>();
        if (this.listaEscuderias == null) this.listaEscuderias = new ArrayList<>();
        if (this.listaPilotos == null)this.listaPilotos = new ArrayList<>();
        if (this.listaAutos == null) this.listaAutos = new ArrayList<>();
        if (this.listaCircuitos == null) this.listaCircuitos = new ArrayList<>();
        if (this.listaMecanicos == null) this.listaMecanicos = new ArrayList<>();
        
        
        this.listaCarreras = new ArrayList<>();
        this.listaPilotoEscuderias = new ArrayList<>();
        this.listaAutoPilotos = new ArrayList<>();
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
        gestorPersistencia.guardarCircuito(nuevo);
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
    
    public void crearMecanicos(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int experiencia){
        Mecanico nuevo = new Mecanico();
        nuevo.setDni(dni);
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setPais(pais);
        nuevo.setEspecialidad(especialidad);
        nuevo.setAñosExperiencia(experiencia);
        listaMecanicos.add(nuevo);
        
        gestorPersistencia.guardarMecanico(nuevo);
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
    
    public Especialidad[] getListaEspecialidades() {
        return Especialidad.values(); 
    }
    
    public void gestionarEscuderias(Piloto piloto, Auto auto, Mecanico mecanico, Escuderia escuderia, String desde, String hasta){
       PilotoEscuderia nuevaAsociacion = new PilotoEscuderia();
       nuevaAsociacion.setPiloto(piloto);
       nuevaAsociacion.setEscuderia(escuderia);
       nuevaAsociacion.setDesdeFecha(desde);
       nuevaAsociacion.setHastaFecha(hasta);
       listaPilotoEscuderias.add(nuevaAsociacion);
       System.out.println("Nueva Asociacion: " + piloto.getNombre() + piloto.getApellido() + " con Escudería " + escuderia.getNombre() + ". Auto a usar: " + auto.getModelo() + ". Mecánico asignado: " + mecanico.getNombre());  
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
     
    
    
    public void asociarPilotoAuto(Piloto piloto, Auto auto, Carrera carrera, String fechaAsignacion) {
        AutoPiloto nuevaAsociacion = new AutoPiloto();
        nuevaAsociacion.setPiloto(piloto);
        nuevaAsociacion.setAuto(auto);
        nuevaAsociacion.setFechaAsignacion(carrera.getFechaRealizacion());;
        this.listaAutoPilotos.add(nuevaAsociacion);
        System.out.println("La Asignacion quedo registrada: " + piloto.getNombre() + " conducirá el " + auto.getModelo() + " desde " + fechaAsignacion + ".");
    }
    
    
    
    public int calcularPuntos(int posicion) {
    return switch(posicion){
        case 1 -> 25;
        case 2 -> 18;
        case 3 -> 15;
        case 4 -> 12;
        case 5 -> 10;
        case 6 -> 8;
        case 7 -> 6;
        case 8 -> 4;
        case 9 -> 2;
        case 10 -> 1;
        default -> 0;
    };
    }
    
    
    public void resultadosCarreras(Piloto piloto, int posicionFinal, boolean vueltaRapida){
        if (posicionFinal == 1) {
        piloto.setVictorias(piloto.getVictorias() + 1);
    }
    
        if (posicionFinal <= 3) {
        piloto.setPodios(piloto.getPodios() + 1);
    }
    
    
        if (vueltaRapida) {
        piloto.setVueltasRapidas(piloto.getVueltasRapidas() + 1);
    }
        
        int puntosObtenidos = calcularPuntos(posicionFinal);
        
        System.out.println(" el Piloto suma " + puntosObtenidos + " puntos.");
   
     }
    
    
      
    public void registrarResultadosCarrera(Carrera carrera, Piloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida){
        ResultadoCarrera nuevoResultado = new ResultadoCarrera();
    
        nuevoResultado.setCarrera(carrera);
        nuevoResultado.setPiloto(piloto);
        nuevoResultado.setPosicionFinal(posicionFinal);
        nuevoResultado.setTiempoFinal(tiempoFinal);
        nuevoResultado.setVueltaRapida(vueltaRapida);

        boolean esPodio = (posicionFinal <= 3);
        nuevoResultado.setPodio(esPodio); 
  
        resultadosCarreras(piloto, posicionFinal, vueltaRapida);

        this.listaResultados.add(nuevoResultado);
        int puntosGanados = calcularPuntos(posicionFinal);
        String podio = "";
        String vr = "";

        if (esPodio) {
           podio = " (Podio!)";
        }

        if (vueltaRapida) {
            vr = " (Vuelta Rápida)";
         }
                      
        System.out.println("El resultado fue: " + piloto.getNombre() + " terminó en posicion" + posicionFinal + ", y sumó " + puntosGanados + " puntos en el GP de " + carrera.getPais().getDescripcion() + "." + podio + vr);    
    }
    
   
    
    // GENERAR INFORMES
    
    
    
    
    
    
    
    public int calcularPuntosTotalesPiloto(Piloto piloto) {
    
        int totalPuntos = 0;
    
        for (ResultadoCarrera resultado : this.listaResultados) {
        
            if (resultado.getPiloto() == piloto) {    
                int posicion = resultado.getPosicionFinal();
                int puntosGanados = calcularPuntos(posicion);
            
                totalPuntos += puntosGanados;
             }
        }
    
        return totalPuntos;
    }
    
    
    private class PilotoPuntuacion {
        Piloto piloto;
        int puntosAcumulados;

        public PilotoPuntuacion(Piloto piloto, int puntosAcumulados) {
        this.piloto = piloto;
        this.puntosAcumulados = puntosAcumulados;
        }
    
        public Piloto getPiloto() { return piloto; }
        public int getPuntosAcumulados() { return puntosAcumulados; }
    }
    
       
   public ArrayList<String> generarRankingPilotos() {
        ArrayList<String> rankingInforme = new ArrayList<>();
        ArrayList<PilotoPuntuacion> rankingTemporal = new ArrayList<>();

        for (Piloto piloto : this.listaPilotos) {
            int puntosAcumulados = 0;
        
            for (ResultadoCarrera resultado : this.listaResultados) {
           
                if (resultado.getPiloto() == piloto) { 
                int posicion = resultado.getPosicionFinal();
                int puntosGanados = calcularPuntos(posicion); 
                puntosAcumulados += puntosGanados;
                }
            }
           rankingTemporal.add(new PilotoPuntuacion(piloto, puntosAcumulados));
        }
    
        Collections.sort(rankingTemporal, new Comparator<PilotoPuntuacion>() {
        @Override
        public int compare(PilotoPuntuacion pp1, PilotoPuntuacion pp2) {
            return Integer.compare(pp2.getPuntosAcumulados(), pp1.getPuntosAcumulados());
        }
        });

        rankingInforme.add("====================");
        rankingInforme.add("RANKING DE PILOTOS F1 2025");
        rankingInforme.add("====================");
    
        if (rankingTemporal.isEmpty()) {
        rankingInforme.add("No hay pilotos registrados o resultados de carreras para calcular el ranking.");
        return rankingInforme;
        }

        int posicion = 1;
        for (PilotoPuntuacion pp : rankingTemporal){
        
            String linea = posicion + ". " + pp.getPiloto().getNombre() + " " + pp.getPiloto().getApellido() + " - Puntos: " + pp.getPuntosAcumulados();

            rankingInforme.add(linea);
            posicion++;
        }
        return rankingInforme;
    }
    
   
   
   
   
   
   public ArrayList<String> generarHistoricoPilotoIndividual(String dni){
    ArrayList<String> informe = new ArrayList<>();
    
    
    Piloto piloto = buscarPilotoPorDNI(dni);
    
    if (piloto == null) {
        informe.add("❌ ERROR: No se encontró ningún piloto con el DNI: " + dni);
        informe.add("=================================================");
        return informe;
    }

    // 2. Generar el informe con las estadísticas acumuladas
    informe.add("=================================================");
    informe.add("👤 HISTORIAL DE ESTADÍSTICAS INDIVIDUAL");
    informe.add("=================================================");
    
    // Obtener todas las estadísticas del objeto Piloto
    int victorias = piloto.getVictorias();
    int podios = piloto.getPodios();
    int pole = piloto.getPolePosition();
    int vueltasRapidas = piloto.getVueltasRapidas();
    
    // Obtener los puntos totales usando tu método "on-the-fly"
    int puntosTotales = calcularPuntosTotalesPiloto(piloto);
    
    informe.add(String.format("Piloto: %s %s (No. %d)",
                              piloto.getNombre(),
                              piloto.getApellido(),
                              piloto.getNumeroCompetencia()));
                              
    informe.add("-------------------------------------------------");
    
    informe.add(String.format("🏆 Victorias: %d", victorias));
    informe.add(String.format("🥉 Podios: %d", podios));
    informe.add(String.format("🏅 Pole Positions: %d", pole));
    informe.add(String.format("💨 Vueltas Rápidas: %d", vueltasRapidas));
    informe.add(String.format("⭐ Puntos Totales Acumulados: %d", puntosTotales));
    
    informe.add("=================================================");
    return informe;
}
   
    
    
    
    
}