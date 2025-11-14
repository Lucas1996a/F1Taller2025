package logica;

import gui.Pantalla;

/**
 * Clase principal que contiene el punto de entrada (main method)
 * para la aplicación de gestión de F1.
 * * Su única responsabilidad es instanciar la lógica de gestión ({@link Gestion})
 * y la interfaz de usuario principal ({@link Pantalla}), y hacerla visible.
 */
public class ProgramaF1 {

    /**
     * El método principal que inicia la aplicación.
     * * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {

        // 1. Crea una instancia del controlador principal de la lógica de negocio.
        Gestion gestionPrincipal = new Gestion();

        // 2. Crea la pantalla principal de la GUI, pasándole la instancia de gestión.
        Pantalla pantalla = new Pantalla(gestionPrincipal);
        
        // 3. Hace visible la pantalla.
        pantalla.setVisible(true);
        
        // 4. Centra la pantalla en el monitor.
        pantalla.setLocationRelativeTo(null);
    }
}