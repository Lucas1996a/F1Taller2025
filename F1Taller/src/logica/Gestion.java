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
    private ArrayList<MecanicoEscuderia> listaMecanicoEscuderias;
    
    GestorPersistencia gestorPersistencia = new GestorPersistencia();
    
    public Gestion(){
    
    
    // --- 1. CARGA DE ENTIDADES BÁSICAS (Nivel 1) ---
    this.listaPais = gestorPersistencia.cargarPaises();
    if (this.listaPais == null) this.listaPais = new ArrayList<>();
    

    this.listaPilotos = gestorPersistencia.cargarPilotos(this.listaPais);
    if (this.listaPilotos == null) this.listaPilotos = new ArrayList<>();
    

    this.listaEscuderias = gestorPersistencia.cargarEscuderias(this.listaPais);
    if (this.listaEscuderias == null) this.listaEscuderias = new ArrayList<>();
    

    this.listaCircuitos = gestorPersistencia.cargarCircuitos(this.listaPais);
    if (this.listaCircuitos == null) this.listaCircuitos = new ArrayList<>();

    // --- 2. CARGA DE ENTIDADES DEPENDIENTES (Nivel 2) ---
    
    this.listaAutos = gestorPersistencia.cargarAutos(this.listaEscuderias);
    if (this.listaAutos == null) this.listaAutos = new ArrayList<>();
    
    
    this.listaCarreras = gestorPersistencia.cargarCarreras(this.listaCircuitos);
    if (this.listaCarreras == null) this.listaCarreras = new ArrayList<>();
    
    this.listaMecanicos = gestorPersistencia.cargarMecanicos(this.listaPais);
    if (this.listaMecanicos == null) this.listaMecanicos = new ArrayList<>();

    // --- 3. CARGA DE ASOCIACIONES (Nivel 3) ---
    
    this.listaPilotoEscuderias = gestorPersistencia.cargarPilotosEscuderias(
        this.listaPilotos, this.listaEscuderias);
    if (this.listaPilotoEscuderias == null) this.listaPilotoEscuderias = new ArrayList<>();
    
    
    this.listaMecanicoEscuderias = gestorPersistencia.cargarMecanicosEscuderias(
        this.listaMecanicos, this.listaEscuderias);
    if (this.listaMecanicoEscuderias == null) this.listaMecanicoEscuderias = new ArrayList<>();

    // --- ¡ESTA ES LA LÍNEA CRÍTICA! ---
    this.listaAutoPilotos = gestorPersistencia.cargarAutoPilotos(
        this.listaPilotos, this.listaAutos);
    if (this.listaAutoPilotos == null) this.listaAutoPilotos = new ArrayList<>();
    
    
    // --- 4. CARGA DE RESULTADOS (Nivel 4) ---
    this.listaResultados = gestorPersistencia.cargarResultadosCarrera(
        this.listaAutoPilotos, this.listaCarreras);
    if (this.listaResultados == null) this.listaResultados = new ArrayList<>();
    
    
    // --- 5. LÓGICA POST-CARGA ---
    for (ResultadoCarrera res : this.listaResultados) {
        resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
    }
}
    
    
    public void recargarResultados(){
        for (ResultadoCarrera res : this.listaResultados) {
        // Re-ejecutamos la lógica de cálculo
        resultadosCarreras(res.getAutoPiloto(), res.getPosicionFinal(), res.isVueltaRapida());
        }
    }
    
