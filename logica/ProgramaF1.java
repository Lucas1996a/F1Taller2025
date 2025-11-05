
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaF1 implements Metodos {

    public static void main(String[] args) {
        System.out.println("Bienvenido al programa Escuderias Unidas F1");

        ArrayList<Piloto> piloto = new ArrayList<>();
        ArrayList<Mecanico> mecanico = new ArrayList<>();
        ArrayList<Auto> auto = new ArrayList<>();
        ArrayList<Escuderia> escuderia = new ArrayList<>();
        ArrayList<Circuito> circuito = new ArrayList<>();
        ArrayList<Pais> pais = new ArrayList<>();


        Scanner teclado = new Scanner(System.in);
       
        String respuesta = "si";
        // Menu de seleccion
        int exit = 1;
        while (exit != 0) {
            Metodos.mostraMenu();

            //metodo que controla si es un int y si esta en el rango de los valores del menu
            exit = Controlador.controladorOpcionesMenu();
            if (exit == -1){
            continue;    
            } 
            switch (exit) {
                case 0:
                    System.out.println("Gracias por usar el programa.");
                    break;
                case 1:
                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarPiloto(piloto);

                        System.out.println("¿Desea registrar otro chofer? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();
                    break;

                case 2:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarMecanico(mecanico);

                        System.out.println("¿Desea registrar otro vehículo? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                case 3:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarAuto(auto);

                        System.out.println("¿Desea registrar otro vehículo? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                case 4:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarEscuderia(escuderia);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                case 5:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarCircuito(circuito);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                    case 6:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarPais(pais);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                    case 7:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarEscuderia(escuderia);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                    case 8:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarEscuderia(escuderia);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                    case 9:
                    respuesta = "si";

                    while (respuesta.trim().equalsIgnoreCase("si")) {
                        Metodos.agregarEscuderia(escuderia);

                        System.out.println("¿Desea registrar otro viaje? Escriba 'si' para continuar o ingrese cualquier tecla para salir .");
                        respuesta = teclado.nextLine();
                    }
                    Metodos.limpiarConsola();

                    break;

                
            }
        }
        }