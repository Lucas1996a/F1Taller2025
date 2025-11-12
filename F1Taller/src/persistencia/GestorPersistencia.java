package persistencia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import logica.*;;

public class GestorPersistencia {
    private static final String ESCUDERIAS_CSV = "src/data/Escuderias.csv";
    private static final String AUTOS_CSV = "src/data/Autos.csv";
    private static final String PILOTOS_CSV = "src/data/Pilotos.csv";
    private static final String CIRCUITOS_CSV = "src/data/Circuitos.csv";
    private static final String PAISES_CSV = "src/data/Paises.csv";
    private static final String MECANICOS_CSV = "src/data/Mecanicos.csv";
    private static final String CARRERAS_CSV = "src/data/Carreras.csv";
    private static final String PILOTOESCUDERIA_CSV = "src/data/PilotoEscuderia.csv";
    private static final String MECANICOESCUDERIA_CSV = "src/data/MecanicoEscuderia.csv";
    private static final String AUTOPILOTO_CSV = "src/data/AutoPiloto.csv";
    private static final String RESULTADOS_CSV = "src/data/ResultadosCarrera.csv";
    
    private static final String SEPARADOR = ";";
    
    public GestorPersistencia() {
        // Constructor (puede estar vacío por ahora)
    }
    
    public void guardarPais(Pais pais) {
        File archivo = new File(PAISES_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true); // 'true' para añadir (append)
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Si el archivo es nuevo, escribimos la cabecera
                bw.write("id_pais" + SEPARADOR + "descripcion");
                bw.newLine();
            }