//    METODOS PARA CREAR OBJETOS
    
    public void crearAutos(String modelo, String motor, Escuderia escuderia){
        Auto nuevoAuto = new Auto();
        nuevoAuto.setModelo(modelo);
        nuevoAuto.setMotor(motor);
        nuevoAuto.setEscuderia(escuderia);
        this.listaAutos.add(nuevoAuto);
        gestorPersistencia.guardarAuto(nuevoAuto);
    }
    
    public void crearEscuderias(String nombre, Pais pais){
        Escuderia nuevaEsc = new Escuderia();
        nuevaEsc.setNombre(nombre);
        nuevaEsc.setPais(pais);
        this.listaEscuderias.add(nuevaEsc);
        
        gestorPersistencia.guardarEscuderia(nuevaEsc);
    }
    
    
    public void crearCircuitos(String nombre, int longitud, Pais pais){
        Circuito nuevo = new Circuito();
        nuevo.setNombre(nombre);
        nuevo.setLongitud(longitud);
        nuevo.setPais(pais);
        this.listaCircuitos.add(nuevo);
        gestorPersistencia.guardarCircuito(nuevo);
      
    }
    
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
    
    
    public void crearCarrera(String fecha, int numeroVueltas, String hora, Pais pais){
        Carrera nuevo = new Carrera();
        nuevo.setFechaRealizacion(fecha);
        nuevo.setNumeroVueltas(numeroVueltas);
        nuevo.setHoraRealizacion(hora);
        nuevo.setPais(pais);
        listaCarreras.add(nuevo);    
    }
    
    public void crearPais(int id, String descrip){
        Pais nuevo = new Pais();
        nuevo.setIdPais(id);
        nuevo.setDescripcion(descrip);
        this.listaPais.add(nuevo);
        gestorPersistencia.guardarPais(nuevo); 
    }
    
