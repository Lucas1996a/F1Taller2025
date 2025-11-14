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
 * Controlador principal y clase "cerebro" de la aplicación.
 * Gestiona todas las listas de datos en memoria (pilotos, autos, carreras, etc.),
 * coordina la lógica de negocio (crear, asociar, borrar) y actúa como
 * el único punto de contacto entre la Interfaz de Usuario (GUI) y la
 * capa de Persistencia (GestorPersistencia).
 *
 * @author Lucas (Preservado del original)
 */
public class Gestion {

    // --- LISTAS DE ENTIDADES PRINCIPALES ---
    private ArrayList<Auto> listaAutos;
    private ArrayList<Escuderia> listaEscuderias;
    private ArrayList<Circuito> listaCircuitos;
    private ArrayList<Piloto> listaPilotos;
    private ArrayList<Mecanico> listaMecanicos;
    private ArrayList<Pais> listaPais;
    private ArrayList<Carrera> listaCarreras;
    private ArrayList<ResultadoCarrera> listaResultados;
    
    // --- LISTAS DE ASOCIACIONES (Tablas de Unión) ---
    private ArrayList<PilotoEscuderia> listaPilotoEscuderias;
    private ArrayList<AutoPiloto> listaAutoPilotos;
    private ArrayList<MecanicoEscuderia> listaMecanicoEscuderias;
    
    /** Instancia única del gestor de persistencia para leer/escribir en CSV. */
    GestorPersistencia gestorPersistencia = new GestorPersistencia();
    
    /**
     * Constructor de Gestion.
     * ¡Este es el punto de carga de datos (deserialización) de la aplicación!
     * Llama al GestorPersistencia para cargar todos los archivos CSV en memoria.
     * El orden de carga es CRÍTICO para resolver las dependencias:
     * 1. Entidades base (País).
     * 2. Entidades dependientes (Piloto, Escuderia, Circuito - que necesitan País).
     * 3. Entidades doble-dependientes (Auto - necesita Escudería).
     * 4. Asociaciones (PilotoEscuderia, AutoPiloto - que necesitan Pilotos, Autos, etc.).
     * 5. Resultados (que necesita AutoPiloto y Carrera).
     *
     * Finalmente, re-calcula las estadísticas de los pilotos (victorias, podios)
     * basándose en los resultados cargados.
     */
    public Gestion() {
        
        // --- 1. CARGA DE ENTIDADES BASE (Sin dependencias) ---
        this.listaPais = gestorPersistencia.cargarPaises();
        if (this.listaPais == null) this.listaPais = new ArrayList<>();

        // --- 2. CARGA DE ENTIDADES (Dependen de País) ---
        this.listaPilotos = gestorPersistencia.cargarPilotos(this.listaPais);
        if (this.listaPilotos == null) this.listaPilotos = new ArrayList<>();
        
        this.listaEscuderias = gestorPersistencia.cargarEscuderias(this.listaPais);
        if (this.listaEscuderias == null) this.listaEscuderias = new ArrayList<>();
        
        this.listaCircuitos = gestorPersistencia.cargarCircuitos(this.listaPais);
        if (this.listaCircuitos == null) this.listaCircuitos = new ArrayList<>();
        
        this.listaMecanicos = gestorPersistencia.cargarMecanicos(this.listaPais);
        if (this.listaMecanicos == null) this.listaMecanicos = new ArrayList<>();

        // --- 3. CARGA DE ENTIDADES DEPENDIENTES ---
        this.listaAutos = gestorPersistencia.cargarAutos(this.listaEscuderias);
        if (this.listaAutos == null) this.listaAutos = new ArrayList<>();
        
        this.listaCarreras = gestorPersistencia.cargarCarreras(this.listaCircuitos);
        if (this.listaCarreras == null) this.listaCarreras = new ArrayList<>();

        // --- 4. CARGA DE ASOCIACIONES (Vinculan todo) ---
        this.listaPilotoEscuderias = gestorPersistencia.cargarPilotosEscuderias(
                this.listaPilotos, this.listaEscuderias);
        if (this.listaPilotoEscuderias == null) this.listaPilotoEscuderias = new ArrayList<>();
        
        this.listaMecanicoEscuderias = gestorPersistencia.cargarMecanicosEscuderias(
                this.listaMecanicos, this.listaEscuderias);
        if (this.listaMecanicoEscuderias == null) this.listaMecanicoEscuderias = new ArrayList<>();

        // LÍNEA CRÍTICA: Vincula Pilotos y Autos
        this.listaAutoPilotos = gestorPersistencia.cargarAutoPilotos(
                this.listaPilotos, this.listaAutos);
        if (this.listaAutoPilotos == null) this.listaAutoPilotos = new ArrayList<>();
        
        // --- 5. CARGA DE RESULTADOS (Dependen de AutoPiloto y Carrera) ---
        this.listaResultados = gestorPersistencia.cargarResultadosCarrera(
                this.listaAutoPilotos, this.listaCarreras);
        if (this.listaResultados == null) this.listaResultados = new ArrayList<>();
        
        // --- 6. LOGICA POST-CARGA ---
        // Actualiza las estadísticas en memoria de los pilotos (victorias, podios)
        // basándose en los resultados cargados desde el CSV.
        for (ResultadoCarrera res : this.listaResultados) {
            resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
        }
    }
    
