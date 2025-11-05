import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public interface Metodos {

    public static void limpiarConsola() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
    }


     public static void mostraMenu(){

        System.out.println("===== MENU =====");
        System.out.println("Ingrese 0 si quiere salir del programa.");
        System.out.println("Ingrese 1 si quiere registrar un piloto.");
        System.out.println("Ingrese 2 si quiere registrar un Mecanico.");
        System.out.println("Ingrese 3 si quiere registrar autos.");
        System.out.println("Ingrese 4 si quiere registrar escuderias.");
        System.out.println("Ingrese 5 si quiere registrar circuitos.");
        System.out.println("Ingrese 6 si quiere registrar paises.");
        System.out.println("Ingrese 7 si quiere Gestionar escuderiascarreras.");
        System.out.println("Ingrese 8 si quiere Planificar carreras.");
        System.out.println("Ingrese 9 si quiere Asociar pilotos a autos y registrar su participación en una carrera.");
        System.out.println("Ingrese 10 si quiere Registrar resultados de las carreras.");
        System.out.println("Ingrese 11 si quiere Calcular puntajes de cada piloto, segun la posicion obtenida.");
        System.out.println("Ingrese 12 si quiere Generar informes.");
        System.out.println("\n" + "Ingrese la opcion del menu que desea" + "\n");

     }


        public static boolean agregarPiloto(ArrayList<Piloto> piloto){
            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar dni");

            String dni = teclado.nextLine();

            System.out.println("Ingrese el nombre");

            String nombre = teclado.nextLine();

            System.out.println("Ingrese el apellido");
            
            String  apellido = teclado.nextLine();

            System.out.println("Ingrese el pais");

            String pais = teclado.nextLine();

            System.out.println("Ingrese el numero de competencio");

            int numeroCompetencia = teclado.nextInt();

        

            return true;


        }




        public static boolean agregarMecanico(ArrayList<Mecanico> mecanico){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar dni");

            String dni = teclado.nextLine();

            System.out.println("Ingrese el nombre");

            String nombre = teclado.nextLine();

            System.out.println("Ingrese el apellido");

            String apellido = teclado.nextLine();

            System.out.println("Ingrese el pais");
        
            String pais = teclado.nextLine();

            System.out.println("Ingrese la especialidad");

            String especialidad = teclado.nextLine();

            System.out.println("Ingrese los años Experiencia");

            int añosExperiencia = teclado.nextInt();


            return true;
        }

        public static boolean agregarAuto(ArrayList<Auto> auto){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar modelo");

            String modelo = teclado.nextLine();

            System.out.println("Ingresar motor");

            String motor = teclado.nextLine();


            return true;
        }

         public static boolean agregarEscuderia(ArrayList<Escuderia> escuderia){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar nombre");

            String nombre = teclado.nextLine();

            System.out.println("Ingresar pais");

            String pais = teclado.nextLine();

         
          // hay que ver aca como se ponen las listas

          // posible opcion


            return true;
        }

        
         public static boolean agregarCircuito(ArrayList<Circuito> circuito){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar nombre");

            String nombre = teclado.nextLine();

            System.out.println("Ingresar longitud");

            double longitud = teclado.nextDouble();

            return true;
        }

        public static boolean agregarPais(ArrayList<Pais> pais){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar id del pais");

            String idpais = teclado.nextLine();

            System.out.println("Ingresar descripcion");

            String descripcion = teclado.nextLine();

            return true;

        }
        public static boolean agregarCarrera(ArrayList<Carrera> carrera){

            Scanner teclado = new Scanner(System.in);

            System.out.println("Ingresar fecha de Realizacion");

            int fechaRealizacion = teclado.nextInt();

            System.out.println("Ingresar numero de Vueltas");

            int numeroVueltas = teclado.nextInt();

            System.out.println("Ingresar hora de Realizacion");

            String horaRealizacion = teclado.nextLine();


            // aca nose si es asi dice planificar
            //aca tambien va circuioto circuito fijate
 
            return true;
        }












     

    }












  
    