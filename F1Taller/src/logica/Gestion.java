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
 * Clase central del sistema F1. Gestiona la carga, persistencia y lógica
 * relacionada con pilotos, autos, escuderías, circuitos, carreras y resultados.
 * 
 * Se encarga de:
 * - Cargar datos desde CSV mediante GestorPersistencia.
 * - Crear entidades y asociaciones.
 * - Registrar carreras y resultados.
 * - Generar informes y rankings.
 *
 *
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
    private ArrayList<MecanicoEscuderia> listaMecanicoEscuderias;
    
    GestorPersistencia gestorPersistencia = new GestorPersistencia();
    
    
    /**
    * Constructor principal. Carga todas las listas necesarias desde los archivos CSV.
    * Ensambla entidades dependientes, asociaciones entre clases y reconstruye
    * estadísticas derivadas de resultados previos.
    */
    public Gestion(){
    
    
    // CARGA DE ENTIDADES QUE VAMOS A USAR
    this.listaPais = gestorPersistencia.cargarPaises();
    if (this.listaPais == null) this.listaPais = new ArrayList<>();
    

    this.listaPilotos = gestorPersistencia.cargarPilotos(this.listaPais);
    if (this.listaPilotos == null) this.listaPilotos = new ArrayList<>();
    

    this.listaEscuderias = gestorPersistencia.cargarEscuderias(this.listaPais);
    if (this.listaEscuderias == null) this.listaEscuderias = new ArrayList<>();
    

    this.listaCircuitos = gestorPersistencia.cargarCircuitos(this.listaPais);
    if (this.listaCircuitos == null) this.listaCircuitos = new ArrayList<>();

    // CARGA DE ENTIDADES DEPENDIENTES 
    
    this.listaAutos = gestorPersistencia.cargarAutos(this.listaEscuderias);
    if (this.listaAutos == null) this.listaAutos = new ArrayList<>();
    
    
    this.listaCarreras = gestorPersistencia.cargarCarreras(this.listaCircuitos);
    if (this.listaCarreras == null) this.listaCarreras = new ArrayList<>();
    
    this.listaMecanicos = gestorPersistencia.cargarMecanicos(this.listaPais);
    if (this.listaMecanicos == null) this.listaMecanicos = new ArrayList<>();

    // CARGA DE ASOCIACIONES 
    
    this.listaPilotoEscuderias = gestorPersistencia.cargarPilotosEscuderias(
        this.listaPilotos, this.listaEscuderias);
    if (this.listaPilotoEscuderias == null) this.listaPilotoEscuderias = new ArrayList<>();
    
    
    this.listaMecanicoEscuderias = gestorPersistencia.cargarMecanicosEscuderias(
        this.listaMecanicos, this.listaEscuderias);
    if (this.listaMecanicoEscuderias == null) this.listaMecanicoEscuderias = new ArrayList<>();

    // LÍNEA CRÍTICA
    this.listaAutoPilotos = gestorPersistencia.cargarAutoPilotos(
        this.listaPilotos, this.listaAutos);
    if (this.listaAutoPilotos == null) this.listaAutoPilotos = new ArrayList<>();
    
    
    // CARGA DE RESULTADOS 
    this.listaResultados = gestorPersistencia.cargarResultadosCarrera(
        this.listaAutoPilotos, this.listaCarreras);
    if (this.listaResultados == null) this.listaResultados = new ArrayList<>();
    
    
    // LOGICA POST CARGA
    for (ResultadoCarrera res : this.listaResultados) {
        resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
    }
}
    
    /**
    * Recalcula las estadísticas de los pilotos basándose en los resultados ya cargados.
    * Útil cuando se recarga la interfaz o se reescriben CSV.
    */
    public void recargarResultados(){
        for (ResultadoCarrera res : this.listaResultados) {
            
            resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
        }
    }
    
    // METODOS PARA CREAR OBJETOS
    
    
    /**
    * Crea un nuevo Auto, lo asocia a una Escudería y lo guarda en persistencia.
    *
    * @param modelo Modelo del auto.
    * @param motor Tipo de motor.
    * @param escuderia Escudería propietaria del auto.
    */
    public void crearAutos(String modelo, String motor, Escuderia escuderia){
        Auto nuevoAuto = new Auto();
        nuevoAuto.setModelo(modelo);
        nuevoAuto.setMotor(motor);
        nuevoAuto.setEscuderia(escuderia);
        this.listaAutos.add(nuevoAuto);
        gestorPersistencia.guardarAuto(nuevoAuto);
    }
    
    
    /**
    * Crea una nueva Escudería y la guarda en persistencia.
    *
    * @param nombre Nombre de la escudería.
    * @param pais País de origen.
    */
    public void crearEscuderias(String nombre, Pais pais){
        Escuderia nuevaEsc = new Escuderia();
        nuevaEsc.setNombre(nombre);
        nuevaEsc.setPais(pais);
        this.listaEscuderias.add(nuevaEsc);
        
        gestorPersistencia.guardarEscuderia(nuevaEsc);
    }
    
    /**
    * Crea un nuevo Circuito y lo guarda en persistencia.
    *
    * @param nombre Nombre del circuito.
    * @param longitud Longitud total del circuito en metros.
    * @param pais País donde se encuentra el circuito.
    */
    public void crearCircuitos(String nombre, int longitud, Pais pais){
        Circuito nuevo = new Circuito();
        nuevo.setNombre(nombre);
        nuevo.setLongitud(longitud);
        nuevo.setPais(pais);
        this.listaCircuitos.add(nuevo);
        gestorPersistencia.guardarCircuito(nuevo);
      
    }
    
    /**
 * Crea un nuevo Piloto siempre que su DNI no exista previamente.
 *
 * @param dni DNI único del piloto.
 * @param nombre Nombre del piloto.
 * @param apellido Apellido del piloto.
 * @param pais País de origen.
 * @param numero Número de competencia.
 * @param victorias Cantidad inicial de victorias.
 * @param pole Poles acumuladas.
 * @param vueltasRapidas Vueltas rápidas logradas.
 * @param podios Podios acumulados.
 * @throws Exception Si ya existe un piloto con el mismo DNI.
 */
    public void crearPilotos(String dni, String nombre, String apellido, Pais pais, int numero, int victorias, int pole, int vueltasRapidas, int podios) throws Exception{
        
        if (buscarPilotoPorDNI(dni) != null) {
            throw new Exception("Error: Ya existe un piloto registrado con  el DNI " + dni);
        }
        
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
        
        gestorPersistencia.guardarPiloto(nuevo);
    }
    
    /**
 * Crea un nuevo Mecánico, lo agrega al sistema y lo guarda en persistencia.
 *
 * @param dni DNI del mecánico.
 * @param nombre Nombre del mecánico.
 * @param apellido Apellido del mecánico.
 * @param pais Nacionalidad.
 * @param especialidad Especialidad técnica del mecánico.
 * @param experiencia Años de experiencia.
 */
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
    }
    
    /**
 * Crea una nueva Carrera y la agrega al sistema.
 *
 * @param fecha Fecha de realización.
 * @param numeroVueltas Cantidad de vueltas programadas.
 * @param hora Hora local.
 * @param pais País anfitrión.
 */
    public void crearCarrera(String fecha, int numeroVueltas, String hora, Pais pais){
        Carrera nuevo = new Carrera();
        nuevo.setFechaRealizacion(fecha);
        nuevo.setNumeroVueltas(numeroVueltas);
        nuevo.setHoraRealizacion(hora);
        nuevo.setPais(pais);
        listaCarreras.add(nuevo);   
        gestorPersistencia.guardarCarrera(nuevo);
    }
    
    
    /**
    * Crea un nuevo País y lo persiste.
    *
    * @param id Identificador único.
    * @param descrip Descripción o nombre del país.
    */
    public void crearPais(int id, String descrip){
        Pais nuevo = new Pais();
        nuevo.setIdPais(id);
        nuevo.setDescripcion(descrip);
        this.listaPais.add(nuevo);
        gestorPersistencia.guardarPais(nuevo); 
    }
    
  //  GETTERS
    
    /**
 * @return Lista completa de <Pais>.
 */
    public ArrayList<Pais> getListaPais() {
        return this.listaPais;
    }
    /**
 * @return Lista completa de <Escuderia>.
 */
    public ArrayList<Escuderia> getListaEscuderias() {
        return this.listaEscuderias;
    }
    /**
    * @return Lista completa de <Especialidad>.
    */
    public Especialidad[] getListaEspecialidades() {
        return Especialidad.values(); 
    }
    /**
 * @return Lista completa de <Circuito>.
 */
    public ArrayList<Circuito> getListaCircuitos() {
    return this.listaCircuitos;
}
    /**
 * @return Lista completa de <Piloto>.
 */
    public ArrayList<Piloto> getListaPilotos() {
        return this.listaPilotos;
    }
    /**
 * @return Lista completa de <Auto>.
 */
    public ArrayList<Auto> getListaAutos() {
        return this.listaAutos;
    }
    /**
 * @return Lista completa de <Mecanico>.
 */
    public ArrayList<Mecanico> getListaMecanicos() {
        return this.listaMecanicos;
    }
    /**
 * @return Lista completa de <Carrera>.
 */
    public ArrayList<Carrera> getListaCarreras(){
        return this.listaCarreras;
    }
    /**
 * @return Lista completa de <AutoPiloto>.
 */
    public ArrayList<AutoPiloto> getListaAutoPilotos() {
        return this.listaAutoPilotos;
    }
    /**
 * @return Lista completa de <ResultadoCarrera>.
 */
    public ArrayList<ResultadoCarrera> getListaResultados(){
         return this.listaResultados;
    }
    /**
 * @return Lista completa de <PilotoEscuderia>.
 */
    public ArrayList<PilotoEscuderia> getListaPilotoEscuderia() {
        return this.listaPilotoEscuderias;
    }
    
    // BORRAR ESCUDERIAS
    
    /**
 * Elimina una Escudería del sistema junto con:
 * - Todos los autos asociados.
 * - Todas las asociaciones Piloto–Escudería.
 * - Todas las asociaciones Mecánico–Escudería.
 * 
 * No elimina pilotos ni mecánicos.
 *
 * @param escuderiaABorrar Escudería a eliminar.
 * @throws Exception Si no se selecciona una escudería válida.
 */
    public void borrarEscuderia(Escuderia escuderiaABorrar) throws Exception {
    
        if (escuderiaABorrar == null) {
            throw new Exception("No se seleccionó ninguna escudería para borrar.");
        }

        String nombreEscuderia = escuderiaABorrar.getNombre();

        // Usamos removeIf para limpiar la lista principal de autos
        this.listaAutos.removeIf(auto -> auto.getEscuderia().equals(escuderiaABorrar));
        // Reescribimos el CSV de Autos (ya sin los autos de esta escudería)
        gestorPersistencia.reescribirAutosCSV(this.listaAutos);

        // NO borramos los pilotos, solo los contratos.

        //  Limpiamos la lista principal de asociaciones
        this.listaPilotoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        // Limpiamos las referencias inversas en cada piloto (para consistencia en memoria)
        for (Piloto p : this.listaPilotos) {
            p.getPilotoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        }
        // Reescribimos el CSV de asociaciones
        gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);

        // Borrar Asociaciones de Mecánicos (Enlaces) 
        // NO borramos los mecánicos, solo los enlaces.

        //  Limpiamos la lista principal de asociaciones
        this.listaMecanicoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        // Limpiamos las referencias inversas en cada mecánico
        for (Mecanico m : this.listaMecanicos) {
            m.getMecanicoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        }
        //  Re-escribimos el CSV de asociaciones
        gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);

        // Borrar la Escudería (El último paso) 
        // Borramos la escudería de la lista principal
        this.listaEscuderias.remove(escuderiaABorrar);
        // Reescribimos el CSV de Escuderías
        gestorPersistencia.reescribirEscuderiasCSV(this.listaEscuderias);
    }
  
    
    // ASOCIAR PILOTOS Y ELIMINAR ASOCIACION  
    
    /**
 * Crea un contrato entre un Piloto y una Escudería con fechas de vigencia.
 *
 * @param piloto Piloto involucrado.
 * @param escuderia Escudería contratante.
 * @param fechaInicio Fecha de inicio del contrato.
 * @param fechaFin Fecha de finalización del contrato.
 */
    public void gestionarPilotoEscuderia(Piloto piloto, Escuderia escuderia, String fechaInicio, String fechaFin) {
        PilotoEscuderia nuevoContrato = new PilotoEscuderia();
        nuevoContrato.setDesdeFecha(fechaInicio);
        nuevoContrato.setHastaFecha(fechaFin);
        nuevoContrato.setPiloto(piloto);
        nuevoContrato.setEscuderia(escuderia);
        
        piloto.agregarPilotoEscuderia(nuevoContrato);
        escuderia.agregarPilotoEscuderia(nuevoContrato);
        this.listaPilotoEscuderias.add(nuevoContrato);
        gestorPersistencia.guardarPilotoEscuderia(nuevoContrato);
    }
    
    
    /**
 * Elimina la asociación entre un Piloto y una Escudería.
 *
 * @param piloto Piloto vinculado.
 * @param escuderia Escudería del contrato.
 * @throws Exception Si la asociación no existe.
 */
    public void darDeBajaPilotoEscuderia(Piloto piloto, Escuderia escuderia) throws Exception {
    
        // 1. Encontrar la asociación a borrar
        PilotoEscuderia asociacionABorrar = null;
        for (PilotoEscuderia pe : this.listaPilotoEscuderias) {
        // Comparamos los objetos
            if (pe.getPiloto().equals(piloto) && pe.getEscuderia().equals(escuderia)) {
                asociacionABorrar = pe;
                break;
            }
        }
    
        if (asociacionABorrar == null) {
            throw new Exception("No se encontró un contrato entre " + piloto.getNombre() + " y " + escuderia.getNombre());
        }
    
        // Borrarla de las listas en memoria (doble vinculación)
        this.listaPilotoEscuderias.remove(asociacionABorrar);
        piloto.getPilotoEscuderias().remove(asociacionABorrar); 
        escuderia.getPilotoEscuderia().remove(asociacionABorrar); 
    
        // Reescribir el CSV
        gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);
    }
    
    
    // ASOCIARLE UN AUTO A LA ESCUDERIA O BORRARLA    
    /**
 * Asocia a una escudería un auto y actualiza persistencia.
 *
 * @param auto Auto a asociar.
 * @param escuderia Escuderia a asociar.
 */
    public void gestionarAutoEscuderia (Auto auto, Escuderia escuderia){
        if (auto.getEscuderia() != escuderia) {
            
        } else {
            
        }
    }
    
    
    /**
 * Elimina un Auto de su escudería y del sistema, y actualiza persistencia.
 *
 * @param autoABorrar Auto a eliminar.
 * @throws Exception Si no se seleccionó un auto válido.
 */
    public void darDeBajaAutoEscuderia(Auto autoABorrar) throws Exception {
        if (autoABorrar == null) {
            throw new Exception("Debe seleccionar un auto.");
        }
    
        // Borrar de la lista en memoria de su escudería 
        if (autoABorrar.getEscuderia() != null) {
            autoABorrar.getEscuderia().getAutos().remove(autoABorrar); 
        }
    
        // Borrar de la lista global de autos
        this.listaAutos.remove(autoABorrar);
    
        // Reescribir el CSV de Autos 
        gestorPersistencia.reescribirAutosCSV(this.listaAutos);
        
    }
    
    // ASOCIAR PILOTOS CON AUTO Y BORRAR LA ASOCIACION
    
    /**
 * Asocia un Piloto a un Auto en una fecha dada.
 *
 * @param piloto Piloto asignado.
 * @param auto Auto a utilizar.
 * @param fechaAsignacion Fecha de asignación.
 */
    public void gestionarPilotoAuto(Piloto piloto, Auto auto, String fechaAsignacion) {
        AutoPiloto nuevaAsociacion = new AutoPiloto();
        nuevaAsociacion.setPiloto(piloto);
        nuevaAsociacion.setAuto(auto);
        nuevaAsociacion.setFechaAsignacion(fechaAsignacion);
        
        piloto.agregarAutoPiloto(nuevaAsociacion);
        auto.agregarAutoPiloto(nuevaAsociacion);
        this.listaAutoPilotos.add(nuevaAsociacion);
        gestorPersistencia.guardarAutoPiloto(nuevaAsociacion);
        
    }
    
    
    // ASOCIAR MECANICOS CON ESCUDERIA Y BORRAR LA ASOCIACION
    
    /**
 * Asigna un Mecánico a una Escudería en un período definido.
 *
 * @param mecanico Mecánico asignado.
 * @param escuderia Escudería correspondiente.
 * @param fechaInicio Fecha de inicio.
 * @param fechaFin Fecha de fin.
 */
    public void gestionarMecanicoEscuderia(Mecanico mecanico, Escuderia escuderia, String fechaInicio, String fechaFin){
        MecanicoEscuderia nuevoContrato = new MecanicoEscuderia();
        nuevoContrato.setDesdeFecha(fechaInicio);
        nuevoContrato.setHastaFecha(fechaFin);
        nuevoContrato.setMecanico(mecanico);
        nuevoContrato.setEscuderia(escuderia);
        
        mecanico.agregarMecanicoEscuderia(nuevoContrato);
        escuderia.agregarMecanicoEscuderia(nuevoContrato);
        
        this.listaMecanicoEscuderias.add(nuevoContrato);
        
        gestorPersistencia.guardarMecanicoEscuderia(nuevoContrato);
        
    } 
  
    /**
 * Elimina una asociación Mecánico–Escudería existente.
 *
 * @param mecanico Mecánico vinculado.
 * @param escuderia Escudería correspondiente.
 * @throws Exception Si la asociación no existe.
 */
    public void darDeBajaMecanicoEscuderia(Mecanico mecanico, Escuderia escuderia) throws Exception {
    
        // 1. Encontrar la asociación a borrar
        MecanicoEscuderia asociacionABorrar = null;
        for (MecanicoEscuderia me : this.listaMecanicoEscuderias) {
            if (me.getMecanico().equals(mecanico) && me.getEscuderia().equals(escuderia)) {
                asociacionABorrar = me;
                break;
            }
        }
    
        if (asociacionABorrar == null) {
            throw new Exception("No se encontró asociación entre " + mecanico.getNombre() + " y " + escuderia.getNombre());
        }
    
        // Borrarla de las listas en memoria 
        this.listaMecanicoEscuderias.remove(asociacionABorrar);
        mecanico.getMecanicoEscuderias().remove(asociacionABorrar); 
        escuderia.getMecanicos().remove(asociacionABorrar); 
    
        //  Reescribir el CSV
        gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);
    }
    
    
    // MÉTODOS PARA CARRERA    
     /**
 * Registra una nueva carrera en un circuito, copiando el país del mismo.
 *
 * @param fecha Fecha del evento.
 * @param numeroVueltas Cantidad de vueltas.
 * @param hora Hora programada.
 * @param circuito Circuito donde se realizará.
 */
    public void planificarCarrera(String fecha, int numeroVueltas, String hora, Circuito circuito){
        Carrera nueva = new Carrera();
    
        nueva.setFechaRealizacion(fecha);
        nueva.setNumeroVueltas(numeroVueltas);
        nueva.setHoraRealizacion(hora);
        nueva.setCircuito(circuito);
        if (circuito != null) {
        nueva.setPais(circuito.getPais()); 
        }
        listaCarreras.add(nueva);
        gestorPersistencia.guardarCarrera(nueva);
       
    }
     
    
    /**
 * Registra que un Piloto con un Auto participa en una Carrera.
 *
 * @param piloto Piloto participante.
 * @param auto Auto utilizado.
 * @param carrera Carrera a disputar.
 */
    public void registrarParticipacionCarrera(Piloto piloto, Auto auto, Carrera carrera) {
      
        // Crear la nueva instancia de la asociación AutoPiloto
        AutoPiloto registroParticipacion = new AutoPiloto();
    
        //  vinculaciones
        registroParticipacion.setPiloto(piloto);
        registroParticipacion.setAuto(auto);
    
        // Vinculamos el registro a la Carrera. 
        ArrayList<Carrera> listaCarrera = new ArrayList<>();
        listaCarrera.add(carrera); 
        registroParticipacion.setCarrera(listaCarrera);
    
        // Reutilizamos la fecha de la carrera 
        registroParticipacion.setFechaAsignacion(carrera.getFechaRealizacion());
    
        // Guardamos la asociación en las listas 
        piloto.agregarAutoPiloto(registroParticipacion);
        auto.agregarAutoPiloto(registroParticipacion);
        this.listaAutoPilotos.add(registroParticipacion);
        gestorPersistencia.guardarAutoPiloto(registroParticipacion);
    }
    
    /**
 * Devuelve los puntos oficiales según la posición final.
 *
 * @param posicion Posición final del piloto.
 * @return Puntos otorgados.
 */
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
    
    /**
 * Actualiza estadísticas del piloto según el resultado obtenido.
 *
 * @param autoPiloto Asociación Auto–Piloto utilizada.
 * @param posicionFinal Posición obtenida.
 * @param vueltaRapida Indica si hizo vuelta rápida.
 */
    public void resultadosCarreras(AutoPiloto autoPiloto, int posicionFinal, boolean vueltaRapida){
        Piloto piloto = autoPiloto.getPiloto();
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
     }
    
    
      /**
 * Crea y almacena un nuevo resultado de carrera.
 *
 * @param carrera Carrera disputada.
 * @param piloto Asociación Auto–Piloto que compite.
 * @param posicionFinal Posición obtenida.
 * @param tiempoFinal Tiempo total registrado.
 * @param vueltaRapida Si obtuvo vuelta rápida.
 */
    public void registrarResultadosCarrera(Carrera carrera, AutoPiloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida){
        ResultadoCarrera nuevoResultado = new ResultadoCarrera();
    
        nuevoResultado.setCarrera(carrera);
        nuevoResultado.setAutoPiloto(piloto);
        nuevoResultado.setPosicionFinal(posicionFinal);
        nuevoResultado.setTiempoFinal(tiempoFinal);
        nuevoResultado.setVueltaRapida(vueltaRapida);

        boolean esPodio = (posicionFinal <= 3);
        nuevoResultado.setPodio(esPodio); 
  
        resultadosCarreras(piloto, posicionFinal, vueltaRapida);

        this.listaResultados.add(nuevoResultado);
        gestorPersistencia.guardarResultadoCarrera(nuevoResultado);
        int puntosGanados = calcularPuntos(posicionFinal);
        
         Piloto pilotoreal = piloto.getPiloto();
    }
    
    
    /**
 * Calcula el total de puntos acumulados por un piloto en todas las carreras.
 *
 * @param piloto Piloto a evaluar.
 * @return Puntos totales del piloto.
 */
    public int calcularPuntosTotalesPiloto(Piloto piloto) {
    
        int totalPuntos = 0;
    
        // 1. Recorre todos los resultados
        for (ResultadoCarrera resultado : this.listaResultados) {
    
     
        // Obtenemos la asociación
            AutoPiloto asociacion = resultado.getAutoPiloto();
        
        //  Obtenemos el Piloto de esa asociación
            Piloto pilotoDelResultado = asociacion.getPiloto();
        
        // Comparamos el piloto
            if (pilotoDelResultado.equals(piloto)) {  
                int posicion = resultado.getPosicionFinal();
                int puntosGanados = calcularPuntos(posicion);
                totalPuntos += puntosGanados;
            }
        }
        return totalPuntos;
    }
    
    
    
    // GENERAR INFORMES
    
    /**
 * Genera un informe de los resultados de todas las carreras disputadas dentro
 * de un intervalo de fechas dado.
 *
 * @param fechaInicio Fecha mínima (incluida).
 * @param fechaFin Fecha máxima (incluida).
 * @return Lista de líneas de texto representando el informe.
 */
    public ArrayList<String> generarInformeResultadosPorFecha(String fechaInicio, String fechaFin) {
        ArrayList<String> informe = new ArrayList<>();

        informe.add("=========================================");
        informe.add(" INFORME DE RESULTADOS DE CARRERAS");
        informe.add(" Período: " + fechaInicio + " al " + fechaFin);
        informe.add("=========================================");

        StringBuilder sb = new StringBuilder();
        int carrerasEncontradas = 0;

        // Recorremos las carreras
        for (Carrera carrera : this.listaCarreras) {

            String fechaCarrera = carrera.getFechaRealizacion();

            // Comparamos las fechas
            if (fechaCarrera.compareTo(fechaInicio) >= 0 && fechaCarrera.compareTo(fechaFin) <= 0) {

                carrerasEncontradas++;
                sb.append("\n--- CARRERA: " + carrera.toString() + " ---\n"); 

                // Recolectamos todos los resultados de una carrera
                ArrayList<ResultadoCarrera> resultadosDeEstaCarrera = new ArrayList<>();
                for (ResultadoCarrera resultado : this.listaResultados) {
                    if (resultado.getCarrera().equals(carrera)) {
                        resultadosDeEstaCarrera.add(resultado);
                    }
                }

                // si se encuentran los resultados los ordenamos por posición
                if (resultadosDeEstaCarrera.isEmpty()) {
                    sb.append("  [Sin resultados registrados para esta carrera]\n");
                } else {

                    // Ordenamos la lista por PosicionFinal (de 1 a 20)
                    Collections.sort(resultadosDeEstaCarrera, new Comparator<ResultadoCarrera>() {
                        @Override
                        public int compare(ResultadoCarrera r1, ResultadoCarrera r2) {
                            return Integer.compare(r1.getPosicionFinal(), r2.getPosicionFinal());
                        }
                    });

                    //  creamos el informe ordenado y con detalles
                    for (ResultadoCarrera resultado : resultadosDeEstaCarrera) {

                        Piloto piloto = resultado.getAutoPiloto().getPiloto();
                        String nombrePiloto = piloto.getNombre() + " " + piloto.getApellido();
                        int posicion = resultado.getPosicionFinal();

                        String prefijo = "  " + posicion + "°: ";
                        String sufijo = "";

                        // podemos distinguir ganador y podio
                        if (posicion == 1) prefijo = "🥇 1°: ";
                        else if (posicion == 2) prefijo = "🥈 2°: ";
                        else if (posicion == 3) prefijo = "🥉 3°: ";

                        // Marcamos la vuelta rápida
                        if (resultado.isVueltaRapida()) sufijo = " (🏁 Vuelta Rápida)";

                        String linea = String.format("%s%s (Tiempo: %s)%s",
                                         prefijo,
                                         nombrePiloto,
                                         resultado.getTiempoFinal(),
                                         sufijo
                                     );
                        sb.append(linea).append("\n");
                    }
                }
               
            }
        } 

        // Verificación 
        if (carrerasEncontradas == 0) {
            informe.add("\nNo se encontraron carreras en el rango de fechas especificado.");
        } else {
            //se agregan los resultados
            informe.add(sb.toString()); 
        }

        return informe;
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
    
       /**
 * Genera el ranking completo de pilotos ordenado por puntos acumulados.
 *
 * @return Texto formateado con posiciones y puntajes.
 */
   public ArrayList<String> generarRankingPilotos() {
        ArrayList<String> rankingInforme = new ArrayList<>();
        ArrayList<PilotoPuntuacion> rankingTemporal = new ArrayList<>();

        for (Piloto piloto : this.listaPilotos) {
            int puntosAcumulados = 0;
        
            for (ResultadoCarrera resultado : this.listaResultados) {
                AutoPiloto asociacion = resultado.getAutoPiloto();
                Piloto pilotoDelResultado = asociacion.getPiloto();
                
                if (pilotoDelResultado.equals(piloto)) { 
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
    
   /**
 * Genera el informe estadístico completo para un piloto específico.
 *
 * @param dni DNI del piloto a consultar.
 * @return Informe de estadísticas individuales.
 */
   public ArrayList<String> generarHistoricoPilotoIndividual(String dni){
        ArrayList<String> informe = new ArrayList<>();
        Piloto piloto = buscarPilotoPorDNI(dni);
    
        if (piloto == null) {
            informe.add("No se encontró ningún piloto con el DNI: " + dni);
            informe.add("=============");
            return informe;
        }

        informe.add("====================================");
        informe.add("HISTORIAL DE ESTADÍSTICAS INDIVIDUAL");
        informe.add("====================================");
   
        int victorias = piloto.getVictorias();
        int podios = piloto.getPodios();
        int pole = piloto.getPolePosition();
        int vueltasRapidas = piloto.getVueltasRapidas();
  
        int puntosTotales = calcularPuntosTotalesPiloto(piloto);
    
        informe.add(String.format("Piloto: %s %s (#%d)",
                piloto.getNombre(),
                piloto.getApellido(),
                piloto.getNumeroCompetencia()));
                              
        informe.add("====================================");
    
        informe.add(String.format(" Victorias: %d", victorias));
        informe.add(String.format(" Podios: %d", podios));
        informe.add(String.format(" Pole Positions: %d", pole));
        informe.add(String.format(" Vueltas Rápidas: %d", vueltasRapidas));
    
        informe.add("====================================");
        informe.add(String.format(" Puntos Totales Acumulados: %d", puntosTotales));
        informe.add("====================================");
        return informe;
    }
   
   /**
 * Genera un informe detallado con estadísticas históricas de todos los pilotos.
 *
 * @return Lista de líneas de informe.
 */
    public ArrayList<String> generarHistoricoTodosPilotos() {
        ArrayList<String> informe = new ArrayList<>();
    
        informe.add("===================");
        informe.add("HISTORIAL DE ESTADÍSTICAS - TODOS LOS PILOTOS");
        informe.add("===================");

        if (this.listaPilotos.isEmpty()) {
            informe.add("No hay pilotos registrados para mostrar.");
            return informe;
        }

        // Recorremos la lista completa de pilotos
        for (Piloto piloto : this.listaPilotos) {
        
            //  estadísticas para cadad piloto
            int victorias = piloto.getVictorias();
            int podios = piloto.getPodios();
            int pole = piloto.getPolePosition();
            int vueltasRapidas = piloto.getVueltasRapidas();
        
            // Llamamos al método para calcular sus puntos
            int puntosTotales = calcularPuntosTotalesPiloto(piloto);
        
            // Agregamos los datos al informe
            informe.add(String.format("\n--- Piloto: %s %s (#%d) ---", 
                piloto.getNombre(), 
                piloto.getApellido(), 
                piloto.getNumeroCompetencia()
            ));
        
        informe.add(String.format("  Victorias: %d", victorias));
        informe.add(String.format("  Podios: %d", podios));
        informe.add(String.format("  Pole Positions: %d", pole));
        informe.add(String.format("  Vueltas Rápidas: %d", vueltasRapidas));
        informe.add(String.format("  Puntos Totales: %d", puntosTotales));
    }
    
    informe.add("\n=================");
    return informe;
}
  
   
   public Piloto buscarPilotoPorDNI(String dni) {
        if (dni == null || dni.isEmpty()) {
        return null;
        }
    
        for (Piloto piloto : this.listaPilotos) {
         if (piloto.getDni().equals(dni)) {
            return piloto;
            }
        }
     return null;
    }
   
    
  
   
   public ArrayList<String> generarInformeAutosEnCarreras(Escuderia escuderia) {
    
        ArrayList<String> informe = new ArrayList<>();

        // Encabezados del informe
        informe.add("====================");
        informe.add("INFORME DE AUTOS UTILIZADOS");
        informe.add("Escudería: " + escuderia.getNombre());
        informe.add("====================");

        boolean tieneRegistros = false;

        // Usamos una lista nueva para no repetir las que tenemos
        ArrayList<String> registrosUnicos = new ArrayList<>();

        // Iteramos sobre la lista de Resultados
        for (ResultadoCarrera resultado : this.listaResultados) {

            // Obtenemos resultado
            AutoPiloto autoPiloto = resultado.getAutoPiloto();
            Carrera carrera = resultado.getCarrera();

            // Validamos exista
            if (autoPiloto == null || autoPiloto.getAuto() == null || carrera == null || carrera.getCircuito() == null) {
                continue;
            }

            Auto autoUsado = autoPiloto.getAuto();

            // filtro para saber si el auto esta en al escuderia
            if (autoUsado.getEscuderia().equals(escuderia)) {

                tieneRegistros = true;
                Piloto piloto = autoPiloto.getPiloto();

                // Creamos una clave para evitar duplicados
                String clave = autoUsado.getModelo() + "|" + carrera.getFechaRealizacion() + "|" + piloto.getDni();

                if (!registrosUnicos.contains(clave)) {
                    registrosUnicos.add(clave);

                    // formetemos la línea del informe
                    String linea = String.format("- Auto: %s (Motor: %s)", autoUsado.getModelo(), autoUsado.getMotor());
                    informe.add(linea);
                    informe.add(String.format("  Usado por: %s %s", piloto.getNombre(), piloto.getApellido()));
                    informe.add(String.format("  En: GP de %s (%s)", carrera.getPais().getDescripcion(), carrera.getFechaRealizacion()));
                    informe.add("--------------------");
                }
            }
        }

        if (!tieneRegistros) {
            informe.add("No hay registros de autos de esta escudería");
            informe.add("utilizados en carreras.");
        }
    
        return informe;
    }
   
   
   
   public ArrayList<String> generarInformeMecanicos(Escuderia escuderia) {
    
     ArrayList<String> informe = new ArrayList<>();
    
        // Encabezados del informe
        informe.add("====================");
        informe.add("INFORME DE MECÁNICOS");
        informe.add("Escudería: " + escuderia.getNombre());
        informe.add("====================");

        boolean tieneMecanicos = false;
    
        // Iteramos sobre las listas
        for (MecanicoEscuderia asociacion : this.listaMecanicoEscuderias) {
        
            // Verificamos si la asociación pertenece a la escudería que buscamos
            if (asociacion.getEscuderia().equals(escuderia)) {
            
                tieneMecanicos = true;
            
                //  en caso que coincida obtenemos el mecánico de la asociasion
                Mecanico mecanico = asociacion.getMecanico();
            
                // Formateamos los datos del mecánico para el informe
                informe.add(String.format("- Mecánico: %s %s", mecanico.getNombre(), mecanico.getApellido()));
                informe.add(String.format("  Especialidad: %s", mecanico.getEspecialidad()));
                informe.add(String.format("  Experiencia: %d años", mecanico.getAñosExperiencia()));
                informe.add("--------------------");
            }
        }

        if (!tieneMecanicos) {
            informe.add("No hay mecánicos asignados a esta escudería.");
        }
    
        return informe;
    }
   
   
   
   
   public Circuito buscarCircuitoPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
         return null;
        }
    
        for (Circuito circuito : this.listaCircuitos) {
            
            if (circuito.getNombre().equalsIgnoreCase(nombre)) {
                return circuito;
            }
        }
        return null;
    }
   
   
   public ArrayList<String> generarInformePilotoEnCircuito(String dniPiloto, String nombreCircuito) {
    
        ArrayList<String> informe = new ArrayList<>();
        informe.add("====================");
        informe.add("PARTICIPACIONES DE PILOTO EN CIRCUITO");
        informe.add("====================");

        Piloto piloto = buscarPilotoPorDNI(dniPiloto);
        Circuito circuito = buscarCircuitoPorNombre(nombreCircuito);
        int contador = 0;

        // Validación de Piloto
        if (piloto == null) {
            informe.add("ERROR: Piloto con DNI " + dniPiloto + " no encontrado.");
            return informe;
        }

        // Validación de Circuito
        if (circuito == null) {
            informe.add("ERROR: Circuito " + nombreCircuito + " no encontrado.");
            return informe;
        }


        for (ResultadoCarrera resultado : this.listaResultados) {
            Carrera carrera = resultado.getCarrera();


            if (carrera == null || carrera.getCircuito() == null || resultado.getAutoPiloto() == null) {
                continue; 
            }

            Piloto pilotoDelResultado = resultado.getAutoPiloto().getPiloto();

            boolean esMismoPiloto = pilotoDelResultado.equals(piloto);
            boolean esMismoCircuito = carrera.getCircuito().equals(circuito);

            if (esMismoPiloto && esMismoCircuito) {
                contador++;
            }
        }

        // Agregamos los resultados finales al informe
        informe.add("Consulta:");
        informe.add("Piloto: " + piloto.getNombre() + " " + piloto.getApellido());
        informe.add("Circuito: " + circuito.getNombre());
        informe.add("--------------------");
        informe.add("Total de participaciones: " + contador);

        return informe;
    }

   
   
    public ArrayList<String> generarInformeCarrerasEnCircuito(String nombreCircuito) {

        ArrayList<String> informe = new ArrayList<>();
        Circuito circuito = buscarCircuitoPorNombre(nombreCircuito);
        int contador = 0;

        informe.add("====================");
        informe.add("CANTIDAD DE CARRERAS POR CIRCUITO");
        informe.add("====================");

        if (circuito == null) {
            informe.add("ERROR: Circuito con nombre '" + nombreCircuito + "' no encontrado.");
            return informe;
        }

        // Contamos las carreras
        for (Carrera carrera : this.listaCarreras) {

            if (carrera.getCircuito() != null && carrera.getCircuito().equals(circuito)) {
                contador++;
            }
        }

        // Agregamos los resultados al informe
        informe.add("Circuito: " + circuito.getNombre());
        informe.add("País: " + circuito.getPais().getDescripcion());
        informe.add("Longitud: " + circuito.getLongitud() + "km");
        informe.add("--------------------");
        informe.add("Total de carreras planificadas: " + contador);

        return informe;
    }  
 }