    /**
     * Vuelve a calcular las estadísticas (victorias, podios, etc.) de todos
     * los pilotos basándose en la lista 'listaResultados' cargada en memoria.
     */
    public void recargarResultados() {
        // (Resetea stats si es necesario, aunque el método actual es acumulativo)
        for (ResultadoCarrera res : this.listaResultados) {
            resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
        }
    }
    
    // --- MÉTODOS PARA CREAR OBJETOS (Llamados desde la GUI) ---
    
    /**
     * Crea un nuevo Auto, lo añade a la lista en memoria y lo persiste.
     * @param modelo El nombre del modelo (ej: "W15").
     * @param motor El fabricante del motor (ej: "Mercedes").
     * @param escuderia El objeto {@link Escuderia} al que pertenece.
     */
    public void crearAutos(String modelo, String motor, Escuderia escuderia) {
        Auto nuevoAuto = new Auto();
        nuevoAuto.setModelo(modelo);
        nuevoAuto.setMotor(motor);
        nuevoAuto.setEscuderia(escuderia);
        this.listaAutos.add(nuevoAuto);
        gestorPersistencia.guardarAuto(nuevoAuto);
    }
    
    /**
     * Crea una nueva Escudería, la añade a la lista en memoria y la persiste.
     * @param nombre El nombre de la escudería (ej: "Ferrari").
     * @param pais El objeto {@link Pais} de su sede.
     */
    public void crearEscuderias(String nombre, Pais pais) {
        Escuderia nuevaEsc = new Escuderia();
        nuevaEsc.setNombre(nombre);
        nuevaEsc.setPais(pais);
        this.listaEscuderias.add(nuevaEsc);
        
        gestorPersistencia.guardarEscuderia(nuevaEsc);
    }
    
    /**
     * Crea un nuevo Circuito, lo añade a la lista en memoria y lo persiste.
     * @param nombre El nombre del circuito (ej: "Monza").
     * @param longitud La longitud en metros.
     * @param pais El objeto {@link Pais} donde se ubica.
     */
    public void crearCircuitos(String nombre, int longitud, Pais pais) {
        Circuito nuevo = new Circuito();
        nuevo.setNombre(nombre);
        nuevo.setLongitud(longitud);
        nuevo.setPais(pais);
        this.listaCircuitos.add(nuevo);
        gestorPersistencia.guardarCircuito(nuevo);
    }
    