            // Escribimos la línea de datos
            String linea = pais.getIdPais() + SEPARADOR + pais.getDescripcion();
            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar país en CSV: " + e.getMessage());
        }
    }
    
    /**
     * Guarda un objeto Piloto en el archivo pilotos.csv
     */
    public void guardarPiloto(Piloto piloto) {
    File archivo = new File(PILOTOS_CSV); // Asume "src/data/Pilotos.csv"
    boolean noExiste = !archivo.exists();

    try (FileWriter fw = new FileWriter(archivo, true);
         BufferedWriter bw = new BufferedWriter(fw)) {

        if (noExiste) {
            // Cabecera del archivo
            bw.write("dni" + SEPARADOR + "nombre" + SEPARADOR + "apellido" + SEPARADOR +
                     "pais_descripcion" + SEPARADOR + "numero_competencia" + SEPARADOR +
                     "victorias" + SEPARADOR + "poles" + SEPARADOR +
                     "vueltas_rapidas" + SEPARADOR + "podios");
            bw.newLine();
        }

        // --- VINCULACIÓN ---
        // Obtenemos la descripción (nombre) del país
            String paisDescripcion = piloto.getPais().getDescripcion();

        // Escribimos la línea de datos
            String linea = piloto.getDni() + SEPARADOR +
                       piloto.getNombre() + SEPARADOR +
                       piloto.getApellido() + SEPARADOR +
                       paisDescripcion + SEPARADOR + // Guardamos la descripción
                       piloto.getNumeroCompetencia() + SEPARADOR +
                       piloto.getVictorias() + SEPARADOR +
                       piloto.getPolePosition() + SEPARADOR +
                       piloto.getVueltasRapidas() + SEPARADOR +
                       piloto.getPodios();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar piloto en CSV: " + e.getMessage());
        }
    }
    
    public void guardarEscuderia(Escuderia escuderia) {
        File archivo = new File(ESCUDERIAS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true); // 'true' para añadir (append)
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Si el archivo es nuevo, escribimos la cabecera
                // Asumo que el orden es: id;nombre;id_pais
                bw.write("nombre" + SEPARADOR + "descPais");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            // Guardamos el ID del país, no el objeto entero
            String descPais = escuderia.getPais().getDescripcion();

            // Escribimos la línea de datos
            // Asumo que tienes un getIdEscuderia() en tu clase Escuderia
            String linea = escuderia.getNombre() + SEPARADOR +
                           descPais;
            
            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar escudería en CSV: " + e.getMessage());
            // Opcional: relanzar la excepción para que la GUI la vea
            // throw new RuntimeException("No se pudo guardar la escudería: " + e.getMessage());
        }
    }
    
    public void guardarPilotoEscuderia(PilotoEscuderia asociacion) {
        File archivo = new File(PILOTOESCUDERIA_CSV); //
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("dni_piloto" + SEPARADOR + "nombre_escuderia" + SEPARADOR +
                         "fecha_desde" + SEPARADOR + "fecha_hasta");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            String dniPiloto = asociacion.getPiloto().getDni();
            String nombreEscuderia = asociacion.getEscuderia().getNombre();

            // Escribimos la línea de datos
            String linea = dniPiloto + SEPARADOR +
                           nombreEscuderia + SEPARADOR +
                           asociacion.getDesdeFecha() + SEPARADOR +
                           asociacion.getHastaFecha();
            
            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar la asociación PilotoEscuderia: " + e.getMessage());
        }
    }
    
    public void guardarAuto(Auto auto) {
        File archivo = new File(AUTOS_CSV); // Asume "src/data/Autos.csv"
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true); // 'true' para añadir
            BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Cabecera del archivo
                bw.write("modelo" + SEPARADOR + "motor" + SEPARADOR + "nombre_escuderia");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            // Obtenemos el nombre de la escudería
            String nombreEscuderia = auto.getEscuderia().getNombre();

            // Escribimos la línea de datos
            String linea = auto.getModelo() + SEPARADOR +
                       auto.getMotor() + SEPARADOR +
                       nombreEscuderia;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar auto en CSV: " + e.getMessage());
        }
    }
    
    public void guardarAutoPiloto(AutoPiloto asociacion) {
    File archivo = new File(AUTOPILOTO_CSV);
    boolean noExiste = !archivo.exists();

    try (FileWriter fw = new FileWriter(archivo, true); // 'true' para añadir
         BufferedWriter bw = new BufferedWriter(fw)) {

        if (noExiste) {
            // Cabecera del archivo
            bw.write("dni_piloto" + SEPARADOR + "modelo_auto" + SEPARADOR + "fecha_asignacion");
            bw.newLine();
        }

        // --- VINCULACIÓN ---
        String dniPiloto = asociacion.getPiloto().getDni();
        String modeloAuto = asociacion.getAuto().getModelo();
        String fecha = asociacion.getFechaAsignacion();

        // Escribimos la línea de datos
        String linea = dniPiloto + SEPARADOR +
                       modeloAuto + SEPARADOR +
                       fecha;

        bw.write(linea);
        bw.newLine();

    } catch (IOException e) {
        System.err.println("Error al guardar la asociación AutoPiloto: " + e.getMessage());
        }
    }
    
    public void guardarCircuito(Circuito circuito) {
        File archivo = new File(CIRCUITOS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Cabecera del archivo
                bw.write("nombre" + SEPARADOR + "longitud" + SEPARADOR + "pais_descripcion");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            // Obtenemos la descripción (nombre) del país
            String paisDescripcion = circuito.getPais().getDescripcion();

            // Escribimos la línea de datos
            String linea = circuito.getNombre() + SEPARADOR +
                           circuito.getLongitud() + SEPARADOR +
                           paisDescripcion; // Guardamos el nombre del país
            
            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar circuito en CSV: " + e.getMessage());
        }
    }
    
    public void guardarMecanico(Mecanico mecanico) {
    File archivo = new File(MECANICOS_CSV);
    boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("dni" + SEPARADOR + "nombre" + SEPARADOR + "apellido" + SEPARADOR +
                     "pais_descripcion" + SEPARADOR + "especialidad" + SEPARADOR + "años_experiencia");
                bw.newLine();
            }

            String paisDescripcion = mecanico.getPais().getDescripcion();

        // --- ¡CAMBIO IMPORTANTE! ---
        // Convertimos el enum a su nombre en String
            String especialidadStr = mecanico.getEspecialidad().name(); 

            String linea = mecanico.getDni() + SEPARADOR +
                       mecanico.getNombre() + SEPARADOR +
                       mecanico.getApellido() + SEPARADOR +
                       paisDescripcion + SEPARADOR +
                       especialidadStr + SEPARADOR + // Guardamos el String
                       mecanico.getAñosExperiencia();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar mecánico en CSV: " + e.getMessage());
        }
    }
    
    public void guardarMecanicoEscuderia(MecanicoEscuderia asociacion) {
        
        File archivo = new File(MECANICOESCUDERIA_CSV);
        boolean noExiste = !archivo.exists();

        // Usamos 'true' para añadir al final (append)
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Si el archivo es nuevo, escribimos la cabecera (solo 2 columnas)
                bw.write("dni_mecanico" + SEPARADOR + "nombre_escuderia");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            String dniMecanico = asociacion.getMecanico().getDni();
            String nombreEscuderia = asociacion.getEscuderia().getNombre();
            String desde = asociacion.getDesdeFecha();
            String hasta = asociacion.getHastaFecha();

            // Escribimos la línea de datos
            String linea = dniMecanico + SEPARADOR + nombreEscuderia + SEPARADOR + desde + SEPARADOR + hasta;
            
            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar la asociación MecanicoEscuderia: " + e.getMessage());
        }
    }
    
    public void guardarCarrera(Carrera carrera) {
    File archivo = new File(CARRERAS_CSV);
    boolean noExiste = !archivo.exists();

    try (FileWriter fw = new FileWriter(archivo, true);
         BufferedWriter bw = new BufferedWriter(fw)) {

        if (noExiste) {
            // Cabecera del archivo
            bw.write("fecha" + SEPARADOR + "hora" + SEPARADOR + "numero_vueltas" + SEPARADOR + "nombre_circuito");
            bw.newLine();
        }

        // --- VINCULACIÓN ---
        String nombreCircuito = carrera.getCircuito().getNombre();

        // Escribimos la línea de datos
        String linea = carrera.getFechaRealizacion() + SEPARADOR +
                       carrera.getHoraRealizacion() + SEPARADOR +
                       carrera.getNumeroVueltas() + SEPARADOR +
                       nombreCircuito;

        bw.write(linea);
        bw.newLine();

    } catch (IOException e) {
        System.err.println("Error al guardar carrera en CSV: " + e.getMessage());
        }
    }
    
    public void guardarResultadoCarrera(ResultadoCarrera resultado) {
    File archivo = new File(RESULTADOS_CSV);
    boolean noExiste = !archivo.exists();

    try (FileWriter fw = new FileWriter(archivo, true);
         BufferedWriter bw = new BufferedWriter(fw)) {

        if (noExiste) {
            // Cabecera del archivo
            bw.write("dni_piloto" + SEPARADOR + "modelo_auto" + SEPARADOR + "id_carrera" +
                     SEPARADOR + "posicion_final" + SEPARADOR + "tiempo_final" + SEPARADOR + "vuelta_rapida");
            bw.newLine();
        }

        // --- VINCULACIÓN ---
        // Sacamos las claves de los objetos
        String apellidoPiloto = resultado.getAutoPiloto().getPiloto().getApellido();
        String modeloAuto = resultado.getAutoPiloto().getAuto().getModelo();
        // Usamos la fecha de la carrera como su ID único
        String idCarrera = resultado.getCarrera().getFechaRealizacion(); 

        // Escribimos la línea de datos
        String linea = apellidoPiloto + SEPARADOR +
                       modeloAuto + SEPARADOR +
                       idCarrera + SEPARADOR +
                       resultado.getPosicionFinal() + SEPARADOR +
                       resultado.getTiempoFinal() + SEPARADOR +
                       resultado.isVueltaRapida();

        bw.write(linea);
        bw.newLine();

    } catch (IOException e) {
        System.err.println("Error al guardar el ResultadoCarrera: " + e.getMessage());
    }
    }
    
    
    
    // --- MÉTODOS DE CARGA ---

    /**
     * Lee paises.csv y devuelve un ArrayList de objetos Pais
     */
    public ArrayList<Pais> cargarPaises() {
        ArrayList<Pais> paises = new ArrayList<>();
        File archivo = new File(PAISES_CSV);

        if (!archivo.exists()) {
            return paises; // Si no hay archivo, devuelve lista vacía
        }
        
        // Usamos try-with-resources para el lector
        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            String linea = br.readLine(); // Leemos la cabecera para saltearla

            while ((linea = br.readLine()) != null) { // Leemos línea por línea
                String[] datos = linea.split(SEPARADOR);
                
                if (datos.length == 2) {
                    int id = Integer.parseInt(datos[0]);
                    String descripcion = datos[1];
                    
                    Pais p = new Pais();
                    p.setIdPais(id);
                    p.setDescripcion(descripcion);
                    paises.add(p);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar países desde CSV: " + e.getMessage());
        }
        return paises;
    }
    
    /**
     * Lee pilotos.csv y devuelve un ArrayList de objetos Piloto
     * @param listaDePaises La lista de países ya cargada para re-vincularlos
     */
    public ArrayList<Piloto> cargarPilotos(ArrayList<Pais> listaDePaises) {
    ArrayList<Piloto> pilotos = new ArrayList<>();
    File archivo = new File(PILOTOS_CSV);

    if (!archivo.exists()) {
        System.err.println("No se encontró el archivo: " + PILOTOS_CSV);
        return pilotos; // Lista vacía
    }

    try (FileReader fr = new FileReader(archivo);
         BufferedReader br = new BufferedReader(fr)) {

        br.readLine(); // Salteamos cabecera

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(SEPARADOR);

            if (datos.length >= 9) {
                try {
                    String dni = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String paisDescripcion = datos[3]; // Leemos la descripción
                    int numeroComp = Integer.parseInt(datos[4]);
                    int victorias = Integer.parseInt(datos[5]);
                    int poles = Integer.parseInt(datos[6]);
                    int fastLap = Integer.parseInt(datos[7]);
                    int podios = Integer.parseInt(datos[8]);

                    // --- VINCULACIÓN ---
                    // Buscamos el objeto Pais que corresponde a esa DESCRIPCIÓN
                    Pais paisDelPiloto = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) {
                            paisDelPiloto = pais;
                            break;
                        }
                    }

                    if (paisDelPiloto != null) {
                        Piloto p = new Piloto();
                        p.setDni(dni);
                        p.setNombre(nombre);
                        p.setApellido(apellido);
                        p.setPais(paisDelPiloto);
                        p.setNumeroCompetencia(numeroComp);
                        p.setVictorias(victorias);
                        p.setPolePosition(poles);
                        p.setVueltasRapidas(fastLap);
                        p.setPodios(podios);
                        pilotos.add(p);
                    } else {
                        System.err.println("No se encontró país '" + paisDescripcion + "' para el piloto " + nombre);
                    }
                } catch (Exception e) {
                     System.err.println("Error parseando línea de piloto: " + linea + " | Error: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
        System.err.println("Error al cargar pilotos desde CSV: " + e.getMessage());
        }
        return pilotos;
    }
    
    public ArrayList<Escuderia> cargarEscuderias(ArrayList<Pais> listaDePaises) {
        ArrayList<Escuderia> escuderias = new ArrayList<>();
        File archivo = new File(ESCUDERIAS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + ESCUDERIAS_CSV);
            return escuderias; // Lista vacía
        }

        try (FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salteamos cabecera (Asumimos: nombre;id_pais)

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 2) { 
                
                    String nombreEscuderia = datos[0];
                    String paisDescripcion = datos[1];
                
                // --- VINCULACIÓN ---
                // Buscamos el objeto Pais que corresponde a ese ID
                    Pais paisDeLaEscuderia = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) { 
                            paisDeLaEscuderia = pais;
                            break;
                        }
                    }
                
                    // Creamos la escudería y la añadimos
                    if (paisDeLaEscuderia != null) {
                        Escuderia esc = new Escuderia();
                        esc.setNombre(nombreEscuderia);
                        esc.setPais(paisDeLaEscuderia);
                        escuderias.add(esc);
                    } else {
                        System.err.println("No se encontró país con descripción '" + paisDescripcion + "' para la escudería " + nombreEscuderia);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar escuderías desde CSV: " + e.getMessage());
    }
    return escuderias; 
    }
    
    public ArrayList<PilotoEscuderia> cargarPilotosEscuderias(
            ArrayList<Piloto> listaPilotos, ArrayList<Escuderia> listaEscuderias) {
        
        ArrayList<PilotoEscuderia> asociaciones = new ArrayList<>();
        File archivo = new File(PILOTOESCUDERIA_CSV);

        if (!archivo.exists()) {
            return asociaciones; 
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); 

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length < 4) continue; 

                String dniPiloto = datos[0];
                String nombreEscuderia = datos[1];
                String fechaDesde = datos[2];
                String fechaHasta = datos[3];

                // --- VINCULACIÓN ---
                Piloto piloto = listaPilotos.stream().filter(p -> p.getDni().equals(dniPiloto)).findFirst().orElse(null);
                Escuderia escuderia = listaEscuderias.stream().filter(e -> e.getNombre().equalsIgnoreCase(nombreEscuderia)).findFirst().orElse(null);

                if (piloto != null && escuderia != null) {
                    PilotoEscuderia pe = new PilotoEscuderia();
                    pe.setPiloto(piloto);
                    pe.setEscuderia(escuderia);
                    pe.setDesdeFecha(fechaDesde);
                    pe.setHastaFecha(fechaHasta);
                    asociaciones.add(pe);
                } else {
                    System.err.println("Error al vincular asociación (Piloto/Escudería no encontrados): " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar PilotosEscuderias: " + e.getMessage());
        }
        return asociaciones;
    }
    
    public ArrayList<Auto> cargarAutos(ArrayList<Escuderia> listaDeEscuderias) {
        ArrayList<Auto> autos = new ArrayList<>();
        File archivo = new File(AUTOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + AUTOS_CSV);
            return autos; 
        }

        try (FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); 

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 3) {

                    String modelo = datos[0];
                    String motor = datos[1];
                    String nombreEscuderia = datos[2];

                    // --- VINCULACIÓN ---
                    // Buscamos el objeto Escuderia que coincide con ese nombre
                    Escuderia escuderiaDelAuto = null;
                    for (Escuderia esc : listaDeEscuderias) {
                        if (esc.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                            escuderiaDelAuto = esc;
                            break;
                        }
                    }

                    if (escuderiaDelAuto != null) {
                        Auto auto = new Auto();
                        auto.setModelo(modelo);
                        auto.setMotor(motor);
                        auto.setEscuderia(escuderiaDelAuto);
                        autos.add(auto);
                    } else {
                        System.err.println("No se encontró escudería '" + nombreEscuderia + "' para el auto " + modelo);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar autos desde CSV: " + e.getMessage());
        }
            return autos;
    }
    
    /**
 * Lee AutoPiloto.csv y devuelve un ArrayList de asociaciones.
 * @param listaPilotos La lista de pilotos ya cargada.
 * @param listaAutos La lista de autos ya cargada.
 */
    public ArrayList<AutoPiloto> cargarAutoPilotos(ArrayList<Piloto> listaPilotos, ArrayList<Auto> listaAutos) {

    ArrayList<AutoPiloto> asociaciones = new ArrayList<>();
    File archivo = new File(AUTOPILOTO_CSV);
    if (!archivo.exists()) {
        return asociaciones; // Devuelve lista vacía
    }

    try (FileReader fr = new FileReader(archivo);
         BufferedReader br = new BufferedReader(fr)) {

        br.readLine(); // Salteamos cabecera

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(SEPARADOR);
            if (datos.length < 3) continue;

            String dniPiloto = datos[0];
            String modeloAuto = datos[1];
            String fechaAsignacion = datos[2];

            // --- VINCULACIÓN ---
            Piloto piloto = listaPilotos.stream().filter(p -> p.getDni().equals(dniPiloto)).findFirst().orElse(null);
            Auto auto = listaAutos.stream().filter(a -> a.getModelo().equalsIgnoreCase(modeloAuto)).findFirst().orElse(null);

            if (piloto != null && auto != null) {
                AutoPiloto ap = new AutoPiloto();
                ap.setPiloto(piloto);
                ap.setAuto(auto);
                ap.setFechaAsignacion(fechaAsignacion);

                // Doble vinculación (¡Aquí se usa tu método!)
                piloto.agregarAutoPiloto(ap);

                asociaciones.add(ap);
            } else {
                System.err.println("Error al vincular AutoPiloto: " + linea);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar AutoPilotos: " + e.getMessage());
    }
        return asociaciones;
    }
    
    public ArrayList<Circuito> cargarCircuitos(ArrayList<Pais> listaDePaises) {
        ArrayList<Circuito> circuitos = new ArrayList<>();
        File archivo = new File(CIRCUITOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + CIRCUITOS_CSV);
            return circuitos; // Lista vacía
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salteamos cabecera (nombre;longitud;pais_descripcion)

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 3) {
                    
                    String nombreCircuito = datos[0];
                    int longitud = Integer.parseInt(datos[1]);
                    String paisDescripcion = datos[2]; // El nombre/descripción del país
                    
                    // --- VINCULACIÓN ---
                    // Buscamos el objeto Pais que corresponde a esa DESCRIPCIÓN
                    Pais paisDelCircuito = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) {
                            paisDelCircuito = pais;
                            break;
                        }
                    }
                    
                    if (paisDelCircuito != null) {
                        Circuito c = new Circuito();
                        c.setNombre(nombreCircuito);
                        c.setLongitud(longitud);
                        c.setPais(paisDelCircuito); // Asignamos el objeto Pais encontrado
                        circuitos.add(c);
                    } else {
                        System.err.println("No se encontró país con descripción '" + paisDescripcion + "' para el circuito " + nombreCircuito);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar circuitos desde CSV: " + e.getMessage());
        }
        return circuitos;
    }
    
    public ArrayList<Mecanico> cargarMecanicos(ArrayList<Pais> listaDePaises) {
    ArrayList<Mecanico> mecanicos = new ArrayList<>();
    File archivo = new File(MECANICOS_CSV);

    if (!archivo.exists()) {
        System.err.println("No se encontró el archivo: " + MECANICOS_CSV);
        return mecanicos;
    }

    try (FileReader fr = new FileReader(archivo);
         BufferedReader br = new BufferedReader(fr)) {

        br.readLine(); // Salteamos cabecera

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(SEPARADOR);

            if (datos.length >= 6) {
                try {
                    String dni = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String paisDescripcion = datos[3];
                    String especialidadStr = datos[4]; // Leemos el String
                    int experiencia = Integer.parseInt(datos[5]);

                    Pais paisDelMecanico = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) {
                            paisDelMecanico = pais;
                            break;
                        }
                    }

                    // --- ¡CAMBIO IMPORTANTE! ---
                    // Convertimos el String de vuelta a un Enum
                    // Usamos toUpperCase() por si el CSV fue editado a mano
                    Especialidad especialidad = Especialidad.valueOf(especialidadStr.toUpperCase()); 

                    if (paisDelMecanico != null) {
                        Mecanico m = new Mecanico();
                        m.setDni(dni);
                        m.setNombre(nombre);
                        m.setApellido(apellido);
                        m.setPais(paisDelMecanico);
                        m.setEspecialidad(especialidad); // Asignamos el enum
                        m.setAñosExperiencia(experiencia);
                        mecanicos.add(m);
                    } else {
                        System.err.println("País no encontrado para mecánico " + nombre);
                    }
                // Este catch es por si 'Especialidad.valueOf()' falla
                } catch (IllegalArgumentException e) { 
                    System.err.println("Especialidad desconocida en CSV: " + datos[4]);
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error al cargar mecánicos desde CSV: " + e.getMessage());
    }
    return mecanicos;
    }
    
    /**
     * Lee MecanicoEscuderia.csv y devuelve un ArrayList de asociaciones.
     * Realiza la "doble vinculación" en memoria.
     * @param listaMecanicos La lista de mecánicos ya cargada.
     * @param listaEscuderias La lista de escuderías ya cargada.
     */
    public ArrayList<MecanicoEscuderia> cargarMecanicosEscuderias(
            ArrayList<Mecanico> listaMecanicos, ArrayList<Escuderia> listaEscuderias) {
        
        ArrayList<MecanicoEscuderia> asociaciones = new ArrayList<>();
        File archivo = new File(MECANICOESCUDERIA_CSV); 

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + MECANICOESCUDERIA_CSV);
            return asociaciones; // Devuelve una lista vacía si no hay archivo
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salteamos la cabecera (dni_mecanico;nombre_escuderia)

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length < 2) continue; 

                String dniMecanico = datos[0];
                String nombreEscuderia = datos[1];

                // --- VINCULACIÓN ---
                // 1. Busca el objeto Mecanico real por su DNI
                Mecanico mecanico = null;
                for(Mecanico m : listaMecanicos) {
                    if(m.getDni().equals(dniMecanico)) {
                        mecanico = m;
                        break;
                    }
                }

                // 2. Busca el objeto Escuderia real por su Nombre
                Escuderia escuderia = null;
                for(Escuderia e : listaEscuderias) {
                    if(e.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                        escuderia = e;
                        break;
                    }
                }

                // 3. Si encontró ambos, crea la asociación
                if (mecanico != null && escuderia != null) {
                    MecanicoEscuderia me = new MecanicoEscuderia();
                    me.setMecanico(mecanico);
                    me.setEscuderia(escuderia);
                    
                    // --- DOBLE VINCULACIÓN ---
                    // Le decimos al mecánico que está en esta escudería
                    mecanico.agregarMecanicoEscuderia(me); // (El método en Mecanico.java)
                    // Le decimos a la escudería que tiene este mecánico
                    escuderia.agregarMecanicoEscuderia(me); // (El método en Escuderia.java)
                    
                    asociaciones.add(me);
                } else {
                    System.err.println("Error al vincular MecanicoEscuderia: No se encontró Mecánico (DNI " + dniMecanico + ") o Escudería (" + nombreEscuderia + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar MecanicosEscuderias: " + e.getMessage());
        }
        
        return asociaciones;
    }
    
    public ArrayList<Carrera> cargarCarreras(ArrayList<Circuito> listaDeCircuitos) {
    ArrayList<Carrera> carreras = new ArrayList<>();
    File archivo = new File(CARRERAS_CSV);

    if (!archivo.exists()) {
        System.err.println("No se encontró el archivo: " + CARRERAS_CSV);
        return carreras; // Lista vacía
    }

    try (FileReader fr = new FileReader(archivo);
         BufferedReader br = new BufferedReader(fr)) {

        br.readLine(); // Salteamos cabecera (fecha;hora;numero_vueltas;nombre_circuito)

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(SEPARADOR);

            if (datos.length >= 4) {
                try {
                    String fecha = datos[0];
                    String hora = datos[1];
                    int numeroVueltas = Integer.parseInt(datos[2]);
                    String nombreCircuito = datos[3];

                    // --- VINCULACIÓN ---
                    Circuito circuitoDeLaCarrera = null;
                    for (Circuito c : listaDeCircuitos) {
                        if (c.getNombre().equalsIgnoreCase(nombreCircuito)) {
                            circuitoDeLaCarrera = c;
                            break;
                        }
                    }

                    if (circuitoDeLaCarrera != null) {
                        Carrera carrera = new Carrera();
                        carrera.setFechaRealizacion(fecha);
                        carrera.setHoraRealizacion(hora);
                        carrera.setNumeroVueltas(numeroVueltas);
                        carrera.setCircuito(circuitoDeLaCarrera);
                        carreras.add(carrera);
                    } else {
                        System.err.println("No se encontró circuito '" + nombreCircuito + "' para la carrera del " + fecha);
                    }
                } catch (Exception e) {
                    System.err.println("Error parseando línea de carrera: " + linea + " | Error: " + e.getMessage());
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error al cargar carreras desde CSV: " + e.getMessage());
    }
    return carreras;
    }
    
    public ArrayList<ResultadoCarrera> cargarResultadosCarrera(
        ArrayList<AutoPiloto> listaAutoPilotos, ArrayList<Carrera> listaCarreras) {

    ArrayList<ResultadoCarrera> resultados = new ArrayList<>();
    File archivo = new File(RESULTADOS_CSV);
    if (!archivo.exists()) {
        return resultados; // Devuelve lista vacía
    }

    try (FileReader fr = new FileReader(archivo);
         BufferedReader br = new BufferedReader(fr)) {

        br.readLine(); // Salteamos cabecera

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(SEPARADOR);
            if (datos.length < 6) continue; // 6 campos

            String dniPiloto = datos[0];
            String modeloAuto = datos[1];
            String idCarrera = datos[2];
            int posicion = Integer.parseInt(datos[3]);
            String tiempo = datos[4];
            boolean vueltaRapida = Boolean.parseBoolean(datos[5]);

            // --- VINCULACIÓN ---
            // 1. Buscamos la asociación AutoPiloto que coincida
            AutoPiloto autoPiloto = null;
            for (AutoPiloto ap : listaAutoPilotos) {
                if (ap.getPiloto().getDni().equals(dniPiloto) && 
                    ap.getAuto().getModelo().equalsIgnoreCase(modeloAuto)) {
                    autoPiloto = ap;
                    break;
                }
            }

            // 2. Buscamos la Carrera que coincida
            Carrera carrera = null;
            for (Carrera c : listaCarreras) {
                if (c.getFechaRealizacion().equals(idCarrera)) {
                    carrera = c;
                    break;
                }
            }

            // 3. Si encontramos ambos, creamos el Resultado
            if (autoPiloto != null && carrera != null) {
                ResultadoCarrera res = new ResultadoCarrera();
                res.setAutoPiloto(autoPiloto); // Vinculamos la asociación
                res.setCarrera(carrera);
                res.setPosicionFinal(posicion);
                res.setTiempoFinal(tiempo);
                res.setVueltaRapida(vueltaRapida);
                res.setPodio(posicion <= 3); // Calculamos el podio

                resultados.add(res);
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error al cargar ResultadosCarrera: " + e.getMessage());
    }
    return resultados;
}
    
        
//    REESCRIBIR CSV
    
    /**
     * SOBRESCRIBE el archivo Escuderias.csv con la lista actualizada.
     * Se usa después de borrar una escudería.
     * @param listaEscuderias La lista completa y actualizada.
     */
    public void reescribirEscuderiasCSV(ArrayList<Escuderia> listaEscuderias) {
        
        // Abrimos el FileWriter SIN 'true' para que BORRE el contenido anterior.
        try (FileWriter fw = new FileWriter(ESCUDERIAS_CSV);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribimos la cabecera (basada en tu CSV)
            bw.write("nombre" + SEPARADOR + "pais_descripcion");
            bw.newLine();

            // 2. Recorremos la lista actualizada y la guardamos
            for (Escuderia escuderia : listaEscuderias) {
                String paisDescripcion = escuderia.getPais().getDescripcion();
                String linea = escuderia.getNombre() + SEPARADOR + paisDescripcion;
                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            // Este es un error grave, si falla aquí, perdiste los datos.
            System.err.println("Error FATAL al reescribir Escuderias.csv: " + e.getMessage());
        }
    }
    
    /**
     * SOBRESCRIBE el archivo Autos.csv con la lista actualizada.
     * Se usa después de borrar una escudería (para borrar sus autos).
     * @param listaAutos La lista completa y actualizada.
     */
    public void reescribirAutosCSV(ArrayList<Auto> listaAutos) {
        
        try (FileWriter fw = new FileWriter(AUTOS_CSV); // Sin 'true'
             BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribimos la cabecera
            bw.write("modelo" + SEPARADOR + "motor" + SEPARADOR + "nombre_escuderia");
            bw.newLine();

            // 2. Recorremos la lista actualizada
            for (Auto auto : listaAutos) {
                String nombreEscuderia = auto.getEscuderia().getNombre();
                String linea = auto.getModelo() + SEPARADOR +
                               auto.getMotor() + SEPARADOR +
                               nombreEscuderia;
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error FATAL al reescribir Autos.csv: " + e.getMessage());
        }
    }
    
    /**
     * SOBRESCRIBE el archivo PilotoEscuderia.csv con la lista actualizada.
     * Se usa después de borrar una escudería o desasociar un piloto.
     * @param listaAsociaciones La lista completa y actualizada de asociaciones.
     */
    public void reescribirPilotoEscuderiaCSV(ArrayList<PilotoEscuderia> listaAsociaciones) {
        
        try (FileWriter fw = new FileWriter(PILOTOESCUDERIA_CSV); // Sin 'true'
             BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribimos la cabecera
            bw.write("dni_piloto" + SEPARADOR + "nombre_escuderia" + SEPARADOR + 
                     "fecha_desde" + SEPARADOR + "fecha_hasta");
            bw.newLine();

            // 2. Recorremos la lista actualizada
            for (PilotoEscuderia asoc : listaAsociaciones) {
                String dniPiloto = asoc.getPiloto().getDni();
                String nombreEscuderia = asoc.getEscuderia().getNombre();

                String linea = dniPiloto + SEPARADOR +
                               nombreEscuderia + SEPARADOR +
                               asoc.getDesdeFecha() + SEPARADOR +
                               asoc.getHastaFecha();
                
                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error FATAL al reescribir PilotoEscuderia.csv: " + e.getMessage());
        }
    }
    
    /**
     * SOBRESCRIBE el archivo MecanicoEscuderia.csv con la lista actualizada.
     * Se usa después de borrar una escudería o desasociar un mecánico.
     * @param listaAsociaciones La lista completa y actualizada de asociaciones.
     */
    public void reescribirMecanicoEscuderiaCSV(ArrayList<MecanicoEscuderia> listaAsociaciones) {
        
        try (FileWriter fw = new FileWriter(MECANICOESCUDERIA_CSV); // Sin 'true'
             BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribimos la cabecera
            bw.write("dni_mecanico" + SEPARADOR + "nombre_escuderia");
            bw.newLine();

            // 2. Recorremos la lista actualizada
            for (MecanicoEscuderia asoc : listaAsociaciones) {
                String linea = asoc.getMecanico().getDni() + SEPARADOR +
                               asoc.getEscuderia().getNombre();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error FATAL al reescribir MecanicoEscuderia.csv: " + e.getMessage());
        }
    }
    
}  

