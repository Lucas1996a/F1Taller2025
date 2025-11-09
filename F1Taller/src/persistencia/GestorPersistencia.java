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
        File archivo = new File(PILOTOS_CSV);
        boolean noExiste = !archivo.exists();

        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (noExiste) {
                // Escribimos la cabecera
                bw.write("dni;nombre;apellido;id_pais;numero_competencia;victorias;poles;vueltas_rapidas;podios");
                bw.newLine();
            }

            // Guardamos el ID del país, no el objeto entero
            int idPais = piloto.getPais().getIdPais();

            String linea = piloto.getDni() + SEPARADOR +
                           piloto.getNombre() + SEPARADOR +
                           piloto.getApellido() + SEPARADOR +
                           idPais + SEPARADOR + // Guardamos solo el ID
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
            return pilotos; // Lista vacía
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine(); // Salteamos cabecera

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);

                if (datos.length == 9) {
                    // Creamos el piloto con los datos del CSV
                    Piloto p = new Piloto();
                    p.setDni(datos[0]);
                    p.setNombre(datos[1]);
                    p.setApellido(datos[2]);
                    
                    int idPais = Integer.parseInt(datos[3]);
                    
                    // --- VINCULACIÓN ---
                    // Buscamos el objeto Pais que corresponde a ese ID
                    Pais paisDelPiloto = null;
                    for (Pais pais : listaDePaises) {
                        if (pais.getIdPais() == idPais) {
                            paisDelPiloto = pais;
                            break;
                        }
                    }
                    p.setPais(paisDelPiloto); // Asignamos el objeto Pais encontrado
                    
                    p.setNumeroCompetencia(Integer.parseInt(datos[4]));
                    p.setVictorias(Integer.parseInt(datos[5]));
                    p.setPolePosition(Integer.parseInt(datos[6]));
                    p.setVueltasRapidas(Integer.parseInt(datos[7]));
                    p.setPodios(Integer.parseInt(datos[8]));
                    
                    pilotos.add(p);
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
    
        
}  

