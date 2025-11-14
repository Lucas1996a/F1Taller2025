package persistencia;

/**
 * Gestor central de la persistencia de datos.
 * Esta clase se encarga de todas las operaciones de I/O (Lectura/Escritura)
 * para guardar el estado de la aplicación en archivos CSV y
 * cargarlo de nuevo en memoria.
 *
 * Se encarga de "serializar" (guardar) y "deserializar" (cargar)
 * todo el modelo de objetos de la lógica.
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
import logica.*;

public class GestorPersistencia {
    // --- RUTAS DE LOS ARCHIVOS ---
    
    /** Ruta al archivo CSV de Escuderías. */
    private static final String ESCUDERIAS_CSV = "src/data/Escuderias.csv";
    /** Ruta al archivo CSV de Autos. */
    private static final String AUTOS_CSV = "src/data/Autos.csv";
    /** Ruta al archivo CSV de Pilotos. */
    private static final String PILOTOS_CSV = "src/data/Pilotos.csv";
    /** Ruta al archivo CSV de Circuitos. */
    private static final String CIRCUITOS_CSV = "src/data/Circuitos.csv";
    /** Ruta al archivo CSV de Países. */
    private static final String PAISES_CSV = "src/data/Paises.csv";
    /** Ruta al archivo CSV de Mecánicos. */
    private static final String MECANICOS_CSV = "src/data/Mecanicos.csv";
    /** Ruta al archivo CSV de Carreras. */
    private static final String CARRERAS_CSV = "src/data/Carreras.csv";
    /** Ruta al archivo CSV de la asociación Piloto-Escudería. */
    private static final String PILOTOESCUDERIA_CSV = "src/data/PilotoEscuderia.csv";
    /** Ruta al archivo CSV de la asociación Mecánico-Escudería. */
    private static final String MECANICOESCUDERIA_CSV = "src/data/MecanicoEscuderia.csv";
    /** Ruta al archivo CSV de la asociación Auto-Piloto. */
    private static final String AUTOPILOTO_CSV = "src/data/AutoPiloto.csv";
    /** Ruta al archivo CSV de los resultados de carreras. */
    private static final String RESULTADOS_CSV = "src/data/ResultadosCarrera.csv";

    /** Separador de campos utilizado en todos los archivos CSV. */
    private static final String SEPARADOR = ";";

    /**
     * Constructor por defecto del gestor de persistencia.
     */
    public GestorPersistencia() {
    }

    // --- MÉTODOS DE GUARDADO (SERIALIZACIÓN) ---

    /**
     * Añade una nueva línea al archivo Paises.csv con los datos del país.
     * Si el archivo no existe, lo crea y escribe la cabecera.
     *
     * @param pais El objeto {@link Pais} a guardar.
     */
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
     * Añade una nueva línea al archivo Pilotos.csv.
     * Guarda los datos del piloto y "aplana" las relaciones, guardando
     * la descripción (nombre) del país en lugar del objeto completo.
     *
     * @param piloto El objeto {@link Piloto} a guardar.
     */
    public void guardarPiloto(Piloto piloto) {
        File archivo = new File(PILOTOS_CSV);
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

            // VINCULACIÓN: Obtenemos la clave (descripción) del país
            String paisDescripcion = piloto.getPais().getDescripcion();

            // Escribimos la línea de datos
            String linea = piloto.getDni() + SEPARADOR +
                    piloto.getNombre() + SEPARADOR +
                    piloto.getApellido() + SEPARADOR +
                    paisDescripcion + SEPARADOR +
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

    /**
     * Añade una nueva línea al archivo Escuderias.csv.
     * Guarda el nombre de la escudería y la descripción de su país.
     *
     * @param escuderia El objeto {@link Escuderia} a guardar.
     */
    public void guardarEscuderia(Escuderia escuderia) {
        File archivo = new File(ESCUDERIAS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("nombre" + SEPARADOR + "descPais");
                bw.newLine();
            }

            // VINCULACIÓN: Guarda la descripción (clave) del país
            String descPais = escuderia.getPais().getDescripcion();

            // Escribe la línea de datos
            String linea = escuderia.getNombre() + SEPARADOR +
                    descPais;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar escudería en CSV: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo PilotoEscuderia.csv.
     * Guarda las claves (DNI del piloto, nombre de escudería) para
     * representar la asociación, junto con las fechas del contrato.
     *
     * @param asociacion El objeto {@link PilotoEscuderia} a guardar.
     */
    public void guardarPilotoEscuderia(PilotoEscuderia asociacion) {
        File archivo = new File(PILOTOESCUDERIA_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("dni_piloto" + SEPARADOR + "nombre_escuderia" + SEPARADOR +
                        "fecha_desde" + SEPARADOR + "fecha_hasta");
                bw.newLine();
            }

            // VINCULACIÓN: Obtiene las claves de los objetos
            String dniPiloto = asociacion.getPiloto().getDni();
            String nombreEscuderia = asociacion.getEscuderia().getNombre();

            // Escribe la línea de datos
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

    /**
     * Añade una nueva línea al archivo Autos.csv.
     * Guarda el modelo, motor y el nombre (clave) de la escudería.
     *
     * @param auto El objeto {@link Auto} a guardar.
     */
    public void guardarAuto(Auto auto) {
        File archivo = new File(AUTOS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Cabecera del archivo
                bw.write("modelo" + SEPARADOR + "motor" + SEPARADOR + "nombre_escuderia");
                bw.newLine();
            }

            // VINCULACIÓN: Obtiene el nombre (clave) de la escudería
            String nombreEscuderia = auto.getEscuderia().getNombre();

            // Escribe la línea de datos
            String linea = auto.getModelo() + SEPARADOR +
                    auto.getMotor() + SEPARADOR +
                    nombreEscuderia;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar auto en CSV: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo AutoPiloto.csv.
     * Guarda las claves (DNI piloto, modelo auto) y la fecha de asignación.
     *
     * @param asociacion El objeto {@link AutoPiloto} a guardar.
     */
    public void guardarAutoPiloto(AutoPiloto asociacion) {
        File archivo = new File(AUTOPILOTO_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("dni_piloto" + SEPARADOR + "modelo_auto" + SEPARADOR + "fecha_asignacion");
                bw.newLine();
            }

            // VINCULACIÓN: Obtiene las claves de los objetos
            String dniPiloto = asociacion.getPiloto().getDni();
            String modeloAuto = asociacion.getAuto().getModelo();
            String fecha = asociacion.getFechaAsignacion();

            // Escribe la línea de datos
            String linea = dniPiloto + SEPARADOR +
                    modeloAuto + SEPARADOR +
                    fecha;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar la asociación AutoPiloto: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo Circuitos.csv.
     * Guarda el nombre, longitud y la descripción (clave) del país.
     *
     * @param circuito El objeto {@link Circuito} a guardar.
     */
    public void guardarCircuito(Circuito circuito) {
        File archivo = new File(CIRCUITOS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("nombre" + SEPARADOR + "longitud" + SEPARADOR + "pais_descripcion");
                bw.newLine();
            }

            // --- VINCULACIÓN ---
            String paisDescripcion = circuito.getPais().getDescripcion();

            // Escribe la línea de datos
            String linea = circuito.getNombre() + SEPARADOR +
                    circuito.getLongitud() + SEPARADOR +
                    paisDescripcion; // Guardamos el nombre del país

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar circuito en CSV: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo Mecanicos.csv.
     * Guarda los datos del mecánico, la descripción de su país y el nombre del enum Especialidad.
     *
     * @param mecanico El objeto {@link Mecanico} a guardar.
     */
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

            // Convierte el enum a su nombre en String (ej: "MOTOR")
            String especialidadStr = mecanico.getEspecialidad().name();

            String linea = mecanico.getDni() + SEPARADOR +
                    mecanico.getNombre() + SEPARADOR +
                    mecanico.getApellido() + SEPARADOR +
                    paisDescripcion + SEPARADOR +
                    especialidadStr + SEPARADOR +
                    mecanico.getAñosExperiencia();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar mecánico en CSV: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo MecanicoEscuderia.csv.
     * Guarda las claves (DNI mecánico, nombre escudería) y las fechas.
     *
     * @param asociacion El objeto {@link MecanicoEscuderia} a guardar.
     */
    public void guardarMecanicoEscuderia(MecanicoEscuderia asociacion) {

        File archivo = new File(MECANICOESCUDERIA_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Cabecera corregida para incluir fechas
                bw.write("dni_mecanico" + SEPARADOR + "nombre_escuderia" + SEPARADOR + "fecha_desde" + SEPARADOR + "fecha_hasta");
                bw.newLine();
            }

            //VINCULACIÓN
            String dniMecanico = asociacion.getMecanico().getDni();
            String nombreEscuderia = asociacion.getEscuderia().getNombre();
            String desde = asociacion.getDesdeFecha();
            String hasta = asociacion.getHastaFecha();

            // Escribe la línea de datos
            String linea = dniMecanico + SEPARADOR + nombreEscuderia + SEPARADOR + desde + SEPARADOR + hasta;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar la asociación MecanicoEscuderia: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva línea al archivo Carreras.csv.
     * Guarda los datos de la carrera y el nombre (clave) del circuito.
     *
     * @param carrera El objeto {@link Carrera} a guardar.
     */
    public void guardarCarrera(Carrera carrera) {
        File archivo = new File(CARRERAS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("fecha" + SEPARADOR + "hora" + SEPARADOR + "numero_vueltas" + SEPARADOR + "nombre_circuito");
                bw.newLine();
            }

            // VINCULACIÓN
            String nombreCircuito = carrera.getCircuito().getNombre();

            // Escribe la línea de datos
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

    /**
     * Añade una nueva línea al archivo ResultadosCarrera.csv.
     * Guarda las claves (DNI piloto, modelo auto, fecha carrera) y los datos del resultado.
     *
     * @param resultado El objeto {@link ResultadoCarrera} a guardar.
     */
    public void guardarResultadoCarrera(ResultadoCarrera resultado) {
        File archivo = new File(RESULTADOS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                bw.write("dni_piloto" + SEPARADOR + "modelo_auto" + SEPARADOR + "id_carrera" +
                        SEPARADOR + "posicion_final" + SEPARADOR + "tiempo_final" + SEPARADOR + "vuelta_rapida");
                bw.newLine();
            }

            // VINCULACIÓN: Saca las claves de los objetos anidados
            String dniPiloto = resultado.getAutoPiloto().getPiloto().getDni();
            String modeloAuto = resultado.getAutoPiloto().getAuto().getModelo();
            // Usa la fecha de la carrera como su ID único
            String idCarrera = resultado.getCarrera().getFechaRealizacion();

            // Escribimos la línea de datos
            String linea = dniPiloto + SEPARADOR +
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

    // --- MÉTODOS DE CARGA (DESERIALIZACIÓN) ---

    /**
     * Lee el archivo Paises.csv y lo convierte en una lista de objetos {@link Pais}.
     * Este método debe ser uno de los primeros en llamarse al cargar.
     *
     * @return Un ArrayList de {@link Pais} con los datos cargados del CSV.
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
     * Lee Pilotos.csv y devuelve un ArrayList de objetos {@link Piloto}.
     * Este método **requiere** la lista de países para poder re-vincular
     * la nacionalidad de cada piloto.
     *
     * @param listaDePaises La lista de todos los países ya cargados en memoria.
     * @return Un ArrayList de {@link Piloto} con los datos cargados.
     */
    public ArrayList<Piloto> cargarPilotos(ArrayList<Pais> listaDePaises) {
        ArrayList<Piloto> pilotos = new ArrayList<>();
        File archivo = new File(PILOTOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + PILOTOS_CSV);
            return pilotos;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta la cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 9) {
                    try {
                        String dni = datos[0];
                        String nombre = datos[1];
                        String apellido = datos[2];
                        String paisDescripcion = datos[3]; // Clave foránea (nombre)
                        int numeroComp = Integer.parseInt(datos[4]);
                        int victorias = Integer.parseInt(datos[5]);
                        int poles = Integer.parseInt(datos[6]);
                        int fastLap = Integer.parseInt(datos[7]);
                        int podios = Integer.parseInt(datos[8]);

                        // VINCULACIÓN: Busca el objeto Pais que corresponde a esa DESCRIPCIÓN
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
                            p.setPais(paisDelPiloto); // Asigna el objeto encontrado
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

    /**
     * Lee Escuderias.csv y devuelve un ArrayList de objetos {@link Escuderia}.
     * Requiere la lista de países para re-vincular la sede.
     *
     * @param listaDePaises La lista de todos los países ya cargados en memoria.
     * @return Un ArrayList de {@link Escuderia}.
     */
    public ArrayList<Escuderia> cargarEscuderias(ArrayList<Pais> listaDePaises) {
        ArrayList<Escuderia> escuderias = new ArrayList<>();
        File archivo = new File(ESCUDERIAS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + ESCUDERIAS_CSV);
            return escuderias;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta la cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 2) {

                    String nombreEscuderia = datos[0];
                    String paisDescripcion = datos[1]; // Clave foránea (nombre)

                    // VINCULACIÓN: Busca el objeto Pais
                    Pais paisDeLaEscuderia = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) {
                            paisDeLaEscuderia = pais;
                            break;
                        }
                    }

                    // Crea la escudería y la añade a la lista
                    if (paisDeLaEscuderia != null) {
                        Escuderia esc = new Escuderia();
                        esc.setNombre(nombreEscuderia);
                        esc.setPais(paisDeLaEscuderia); // Asigna el objeto
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

    /**
     * Carga las asociaciones de PilotoEscuderia.csv.
     * Lee las claves (DNI, nombre de escudería) y busca los objetos
     * correspondientes en las listas proveídas.
     * Realiza la **doble vinculación**: añade la asociación al piloto
     * y a la escudería.
     *
     * @param listaPilotos La lista de todos los pilotos ya cargados.
     * @param listaEscuderias La lista de todas las escuderías ya cargadas.
     * @return Un ArrayList de {@link PilotoEscuderia} con las relaciones.
     */
    public ArrayList<PilotoEscuderia> cargarPilotosEscuderias(
            ArrayList<Piloto> listaPilotos, ArrayList<Escuderia> listaEscuderias) {

        ArrayList<PilotoEscuderia> asociaciones = new ArrayList<>();
        File archivo = new File(PILOTOESCUDERIA_CSV);

        if (!archivo.exists()) {
            return asociaciones;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length < 4) { // Asegura que lee las fechas
                    continue;
                }

                String dniPiloto = datos[0].trim();
                String nombreEscuderia = datos[1].trim();
                String fechaDesde = datos[2];
                String fechaHasta = datos[3];

                // VINCULACIÓN 
                Piloto piloto = listaPilotos.stream().filter(p -> p.getDni().equals(dniPiloto)).findFirst().orElse(null);
                Escuderia escuderia = listaEscuderias.stream().filter(e -> e.getNombre().equalsIgnoreCase(nombreEscuderia)).findFirst().orElse(null);

                if (piloto != null && escuderia != null) {

                    PilotoEscuderia pe = new PilotoEscuderia();
                    pe.setPiloto(piloto);
                    pe.setEscuderia(escuderia);
                    pe.setDesdeFecha(fechaDesde);
                    pe.setHastaFecha(fechaHasta);

                    // Doble vinculación (fundamental)
                    piloto.agregarPilotoEscuderia(pe);
                    escuderia.agregarPilotoEscuderia(pe);

                    asociaciones.add(pe);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar PilotoEscuderia: " + e.getMessage());
        }

        return asociaciones;
    }

    /**
     * Lee Autos.csv y devuelve un ArrayList de objetos {@link Auto}.
     * Requiere la lista de escuderías para re-vincular cada auto a su equipo.
     *
     * @param listaDeEscuderias La lista de todas las escuderías ya cargadas.
     * @return Un ArrayList de {@link Auto}.
     */
    public ArrayList<Auto> cargarAutos(ArrayList<Escuderia> listaDeEscuderias) {
        ArrayList<Auto> autos = new ArrayList<>();
        File archivo = new File(AUTOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + AUTOS_CSV);
            return autos;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 3) {

                    String modelo = datos[0];
                    String motor = datos[1];
                    String nombreEscuderia = datos[2]; // Clave foránea (nombre)

                    // --- VINCULACIÓN ---
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
                        auto.setEscuderia(escuderiaDelAuto); // Asigna el objeto
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
     * Carga las asociaciones de AutoPiloto.csv.
     * Realiza la **doble vinculación**: añade la asociación al piloto
     * y al auto.
     *
     * @param listaPilotos La lista de todos los pilotos ya cargados.
     * @param listaAutos La lista de todos los autos ya cargados.
     * @return Un ArrayList de {@link AutoPiloto} con las relaciones.
     */
    public ArrayList<AutoPiloto> cargarAutoPilotos(ArrayList<Piloto> listaPilotos, ArrayList<Auto> listaAutos) {

        ArrayList<AutoPiloto> asociaciones = new ArrayList<>();
        File archivo = new File(AUTOPILOTO_CSV);
        if (!archivo.exists()) {
            return asociaciones;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length < 3)
                    continue;

                String dniPiloto = datos[0];
                String modeloAuto = datos[1];
                String fechaAsignacion = datos[2];

                // VINCULACIÓN
                Piloto piloto = listaPilotos.stream().filter(p -> p.getDni().equals(dniPiloto)).findFirst().orElse(null);
                Auto auto = listaAutos.stream().filter(a -> a.getModelo().equalsIgnoreCase(modeloAuto)).findFirst().orElse(null);

                if (piloto != null && auto != null) {

                    AutoPiloto ap = new AutoPiloto();
                    ap.setPiloto(piloto);
                    ap.setAuto(auto);
                    ap.setFechaAsignacion(fechaAsignacion);

                    // Doble vinculación
                    piloto.agregarAutoPiloto(ap);
                    auto.agregarAutoPiloto(ap);

                    asociaciones.add(ap);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar AutoPilotos: " + e.getMessage());
        }
        return asociaciones;
    }

    /**
     * Lee Circuitos.csv y devuelve un ArrayList de objetos {@link Circuito}.
     * Requiere la lista de países para re-vincular la ubicación del circuito.
     *
     * @param listaDePaises La lista de todos los países ya cargados.
     * @return Un ArrayList de {@link Circuito}.
     */
    public ArrayList<Circuito> cargarCircuitos(ArrayList<Pais> listaDePaises) {
        ArrayList<Circuito> circuitos = new ArrayList<>();
        File archivo = new File(CIRCUITOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + CIRCUITOS_CSV);
            return circuitos;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 3) {

                    String nombreCircuito = datos[0];
                    int longitud = Integer.parseInt(datos[1]);
                    String paisDescripcion = datos[2]; // Clave foránea (nombre)

                    // VINCULACIÓN
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
                        c.setPais(paisDelCircuito); // Asigna el objeto Pais encontrado
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

    /**
     * Lee Mecanicos.csv y devuelve un ArrayList de objetos {@link Mecanico}.
     * Requiere la lista de países para re-vincular la nacionalidad.
     * Convierte el String de especialidad de nuevo a un enum {@link Especialidad}.
     *
     * @param listaDePaises La lista de todos los países ya cargados.
     * @return Un ArrayList de {@link Mecanico}.
     */
    public ArrayList<Mecanico> cargarMecanicos(ArrayList<Pais> listaDePaises) {
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        File archivo = new File(MECANICOS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + MECANICOS_CSV);
            return mecanicos;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 6) {
                    try {
                        String dni = datos[0];
                        String nombre = datos[1];
                        String apellido = datos[2];
                        String paisDescripcion = datos[3];
                        String especialidadStr = datos[4];
                        int experiencia = Integer.parseInt(datos[5]);

                        // VINCULACIÓN País
                        Pais paisDelMecanico = null;
                        for (Pais pais : listaDePaises) {
                            if (pais.getDescripcion().equalsIgnoreCase(paisDescripcion)) {
                                paisDelMecanico = pais;
                                break;
                            }
                        }

                        // VINCULACIÓN Enum (convierte String "MOTOR" a Especialidad.MOTOR)
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
     * Carga las asociaciones de MecanicoEscuderia.csv.
     * Realiza la **doble vinculación**: añade la asociación al mecánico
     * y a la escudería.
     *
     * @param listaMecanicos La lista de todos los mecánicos ya cargados.
     * @param listaEscuderias La lista de todas las escuderías ya cargadas.
     * @return Un ArrayList de {@link MecanicoEscuderia}.
     */
    public ArrayList<MecanicoEscuderia> cargarMecanicosEscuderias(
            ArrayList<Mecanico> listaMecanicos, ArrayList<Escuderia> listaEscuderias) {

        ArrayList<MecanicoEscuderia> asociaciones = new ArrayList<>();
        File archivo = new File(MECANICOESCUDERIA_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + MECANICOESCUDERIA_CSV);
            return asociaciones;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length < 4) continue; // Debe tener dni, escuderia, desde, hasta

                String dniMecanico = datos[0];
                String nombreEscuderia = datos[1];
                String desde = datos[2];
                String hasta = datos[3];

                // VINCULACIÓN
                Mecanico mecanico = null;
                for (Mecanico m : listaMecanicos) {
                    if (m.getDni().equals(dniMecanico)) {
                        mecanico = m;
                        break;
                    }
                }

                Escuderia escuderia = null;
                for (Escuderia e : listaEscuderias) {
                    if (e.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                        escuderia = e;
                        break;
                    }
                }

                if (mecanico != null && escuderia != null) {
                    MecanicoEscuderia me = new MecanicoEscuderia();
                    me.setMecanico(mecanico);
                    me.setEscuderia(escuderia);
                    me.setDesdeFecha(desde);
                    me.setHastaFecha(hasta);

                    // DOBLE VINCULACIÓN
                    mecanico.agregarMecanicoEscuderia(me);
                    escuderia.agregarMecanicoEscuderia(me);

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

    /**
     * Lee Carreras.csv y devuelve un ArrayList de objetos {@link Carrera}.
     * Requiere la lista de circuitos para re-vincular la ubicación.
     *
     * @param listaDeCircuitos La lista de todos los circuitos ya cargados.
     * @return Un ArrayList de {@link Carrera}.
     */
    public ArrayList<Carrera> cargarCarreras(ArrayList<Circuito> listaDeCircuitos) {
        ArrayList<Carrera> carreras = new ArrayList<>();
        File archivo = new File(CARRERAS_CSV);

        if (!archivo.exists()) {
            System.err.println("No se encontró el archivo: " + CARRERAS_CSV);
            return carreras;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length >= 4) {
                    try {
                        String fecha = datos[0];
                        String hora = datos[1];
                        int numeroVueltas = Integer.parseInt(datos[2]);
                        String nombreCircuito = datos[3]; // Clave foránea

                        // VINCULACIÓN
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
                            // Vincula también el país, heredado del circuito
                            carrera.setPais(circuitoDeLaCarrera.getPais());
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

    /**
     * Lee ResultadosCarrera.csv y devuelve un ArrayList de {@link ResultadoCarrera}.
     * Este es uno de los métodos más complejos, ya que vincula una {@link Carrera}
     * y una asociación {@link AutoPiloto} (por sus claves).
     *
     * @param listaAutoPilotos La lista de todas las asociaciones Auto-Piloto.
     * @param listaCarreras La lista de todas las carreras.
     * @return Un ArrayList de {@link ResultadoCarrera}.
     */
    public ArrayList<ResultadoCarrera> cargarResultadosCarrera(
            ArrayList<AutoPiloto> listaAutoPilotos, ArrayList<Carrera> listaCarreras) {

        ArrayList<ResultadoCarrera> resultados = new ArrayList<>();
        File archivo = new File(RESULTADOS_CSV);
        if (!archivo.exists()) {
            return resultados;
        }

        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salta cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length < 6)
                    continue;

                String dniPiloto = datos[0];
                String modeloAuto = datos[1];
                String idCarrera = datos[2]; // Clave (fecha) de la carrera
                int posicion = Integer.parseInt(datos[3]);
                String tiempo = datos[4];
                boolean vueltaRapida = Boolean.parseBoolean(datos[5]);

                // VINCULACIÓN 1: Buscar la asociación AutoPiloto
                AutoPiloto autoPiloto = null;
                for (AutoPiloto ap : listaAutoPilotos) {
                    if (ap.getPiloto().getDni().equals(dniPiloto) &&
                            ap.getAuto().getModelo().equalsIgnoreCase(modeloAuto)) {
                        autoPiloto = ap;
                        break;
                    }
                }

                // VINCULACIÓN 2: Buscar la Carrera
                Carrera carrera = null;
                for (Carrera c : listaCarreras) {
                    if (c.getFechaRealizacion().equals(idCarrera)) {
                        carrera = c;
                        break;
                    }
                }

                // Si encuentra ambos, crea el Resultado
                if (autoPiloto != null && carrera != null) {
                    ResultadoCarrera res = new ResultadoCarrera();
                    res.setAutoPiloto(autoPiloto); // Vincula la asociación
                    res.setCarrera(carrera);
                    res.setPosicionFinal(posicion);
                    res.setTiempoFinal(tiempo);
                    res.setVueltaRapida(vueltaRapida);
                    res.setPodio(posicion <= 3); // Calcula el podio

                    resultados.add(res);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar ResultadosCarrera: " + e.getMessage());
        }
        return resultados;
    }

    // --- MÉTODOS DE REESCRITURA (Para persistir eliminaciones) ---

    /**
     * **SOBRESCRiBE** el archivo Escuderias.csv con la lista actualizada.
     * Este método se utiliza para persistir cambios, como eliminaciones.
     * Abre el archivo en modo de truncamiento (no-append).
     *
     * @param listaEscuderias La lista completa y actualizada de escuderías.
     */
    public void reescribirEscuderiasCSV(ArrayList<Escuderia> listaEscuderias) {

        // Abre el FileWriter SIN 'true' para que BORRE el contenido anterior.
        try (FileWriter fw = new FileWriter(ESCUDERIAS_CSV);
                BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribe la cabecera
            bw.write("nombre" + SEPARADOR + "pais_descripcion");
            bw.newLine();

            // 2. Recorre la lista actualizada y la guardamos
            for (Escuderia escuderia : listaEscuderias) {
                String paisDescripcion = escuderia.getPais().getDescripcion();
                String linea = escuderia.getNombre() + SEPARADOR + paisDescripcion;
                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error FATAL al reescribir Escuderias.csv: " + e.getMessage());
        }
    }

    /**
     * **SOBRESCRiBE** el archivo Autos.csv con la lista actualizada.
     * Se usa después de borrar una escudería (para borrar sus autos).
     *
     * @param listaAutos La lista completa y actualizada.
     */
    public void reescribirAutosCSV(ArrayList<Auto> listaAutos) {

        try (FileWriter fw = new FileWriter(AUTOS_CSV); // Sin 'true'
                BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribe la cabecera
            bw.write("modelo" + SEPARADOR + "motor" + SEPARADOR + "nombre_escuderia");
            bw.newLine();

            // 2. Recorre la lista actualizada
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
     * **SOBRESCRiBE** el archivo PilotoEscuderia.csv con la lista actualizada.
     * Se usa después de borrar una escudería o desasociar un piloto.
     *
     * @param listaAsociaciones La lista completa y actualizada de asociaciones.
     */
    public void reescribirPilotoEscuderiaCSV(ArrayList<PilotoEscuderia> listaAsociaciones) {

        try (FileWriter fw = new FileWriter(PILOTOESCUDERIA_CSV); // Sin 'true'
                BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribe la cabecera
            bw.write("dni_piloto" + SEPARADOR + "nombre_escuderia" + SEPARADOR +
                    "fecha_desde" + SEPARADOR + "fecha_hasta");
            bw.newLine();

            // 2. Recorre la lista actualizada
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
     * **SOBRESCRiBE** el archivo MecanicoEscuderia.csv con la lista actualizada.
     * Se usa después de borrar una escudería o desasociar un mecánico.
     *
     * @param listaAsociaciones La lista completa y actualizada de asociaciones.
     */
    public void reescribirMecanicoEscuderiaCSV(ArrayList<MecanicoEscuderia> listaAsociaciones) {

        try (FileWriter fw = new FileWriter(MECANICOESCUDERIA_CSV); // Sin 'true'
                BufferedWriter bw = new BufferedWriter(fw)) {

            // 1. Escribe la cabecera
            bw.write("dni_mecanico" + SEPARADOR + "nombre_escuderia" + SEPARADOR + "fecha_desde" + SEPARADOR + "fecha_hasta");
            bw.newLine();

            // 2. Recorre la lista actualizada
            for (MecanicoEscuderia asoc : listaAsociaciones) {
                String linea = asoc.getMecanico().getDni() + SEPARADOR +
                        asoc.getEscuderia().getNombre() + SEPARADOR +
                        asoc.getDesdeFecha() + SEPARADOR +
                        asoc.getHastaFecha();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error FATAL al reescribir MecanicoEscuderia.csv: " + e.getMessage());
        }
    }

}