//    GETTERS
    
    public ArrayList<Pais> getListaPais() {
        return this.listaPais;
    }
    
    public ArrayList<Escuderia> getListaEscuderias() {
        return this.listaEscuderias;
    }
    
    public Especialidad[] getListaEspecialidades() {
        return Especialidad.values(); 
    }
    
    public ArrayList<Circuito> getListaCircuitos() {
    return this.listaCircuitos;
}
    
    public ArrayList<Piloto> getListaPilotos() {
        return this.listaPilotos;
    }
    
    public ArrayList<Auto> getListaAutos() {
        return this.listaAutos;
    }
    
    public ArrayList<Mecanico> getListaMecanicos() {
        return this.listaMecanicos;
    }
    
    public ArrayList<Carrera> getListaCarreras(){
        return this.listaCarreras;
    }
    
    public ArrayList<AutoPiloto> getListaAutoPilotos() {
        return this.listaAutoPilotos;
    }
    
    // BORRAR ESCUDERIAS
    
    public void borrarEscuderia(Escuderia escuderiaABorrar) throws Exception {
    
    if (escuderiaABorrar == null) {
        throw new Exception("No se seleccionó ninguna escudería para borrar.");
    }
    
    String nombreEscuderia = escuderiaABorrar.getNombre();
    
    // --- PASO 1: Borrar Autos (Objetos dependientes) ---
    // Usamos removeIf para limpiar la lista principal de autos
    this.listaAutos.removeIf(auto -> auto.getEscuderia().equals(escuderiaABorrar));
    // Re-escribimos el CSV de Autos (ya sin los autos de esta escudería)
    gestorPersistencia.reescribirAutosCSV(this.listaAutos);

    // --- PASO 2: Borrar Asociaciones de Pilotos (Enlaces) ---
    // NO borramos los pilotos, solo los contratos.
    
    // 2a. Limpiamos la lista principal de asociaciones
    this.listaPilotoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
    // 2b. Limpiamos las referencias inversas en cada piloto (para consistencia en memoria)
    for (Piloto p : this.listaPilotos) {
        p.getPilotoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
    }
    // 2c. Re-escribimos el CSV de asociaciones
    gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);

    // --- PASO 3: Borrar Asociaciones de Mecánicos (Enlaces) ---
    // NO borramos los mecánicos, solo los enlaces.

    // 3a. Limpiamos la lista principal de asociaciones
    this.listaMecanicoEscuderias.removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
    // 3b. Limpiamos las referencias inversas en cada mecánico
    for (Mecanico m : this.listaMecanicos) {
        m.getMecanicoEscuderias().removeIf(asoc -> asoc.getEscuderia().equals(escuderiaABorrar));
    }
    // 3c. Re-escribimos el CSV de asociaciones
    gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);

    // --- PASO 4: Borrar la Escudería (El último paso) ---
    // Borramos la escudería de la lista principal
    this.listaEscuderias.remove(escuderiaABorrar);
    // Re-escribimos el CSV de Escuderías
    gestorPersistencia.reescribirEscuderiasCSV(this.listaEscuderias);
}
  
    
    // ASOCIAR PILOTOS Y ELIMINAR ASOCIACION  
    
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
    
    // 2. Borrarla de las listas en memoria (doble vinculación)
        this.listaPilotoEscuderias.remove(asociacionABorrar);
        piloto.getPilotoEscuderias().remove(asociacionABorrar); // (El método de Piloto)
        escuderia.getPilotoEscuderia().remove(asociacionABorrar); // (El método de Escuderia)
    
    // 3. Re-escribir el CSV
        gestorPersistencia.reescribirPilotoEscuderiaCSV(this.listaPilotoEscuderias);
    }
    
    
    // ASOCIARLE UN AUTO A LA ESCUDERIA O BORRARSELO    
    
    public void gestionarAutoEscuderia (Auto auto, Escuderia escuderia){
        if (auto.getEscuderia() != escuderia) {
            
        } else {
            
        }
    }
    
    public void darDeBajaAutoEscuderia(Auto autoABorrar) throws Exception {
        if (autoABorrar == null) {
            throw new Exception("Debe seleccionar un auto.");
        }
    
    // 1. Borrar de la lista en memoria de su escudería (si la tiene)
        if (autoABorrar.getEscuderia() != null) {
            autoABorrar.getEscuderia().getAutos().remove(autoABorrar); // Asumo getAutos() existe
        }
    
    // 2. Borrar de la lista global de autos
        this.listaAutos.remove(autoABorrar);
    
    // 3. Re-escribir el CSV de Autos (¡Importante!)
        gestorPersistencia.reescribirAutosCSV(this.listaAutos);
        
    }
    
    // ASOCIAR PILOTOS CON AUTO Y BORRAR LA ASOCIACION
    
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
    
    // 2. Borrarla de las listas en memoria (doble vinculación)
        this.listaMecanicoEscuderias.remove(asociacionABorrar);
        mecanico.getMecanicoEscuderias().remove(asociacionABorrar); // (El método de Mecanico)
        escuderia.getMecanicos().remove(asociacionABorrar); // (El método de Escuderia)
    
    // 3. Re-escribir el CSV
        gestorPersistencia.reescribirMecanicoEscuderiaCSV(this.listaMecanicoEscuderias);
    }
    
    // MÉTODOS PARA CARRERA    
     
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
     
    
    
    public void registrarParticipacionCarrera(Piloto piloto, Auto auto, Carrera carrera) {
      
        // 1. Crear la nueva instancia de la asociación AutoPiloto
        // Esta instancia representa la participación específica en ESTA carrera.
        AutoPiloto registroParticipacion = new AutoPiloto();
    
        // 2. Establecer las vinculaciones
        registroParticipacion.setPiloto(piloto);
        registroParticipacion.setAuto(auto);
    
        // **CLAVE:** Vinculamos el registro a la Carrera. 
        // Asumimos que la clase AutoPiloto tiene un método setCarrera(Carrera c).
        ArrayList<Carrera> listaCarrera = new ArrayList<>();
        listaCarrera.add(carrera); 
        registroParticipacion.setCarrera(listaCarrera);
    
        // 3. Reutilizamos la fecha de la carrera para marcar la asignación temporal
        registroParticipacion.setFechaAsignacion(carrera.getFechaRealizacion());
    
        // 4. Guardar la asociación en las listas (misma lógica que gestionarPilotoAuto)
        piloto.agregarAutoPiloto(registroParticipacion);
        auto.agregarAutoPiloto(registroParticipacion);
        this.listaAutoPilotos.add(registroParticipacion);
        gestorPersistencia.guardarAutoPiloto(registroParticipacion);
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
    
    
    
    public int calcularPuntosTotalesPiloto(Piloto piloto) {
    
        int totalPuntos = 0;
    
    // 1. Recorre todos los resultados
        for (ResultadoCarrera resultado : this.listaResultados) {
    
        // ¡AQUÍ ESTÁ EL ARREGLO!
        // 2. Obtenemos la asociación (ej: "Hamilton (W15)") del resultado
            AutoPiloto asociacion = resultado.getAutoPiloto();
        
        // 3. Obtenemos el Piloto (ej: "Hamilton") de esa asociación
            Piloto pilotoDelResultado = asociacion.getPiloto();
        
        // 4. Comparamos si es el piloto que estamos buscando
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
 * Genera un informe de resultados detallados de todas las carreras
 * que ocurrieron entre dos fechas, ORDENADOS por posición.
 *
 * @param fechaInicio La fecha de inicio (ej: "2025-01-01")
 * @param fechaFin La fecha de fin (ej: "2025-12-31")
 * @return Un ArrayList de Strings con el informe listo para mostrar.
 */
public ArrayList<String> generarInformeResultadosPorFecha(String fechaInicio, String fechaFin) {
    ArrayList<String> informe = new ArrayList<>();
    
    informe.add("=========================================");
    informe.add(" INFORME DE RESULTADOS DE CARRERAS");
    informe.add(" Período: " + fechaInicio + " al " + fechaFin);
    informe.add("=========================================");

    StringBuilder sb = new StringBuilder();
    int carrerasEncontradas = 0;

    // 1. Recorremos todas las carreras
    for (Carrera carrera : this.listaCarreras) {
        
        String fechaCarrera = carrera.getFechaRealizacion();
        
        // 2. Comparamos las fechas
        if (fechaCarrera.compareTo(fechaInicio) >= 0 && fechaCarrera.compareTo(fechaFin) <= 0) {
            
            carrerasEncontradas++;
            sb.append("\n--- CARRERA: " + carrera.toString() + " ---\n"); // ej: GP de Bahréin (2025-03-05)
            
            // --- INICIO DE LA MEJORA ---
            
            // 3. Recolectamos TODOS los resultados de ESTA carrera
            ArrayList<ResultadoCarrera> resultadosDeEstaCarrera = new ArrayList<>();
            for (ResultadoCarrera resultado : this.listaResultados) {
                if (resultado.getCarrera().equals(carrera)) {
                    resultadosDeEstaCarrera.add(resultado);
                }
            }

            // 4. Si encontramos resultados, los ordenamos por posición
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

                // 5. Ahora sí, creamos el informe ordenado y con detalles
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
                    
                    // Marcar vuelta rápida
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
            // --- FIN DE LA MEJORA ---
        }
    } // Fin del bucle de carreras

    // 6. Verificación final
    if (carrerasEncontradas == 0) {
        informe.add("\nNo se encontraron carreras en el rango de fechas especificado.");
    } else {
        informe.add(sb.toString()); // Agregamos todos los resultados encontrados
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
 * Genera un informe de historial (victorias, podios, etc.) 
 * para TODOS los pilotos registrados.
 * @return Un ArrayList de Strings con el informe completo.
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

        // 1. Recorremos la lista completa de pilotos
        for (Piloto piloto : this.listaPilotos) {
        
            // 2. Para cada piloto, obtenemos sus estadísticas
            int victorias = piloto.getVictorias();
            int podios = piloto.getPodios();
            int pole = piloto.getPolePosition();
            int vueltasRapidas = piloto.getVueltasRapidas();
        
            // 3. Llamamos al método que ya tenés para calcular sus puntos
            int puntosTotales = calcularPuntosTotalesPiloto(piloto);
        
            // 4. Agregamos los datos al informe
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
    
    // Usamos una lista de "claves únicas" para no repetir el mismo auto/carrera/piloto
    ArrayList<String> registrosUnicos = new ArrayList<>();
    
    // Iteramos sobre la lista de RESULTADOS. Es la fuente de datos más fiable.
    for (ResultadoCarrera resultado : this.listaResultados) {
        
        // Obtenemos las partes del resultado
        AutoPiloto autoPiloto = resultado.getAutoPiloto();
        Carrera carrera = resultado.getCarrera();
        
        // Validamos que todo exista
        if (autoPiloto == null || autoPiloto.getAuto() == null || carrera == null || carrera.getCircuito() == null) {
            continue;
        }
        
        Auto autoUsado = autoPiloto.getAuto();
        
        // 1. FILTRAMOS: ¿El auto es de la escudería que buscamos?
        if (autoUsado.getEscuderia().equals(escuderia)) {
            
            tieneRegistros = true;
            Piloto piloto = autoPiloto.getPiloto();
            
            // Creamos una clave para evitar duplicados
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
   
   
   
   public ArrayList<String> generarInformeMecanicos(Escuderia escuderia) {
    
     ArrayList<String> informe = new ArrayList<>();
    
        // Encabezados del informe
        informe.add("====================");
        informe.add("INFORME DE MECÁNICOS");
        informe.add("Escudería: " + escuderia.getNombre());
        informe.add("====================");

        boolean tieneMecanicos = false;
    
        // Iteramos sobre la lista de CONTRATOS/ASOCIACIONES (MecanicoEscuderia)
        // Esta es la forma más eficiente de encontrar la relación
        for (MecanicoEscuderia asociacion : this.listaMecanicoEscuderias) {
        
            // Verificamos si esta asociación pertenece a la escudería que buscamos
            if (asociacion.getEscuderia().equals(escuderia)) {
            
                tieneMecanicos = true;
            
                // Si coincide, obtenemos el mecánico de esa asociación
                Mecanico mecanico = asociacion.getMecanico();
            
                // Formateamos los datos del mecánico para el informe
                informe.add(String.format("- Mecánico: %s %s", mecanico.getNombre(), mecanico.getApellido()));
                informe.add(String.format("  Especialidad: %s", mecanico.getEspecialidad()));
                informe.add(String.format("  Experiencia: %d años", mecanico.getAñosExperiencia()));
                informe.add("--------------------");
            }
        }

        // Si, después de recorrer todo, no encontramos mecánicos para esa escudería
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

    // Validación Piloto
    if (piloto == null) {
        informe.add("ERROR: Piloto con DNI " + dniPiloto + " no encontrado.");
        return informe;
    }
    
    // Validación Circuito
    if (circuito == null) {
        informe.add("ERROR: Circuito " + nombreCircuito + " no encontrado.");
        return informe;
    }

    // Empezamos el conteo
    for (ResultadoCarrera resultado : this.listaResultados) {
        Carrera carrera = resultado.getCarrera();
        
        // Ignoramos resultados sin carrera, sin circuito, o sin piloto asignado
        if (carrera == null || carrera.getCircuito() == null || resultado.getAutoPiloto() == null) {
            continue; 
        }
        
        Piloto pilotoDelResultado = resultado.getAutoPiloto().getPiloto();
        
        // ¡CORRECCIÓN IMPORTANTE! Usamos .equals() para ambos objetos
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

    // Preparamos los encabezados del informe
    informe.add("====================");
    informe.add("CANTIDAD DE CARRERAS POR CIRCUITO");
    informe.add("====================");

    // Validamos si encontramos el circuito
    if (circuito == null) {
        informe.add("ERROR: Circuito con nombre '" + nombreCircuito + "' no encontrado.");
        return informe;
    }

    // Contamos las carreras
    for (Carrera carrera : this.listaCarreras) {
        
        // ¡CORRECCIÓN IMPORTANTE!
        // Usamos .equals() para comparar objetos, no '=='
        // También validamos que el circuito de la carrera no sea null
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