    /**
     * Crea un nuevo Piloto, lo añade a la lista en memoria y lo persiste.
     * Lanza una excepción si el DNI ya está registrado.
     *
     * @param dni DNI (único).
     * @param nombre Nombre de pila.
     * @param apellido Apellido.
     * @param pais Objeto {@link Pais} de nacionalidad.
     * @param numero Número de competencia.
     * @param victorias Conteo inicial de victorias.
     * @param pole Conteo inicial de poles.
     * @param vueltasRapidas Conteo inicial de vueltas rápidas.
     * @param podios Conteo inicial de podios.
     * @throws Exception Si el DNI ya existe ({@link #buscarPilotoPorDNI}).
     */
    public void crearPilotos(String dni, String nombre, String apellido, Pais pais, int numero, int victorias, int pole, int vueltasRapidas, int podios) throws Exception {
        
        // Validación de duplicados
        if (buscarPilotoPorDNI(dni) != null) {
            throw new Exception("Error: Ya existe un piloto registrado con el DNI " + dni);
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
     * Crea un nuevo Mecánico, lo añade a la lista en memoria y lo persiste.
     * @param dni DNI (único).
     * @param nombre Nombre de pila.
     * @param apellido Apellido.
     * @param pais Objeto {@link Pais} de nacionalidad.
     * @param especialidad El {@link Especialidad} (enum) del mecánico.
     * @param experiencia Años de experiencia.
     */
    public void crearMecanicos(String dni, String nombre, String apellido, Pais pais, Especialidad especialidad, int experiencia) {
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
     * (Método obsoleto/reemplazado) Crea una Carrera.
     * Reemplazado por {@link #planificarCarrera(String, int, String, Circuito)}
     * @param fecha Fecha de la carrera.
     * @param numeroVueltas Total de vueltas.
     * @param hora Hora de inicio.
     * @param pais País anfitrión.
     */
    public void crearCarrera(String fecha, int numeroVueltas, String hora, Pais pais) {
        Carrera nuevo = new Carrera();
        nuevo.setFechaRealizacion(fecha);
        nuevo.setNumeroVueltas(numeroVueltas);
        nuevo.setHoraRealizacion(hora);
        nuevo.setPais(pais);
        listaCarreras.add(nuevo);
        // (Falta persistencia aquí, pero parece ser un método antiguo)
    }
    
    /**
     * Crea un nuevo País, lo añade a la lista en memoria y lo persiste.
     * @param id ID numérico único.
     * @param descrip Nombre del país (ej: "Italia").
     */
    public void crearPais(int id, String descrip) {
        Pais nuevo = new Pais();
        nuevo.setIdPais(id);
        nuevo.setDescripcion(descrip);
        this.listaPais.add(nuevo);
        gestorPersistencia.guardarPais(nuevo);
    }
    
    // --- GETTERS (Usados por la GUI para poblar JComboBoxes) ---
    
    /** @return La lista completa de Países cargados. */
    public ArrayList<Pais> getListaPais() {
        return this.listaPais;
    }
    
    /** @return La lista completa de Escuderías cargadas. */
    public ArrayList<Escuderia> getListaEscuderias() {
        return this.listaEscuderias;
    }
    
    /** @return Un array con todos los valores del enum {@link Especialidad}. */
    public Especialidad[] getListaEspecialidades() {
        return Especialidad.values();
    }
    
    /** @return La lista completa de Circuitos cargados. */
    public ArrayList<Circuito> getListaCircuitos() {
        return this.listaCircuitos;
    }
    
    /** @return La lista completa de Pilotos cargados. */
    public ArrayList<Piloto> getListaPilotos() {
        return this.listaPilotos;
    }
    
    /** @return La lista completa de Autos cargados. */
    public ArrayList<Auto> getListaAutos() {
        return this.listaAutos;
    }
    
    /** @return La lista completa de Mecánicos cargados. */
    public ArrayList<Mecanico> getListaMecanicos() {
        return this.listaMecanicos;
    }
    
    /** @return La lista completa de Carreras cargadas. */
    public ArrayList<Carrera> getListaCarreras() {
        return this.listaCarreras;
    }
    
    /** @return La lista completa de asociaciones {@link AutoPiloto} cargadas. */
    public ArrayList<AutoPiloto> getListaAutoPilotos() {
        return this.listaAutoPilotos;
    }
    
    /** @return La lista completa de {@link ResultadoCarrera} cargados. */
    public ArrayList<ResultadoCarrera> getListaResultados() {
        return this.listaResultados;
    }
    
    /** @return La lista completa de asociaciones {@link PilotoEscuderia} cargadas. */
    public ArrayList<PilotoEscuderia> getListaPilotoEscuderia() {
        return this.listaPilotoEscuderias;
    }
    
    // --- LÓGICA DE GESTIÓN Y BORRADO ---
    
    /**
     * Realiza un **BORRADO EN CASCADA** de una Escudería.
     * Esto implica:
     * 1. Borrar todos los {@link Auto} que le pertenecen.
     * 2. Desvincular todos los contratos {@link PilotoEscuderia}.
     * 3. Desvincular todos los contratos {@link MecanicoEscuderia}.
     * 4. Borrar la {@link Escuderia} misma.
     * 5. Reescribe todos los CSVs afectados (Autos, PilotoEscuderia,
     * MecanicoEscuderia, Escuderias).
     *
     * @param escuderiaABorrar El objeto {@link Escuderia} a eliminar.
     * @throws Exception Si la escudería es nula.
     */
    public void borrarEscuderia(Escuderia escuderiaABorrar) throws Exception {
        
        if (escuderiaABorrar == null) {
            throw new Exception("No se seleccionó ninguna escudería para borrar.");
        }
        
        String nombreEscuderia = escuderiaABorrar.getNombre();
        
        // --- 1. Borrar Autos (1-a-N) ---
        // Usamos removeIf para limpiar la lista principal de autos
        this.listaAutos.removeIf(auto -> auto.getEscuderia().equals(escuderiaABorrar));
        gestorPersistencia.reescribirAutosCSV(this.listaAutos);
        
        // --- 2. Borrar Contratos de Pilotos (N-a-N) ---
        // NO borramos los pilotos, solo los contratos.
        
        // Limpiamos la lista principal de asociaciones
        this.listaPilotoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        // Limpiamos las referencias inversas en cada piloto (para consistencia en memoria)
        for (Piloto p : this.listaPilotos) {
            p.getPilotoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        }
        gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);
        
        // --- 3. Borrar Contratos de Mecánicos (N-a-N) ---
        // NO borramos los mecánicos, solo los enlaces.
        
        // Limpiamos la lista principal de asociaciones
        this.listaMecanicoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        // Limpiamos las referencias inversas en cada mecánico
        for (Mecanico m : this.listaMecanicos) {
            m.getMecanicoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
        }
        gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);
        
        // --- 4. Borrar la Escudería (El último paso) ---
        this.listaEscuderias.remove(escuderiaABorrar);
        gestorPersistencia.reescribirEscuderiasCSV(this.listaEscuderias);
    }
    
    // --- GESTIÓN DE ASOCIACIONES (PILOTO-ESCUDERIA) ---
    
    /**
     * Crea una nueva asociación N-a-N (contrato) entre un Piloto y una Escuderia.
     * Realiza la "doble vinculación" (añade el contrato a la lista del piloto
     * y a la lista de la escudería) y persiste la nueva asociación.
     *
     * @param piloto El {@link Piloto} a asociar.
     * @param escuderia La {@link Escuderia} a asociar.
     * @param fechaInicio La fecha de inicio del contrato (YYYYMMDD).
     * @param fechaFin La fecha de fin del contrato (YYYYMMDD).
     */
    public void gestionarPilotoEscuderia(Piloto piloto, Escuderia escuderia, String fechaInicio, String fechaFin) {
        PilotoEscuderia nuevoContrato = new PilotoEscuderia();
        nuevoContrato.setDesdeFecha(fechaInicio);
        nuevoContrato.setHastaFecha(fechaFin);
        nuevoContrato.setPiloto(piloto);
        nuevoContrato.setEscuderia(escuderia);
        
        // Doble vinculación en memoria
        piloto.agregarPilotoEscuderia(nuevoContrato);
        escuderia.agregarPilotoEscuderia(nuevoContrato);
        this.listaPilotoEscuderias.add(nuevoContrato);
        
        // Persistencia
        gestorPersistencia.guardarPilotoEscuderia(nuevoContrato);
    }
    
    /**
     * Elimina una asociación N-a-N (contrato) existente entre un Piloto y una
     * Escudería.
     * Realiza la "doble desvinculación" (elimina de las listas de ambos
     * objetos)
     * y reescribe el CSV de asociaciones.
     *
     * @param piloto El {@link Piloto} de la asociación.
     * @param escuderia La {@link Escuderia} de la asociación.
     * @throws Exception Si no se encuentra un contrato que coincida.
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
        
        // 2. Borrarla de las listas en memoria (doble desvinculación)
        this.listaPilotoEscuderias.remove(asociacionABorrar);
        piloto.getPilotoEscuderias().remove(asociacionABorrar);
        escuderia.getPilotoEscuderia().remove(asociacionABorrar);
        
        // 3. Reescribir el CSV
        gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);
    }
    
    // --- GESTIÓN DE ASOCIACIONES (AUTO-ESCUDERIA) ---
    
    /**
     * (Método no implementado) Stub para la lógica de reasignación de un
     * Auto a una nueva Escudería.
     * @param auto El auto a reasignar.
     * @param escuderia La nueva escudería.
     */
    public void gestionarAutoEscuderia(Auto auto, Escuderia escuderia) {
        if (auto.getEscuderia() != escuderia) {
            // Lógica para reasignar (actualizar el objeto Auto y reescribir CSV)
        } else {
            // Lógica si ya pertenece (lanzar error, etc.)
        }
    }
    
    /**
     * (Método no utilizado) Elimina un Auto de su escudería y de la lista
     * global.
     * @param autoABorrar El auto a eliminar.
     * @throws Exception Si el auto es nulo.
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
    
    // --- GESTIÓN DE ASOCIACIONES (PILOTO-AUTO) ---
    
    /**
     * Crea una nueva asociación N-a-N entre un Piloto y un Auto.
     * Realiza la "doble vinculación" (añade a listas de piloto y auto) y persiste.
     * @param piloto El {@link Piloto} a asociar.
     * @param auto El {@link Auto} a asociar.
     * @param fechaAsignacion La fecha de la asignación (YYYYMMDD).
     */
    public void gestionarPilotoAuto(Piloto piloto, Auto auto, String fechaAsignacion) {
        AutoPiloto nuevaAsociacion = new AutoPiloto();
        nuevaAsociacion.setPiloto(piloto);
        nuevaAsociacion.setAuto(auto);
        nuevaAsociacion.setFechaAsignacion(fechaAsignacion);
        
        // Doble vinculación en memoria
        piloto.agregarAutoPiloto(nuevaAsociacion);
        auto.agregarAutoPiloto(nuevaAsociacion);
        this.listaAutoPilotos.add(nuevaAsociacion);
        
        // Persistencia
        gestorPersistencia.guardarAutoPiloto(nuevaAsociacion);
    }
    
    // --- GESTIÓN DE ASOCIACIONES (MECANICO-ESCUDERIA) ---
    
    /**
     * Crea una nueva asociación N-a-N (contrato) entre un Mecánico y una
     * Escudería.
     * Realiza la "doble vinculación" y persiste.
     *
     * @param mecanico El {@link Mecanico} a asociar.
     * @param escuderia La {@link Escuderia} a asociar.
     * @param fechaInicio La fecha de inicio del contrato (YYYYMMDD).
     * @param fechaFin La fecha de fin del contrato (YYYYMMDD).
     */
    public void gestionarMecanicoEscuderia(Mecanico mecanico, Escuderia escuderia, String fechaInicio, String fechaFin) {
        MecanicoEscuderia nuevoContrato = new MecanicoEscuderia();
        nuevoContrato.setDesdeFecha(fechaInicio);
        nuevoContrato.setHastaFecha(fechaFin);
        nuevoContrato.setMecanico(mecanico);
        nuevoContrato.setEscuderia(escuderia);
        
        // Doble vinculación
        mecanico.agregarMecanicoEscuderia(nuevoContrato);
        escuderia.agregarMecanicoEscuderia(nuevoContrato);
        this.listaMecanicoEscuderias.add(nuevoContrato);
        
        // Persistencia
        gestorPersistencia.guardarMecanicoEscuderia(nuevoContrato);
    }
    
    /**
     * Elimina una asociación N-a-N (contrato) existente entre un Mecánico y
     * una Escudería.
     * Realiza la "doble desvinculación" y reescribe el CSV.
     *
     * @param mecanico El {@link Mecanico} de la asociación.
     * @param escuderia La {@link Escuderia} de la asociación.
     * @throws Exception Si no se encuentra una asociación que coincida.
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
        
        // 2. Borrarla de las listas en memoria
        this.listaMecanicoEscuderias.remove(asociacionABorrar);
        mecanico.getMecanicoEscuderias().remove(asociacionABorrar);
        escuderia.getMecanicos().remove(asociacionABorrar);
        
        // 3. Reescribir el CSV
        gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);
    }
    
    // --- MÉTODOS PARA CARRERA ---
    
    /**
     * Planifica (crea) una nueva Carrera, la añade a la lista y la persiste.
     * Vincula automáticamente el País basándose en el Circuito seleccionado.
     *
     * @param fecha La fecha de la carrera (YYYYMMDD).
     * @param numeroVueltas El total de vueltas.
     * @param hora La hora de inicio (HHMM).
     * @param circuito El objeto {@link Circuito} donde se correrá.
     */
    public void planificarCarrera(String fecha, int numeroVueltas, String hora, Circuito circuito) {
        Carrera nueva = new Carrera();
        
        nueva.setFechaRealizacion(fecha);
        nueva.setNumeroVueltas(numeroVueltas);
        nueva.setHoraRealizacion(hora);
        nueva.setCircuito(circuito);
        if (circuito != null) {
            nueva.setPais(circuito.getPais()); // Hereda el país del circuito
        }
        listaCarreras.add(nueva);
        gestorPersistencia.guardarCarrera(nueva);
        
    }
    
    /**
     * (Método obsoleto/reemplazado) Registra una participación.
     * Reemplazado por {@link #gestionarPilotoAuto(Piloto, Auto, String)}.
     * @param piloto El piloto.
     * @param auto El auto.
     * @param carrera La carrera.
     */
    public void registrarParticipacionCarrera(Piloto piloto, Auto auto, Carrera carrera) {
        
        // Crear la nueva instancia de la asociación AutoPiloto
        AutoPiloto registroParticipacion = new AutoPiloto();
        
        // Vinculaciones
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
     * Helper utility para calcular los puntos F1 estándar según la posición.
     * @param posicion La posición final (1-20).
     * @return Los puntos (25, 18, 15...) o 0 si está fuera del top 10.
     */
    public int calcularPuntos(int posicion) {
        return switch (posicion) {
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
     * Helper lógico que **actualiza las estadísticas en memoria** de un
     * {@link Piloto} (victorias, podios, vueltas rápidas)
     * basándose en un resultado de carrera.
     * Este método es llamado tanto al cargar los datos como al registrar un
     * nuevo resultado.
     *
     * @param autoPiloto La asociación {@link AutoPiloto} que contiene al piloto.
     * @param posicionFinal La posición obtenida.
     * @param vueltaRapida Si obtuvo (true) o no (false) la vuelta rápida.
     */
    public void resultadosCarreras(AutoPiloto autoPiloto, int posicionFinal, boolean vueltaRapida) {
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
        // (Nota: los puntos no se almacenan en el Piloto, se calculan en
        // informes)
    }
    
    /**
     * Registra el resultado final de un participante en una carrera.
     * 1. Crea el objeto {@link ResultadoCarrera}.
     * 2. Llama a {@link #resultadosCarreras} para actualizar las estadísticas
     * del piloto (victorias, podios, etc.) en memoria.
     * 3. Persiste el nuevo {@link ResultadoCarrera} en el CSV.
     *
     * @param carrera La {@link Carrera} donde se obtuvo el resultado.
     * @param piloto El participante ({@link AutoPiloto}) que obtuvo el resultado.
     * @param posicionFinal La posición final (1-20).
     * @param tiempoFinal El tiempo (formato HHMMSS).
     * @param vueltaRapida Si (true) o no (false) obtuvo la vuelta rápida.
     */
    public void registrarResultadosCarrera(Carrera carrera, AutoPiloto piloto, int posicionFinal, String tiempoFinal, boolean vueltaRapida) {
        ResultadoCarrera nuevoResultado = new ResultadoCarrera();
        
        nuevoResultado.setCarrera(carrera);
        nuevoResultado.setAutoPiloto(piloto);
        nuevoResultado.setPosicionFinal(posicionFinal);
        nuevoResultado.setTiempoFinal(tiempoFinal);
        nuevoResultado.setVueltaRapida(vueltaRapida);
        
        boolean esPodio = (posicionFinal <= 3);
        nuevoResultado.setPodio(esPodio);
        
        // Actualiza las estadísticas del objeto Piloto en memoria
        resultadosCarreras(piloto, posicionFinal, vueltaRapida);
        
        // Guarda el resultado en la lista y en persistencia
        this.listaResultados.add(nuevoResultado);
        gestorPersistencia.guardarResultadoCarrera(nuevoResultado);
    }
    
    /**
     * Calcula el puntaje total acumulado de un piloto específico.
     * Itera sobre *todos* los resultados de carrera registrados, filtra por
     * el piloto, y suma los puntos usando {@link #calcularPuntos(int)}.
     *
     * @param piloto El {@link Piloto} para el cual calcular los puntos.
     * @return El total de puntos acumulados.
     */
    public int calcularPuntosTotalesPiloto(Piloto piloto) {
        
        int totalPuntos = 0;
        
        // 1. Recorre todos los resultados
        for (ResultadoCarrera resultado : this.listaResultados) {
            
            // Obtenemos la asociación
            AutoPiloto asociacion = resultado.getAutoPiloto();
            
            // Obtenemos el Piloto de esa asociación
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
    
    // --- GENERACIÓN DE INFORMES (Llamados desde la GUI) ---
    
    /**
     * Genera un informe de resultados de carreras dentro de un rango de fechas.
     * Filtra las carreras por fecha (YYYYMMDD), busca sus resultados,
     * los ordena por posición y los formatea en un ArrayList de Strings.
     *
     * @param fechaInicio Fecha de inicio (YYYYMMDD).
     * @param fechaFin Fecha de fin (YYYYMMDD).
     * @return Un ArrayList<String> listo para mostrar en un JTextArea.
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

            // Comparamos las fechas (String compareTo funciona para YYYYMMDD)
            if (fechaCarrera.compareTo(fechaInicio) >= 0 && fechaCarrera.compareTo(fechaFin) <= 0) {

                carrerasEncontradas++;
                sb.append("\n--- CARRERA: " + carrera.toString() + " ---\n");

                // Recolectamos todos los resultados de ESTA carrera
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

                    // creamos el informe ordenado y con detalles
                    for (ResultadoCarrera resultado : resultadosDeEstaCarrera) {

                        Piloto piloto = resultado.getAutoPiloto().getPiloto();
                        String nombrePiloto = piloto.getNombre() + " " + piloto.getApellido();
                        int posicion = resultado.getPosicionFinal();

                        String prefijo = "  " + posicion + "°: ";
                        String sufijo = "";

                        // Distinguir ganador y podio
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
        } // Fin del bucle de carreras

        // Verificación
        if (carrerasEncontradas == 0) {
            informe.add("\nNo se encontraron carreras en el rango de fechas especificado.");
        } else {
            //se agregan los resultados
            informe.add(sb.toString());
        }

        return informe;
    }
    
    /**
     * Clase interna privada (Helper) usada para generar el ranking.
     * Almacena un piloto y su puntaje total calculado para poder ordenarlos.
     */
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
     * Genera un informe de Ranking de Pilotos.
     * 1. Itera todos los pilotos en 'listaPilotos'.
     * 2. Calcula los puntos totales para cada uno llamando a
     * {@link #calcularPuntosTotalesPiloto(Piloto)}.
     * 3. Almacena los resultados en una lista temporal de
     * {@link PilotoPuntuacion}.
     * 4. Ordena la lista temporal de mayor a menor puntaje.
     * 5. Formatea la lista ordenada como un ArrayList<String>.
     *
     * @return Un ArrayList<String> (el ranking) listo para mostrar en un
     * JTextArea.
     */
    public ArrayList<String> generarRankingPilotos() {
        ArrayList<String> rankingInforme = new ArrayList<>();
        ArrayList<PilotoPuntuacion> rankingTemporal = new ArrayList<>();

        // 1. Itera sobre CADA piloto
        for (Piloto piloto : this.listaPilotos) {
            int puntosAcumulados = 0;
            
            // 2. Itera sobre CADA resultado para calcular los puntos de ESE piloto
            for (ResultadoCarrera resultado : this.listaResultados) {
                AutoPiloto asociacion = resultado.getAutoPiloto();
                Piloto pilotoDelResultado = asociacion.getPiloto();
                
                if (pilotoDelResultado.equals(piloto)) {
                    int posicion = resultado.getPosicionFinal();
                    int puntosGanados = calcularPuntos(posicion);
                    puntosAcumulados += puntosGanados;
                }
            }
            // 3. Añade al ranking temporal
            rankingTemporal.add(new PilotoPuntuacion(piloto, puntosAcumulados));
        }
        
        // 4. Ordena la lista temporal
        Collections.sort(rankingTemporal, new Comparator<PilotoPuntuacion>() {
            @Override
            public int compare(PilotoPuntuacion pp1, PilotoPuntuacion pp2) {
                // Orden descendente (pp2 vs pp1)
                return Integer.compare(pp2.getPuntosAcumulados(), pp1.getPuntosAcumulados());
            }
        });

        // 5. Formatea la salida
        rankingInforme.add("====================");
        rankingInforme.add("RANKING DE PILOTOS F1 2025");
        rankingInforme.add("====================");
        
        if (rankingTemporal.isEmpty()) {
            rankingInforme.add("No hay pilotos registrados o resultados de carreras para calcular el ranking.");
            return rankingInforme;
        }

        int posicion = 1;
        for (PilotoPuntuacion pp : rankingTemporal) {
            String linea = posicion + ". " + pp.getPiloto().getNombre() + " " + pp.getPiloto().getApellido() + " - Puntos: " + pp.getPuntosAcumulados();
            rankingInforme.add(linea);
            posicion++;
        }
        return rankingInforme;
    }
    
    /**
     * Genera un informe de estadísticas históricas para un único piloto,
     * identificado por su DNI.
     *
     * @param dni El DNI del piloto a buscar.
     * @return Un ArrayList<String> con las estadísticas (Victorias, Podios,
     * Puntos) del piloto.
     */
    public ArrayList<String> generarHistoricoPilotoIndividual(String dni) {
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
        
        // Obtiene las estadísticas almacenadas en el objeto Piloto
        int victorias = piloto.getVictorias();
        int podios = piloto.getPodios();
        int pole = piloto.getPolePosition();
        int vueltasRapidas = piloto.getVueltasRapidas();
        
        // Calcula los puntos totales iterando los resultados
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
     * Genera un informe de estadísticas históricas para **todos** los pilotos
     * registrados.
     * Itera la 'listaPilotos' y para cada uno, calcula sus puntos y formatea
     * sus estadísticas.
     *
     * @return Un ArrayList<String> con el historial de todos los pilotos.
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
            
            // Obtiene estadísticas del objeto
            int victorias = piloto.getVictorias();
            int podios = piloto.getPodios();
            int pole = piloto.getPolePosition();
            int vueltasRapidas = piloto.getVueltasRapidas();
            
            // Llama al método para calcular sus puntos
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
    
    /**
     * Helper utility para buscar un {@link Piloto} en memoria por su DNI.
     * @param dni El DNI a buscar.
     * @return El objeto {@link Piloto} si se encuentra, o {@code null} si no.
     */
    public Piloto buscarPilotoPorDNI(String dni) {
        if (dni == null || dni.isEmpty()) {
            return null;
        }
        
        for (Piloto piloto : this.listaPilotos) {
            if (piloto.getDni().equals(dni)) {
                return piloto;
            }
        }
        return null; // No se encontró
    }
    
    /**
     * Genera un informe de los autos utilizados por una escudería específica
     * en las carreras registradas.
     *
     * @param escuderia La {@link Escuderia} a consultar.
     * @return Un ArrayList<String> detallando qué autos usó, quién los
     * condujo y en qué carrera.
     */
    public ArrayList<String> generarInformeAutosEnCarreras(Escuderia escuderia) {
        
        ArrayList<String> informe = new ArrayList<>();

        // Encabezados del informe
        informe.add("====================");
        informe.add("INFORME DE AUTOS UTILIZADOS");
        informe.add("Escudería: " + escuderia.getNombre());
        informe.add("====================");

        boolean tieneRegistros = false;

        // Usamos una lista nueva para no repetir (evita duplicados si un
        // piloto usó el mismo auto en la misma carrera múltiples veces)
        ArrayList<String> registrosUnicos = new ArrayList<>();

        // Iteramos sobre la lista de Resultados
        for (ResultadoCarrera resultado : this.listaResultados) {

            // Obtenemos los objetos relacionados
            AutoPiloto autoPiloto = resultado.getAutoPiloto();
            Carrera carrera = resultado.getCarrera();

            // Validamos que los datos existan
            if (autoPiloto == null || autoPiloto.getAuto() == null || carrera == null || carrera.getCircuito() == null) {
                continue;
            }

            Auto autoUsado = autoPiloto.getAuto();

            // FILTRO: ¿El auto usado pertenece a la escudería que buscamos?
            if (autoUsado.getEscuderia().equals(escuderia)) {
                
                tieneRegistros = true;
                Piloto piloto = autoPiloto.getPiloto();

                // Creamos una clave única (Modelo|Fecha|DNI) para evitar
                // duplicados
                String clave = autoUsado.getModelo() + "|" + carrera.getFechaRealizacion() + "|" + piloto.getDni();

                if (!registrosUnicos.contains(clave)) {
                    registrosUnicos.add(clave);

                    // Formateamos la línea del informe
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
    
    /**
     * Genera un informe de todos los mecánicos actualmente asociados a una
     * escudería.
     *
     * @param escuderia La {@link Escuderia} a consultar.
     * @return Un ArrayList<String> con los detalles de los mecánicos.
     */
    public ArrayList<String> generarInformeMecanicos(Escuderia escuderia) {
        
        ArrayList<String> informe = new ArrayList<>();
        
        // Encabezados del informe
        informe.add("====================");
        informe.add("INFORME DE MECÁNICOS");
        informe.add("Escudería: " + escuderia.getNombre());
        informe.add("====================");

        boolean tieneMecanicos = false;
        
        // Iteramos sobre las asociaciones Mecanico-Escuderia
        for (MecanicoEscuderia asociacion : this.listaMecanicoEscuderias) {
            
            // Verificamos si la asociación pertenece a la escudería que buscamos
            if (asociacion.getEscuderia().equals(escuderia)) {
                
                tieneMecanicos = true;
                
                // Si coincide, obtenemos el mecánico de la asociación
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
    
    /**
     * Helper utility para buscar un {@link Circuito} en memoria por su
     * nombre (ignorando mayúsculas/minúsculas).
     * @param nombre El nombre a buscar.
     * @return El objeto {@link Circuito} si se encuentra, o {@code null} si no.
     */
    public Circuito buscarCircuitoPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        
        for (Circuito circuito : this.listaCircuitos) {
            
            if (circuito.getNombre().equalsIgnoreCase(nombre)) {
                return circuito;
            }
        }
        return null; // No se encontró
    }
    
    /**
     * Genera un informe que cuenta cuántas veces un piloto específico ha
     * corrido en un circuito específico.
     *
     * @param dniPiloto El DNI del piloto a buscar.
     * @param nombreCircuito El nombre del circuito a buscar.
     * @return Un ArrayList<String> con el resultado del conteo.
     */
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

        // Itera sobre los resultados para contar
        for (ResultadoCarrera resultado : this.listaResultados) {
            Carrera carrera = resultado.getCarrera();

            // Validación de seguridad
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
    
    /**
     * Genera un informe que cuenta cuántas carreras se han planificado en un
     * circuito específico.
     *
     * @param nombreCircuito El nombre del circuito a buscar.
     * @return Un ArrayList<String> con los detalles del circuito y el conteo.
     */
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

        // Contamos las carreras planificadas
        for (Carrera carrera : this.listaCarreras) {
            if (carrera.getCircuito() != null && carrera.getCircuito().equals(circuito)) {
                contador++;
            }
        }

        // Agregamos los resultados al informe
        informe.add("Circuito: " + circuito.getNombre());
        informe.add("País: " + circuito.getPais().getDescripcion());
        informe.add("Longitud: " + circuito.getLongitud() + "km"); // (Nota: el guardado dice 'm', el informe 'km')
        informe.add("--------------------");
        informe.add("Total de carreras planificadas: " + contador);

        return informe;
    }
}