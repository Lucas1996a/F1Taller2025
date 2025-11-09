package logica;

import gui.Pantalla;

public class ProgramaF1  {

    public static void main(String[] args) {
        
        Gestion gestionPrincipal = new Gestion();
        
        Pantalla pantalla = new Pantalla(gestionPrincipal);
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
    }
 }