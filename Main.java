import java.util.Scanner; // Se importa la libreria scanner para poder leer datos del teclado

public class Main {
        public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);   //Se crea el objeto scanner que leera lo que el usuario escriba

        // Se declaran variables fuera del ciclo do-while para mantener su valor
        int opcion;
        String nombreUsuario = null;
        String Sueldo = null;
        int numeroTelefono = 0;
        boolean existenRegistro = false;              // se inicia con exitenRegistro = false para  indicar que no se ha creado un usuario
        do{
            //Creacion de menu unicamente visual
            System.out.println("///////////////////////////////");
         System.out.println("______________");
         System.out.println("|    MENU      |");
         System.out.println("|  1. Crear    | ");
         System.out.println("|  2. Ver      |");
         System.out.println("|  3. Editar   |");
         System.out.println("|  4. Eliminar |");
         System.out.println("|  5. Terminar |");
         System.out.println("________________");
        
         System.out.println("Ingrese una opcion: ");   //Le pedimos al usuario ingresar una opcion del menu 
         opcion = teclado.nextInt();                     // Leemos el numero ingresado por el usuario 
         teclado.nextLine();
         switch (opcion) {                               // Segun numero escrito se saltara al caso correspondiente
            case 1:                                     // ----CREAR----
                                                        // Se creara un perfil con los siguientes datos
                System.out.println("_________________________________");
                System.out.print("| Ingrese su nombre de usuario:  ");
                nombreUsuario = teclado.nextLine();     // Leeremos el nombre ingresado (como texto)
                System.out.print("| ingrese sueldo:                ");
                Sueldo = teclado.nextLine();
                System.out.print("| Ingrese numero de telefono:     ");
                numeroTelefono = teclado.nextInt();
                System.out.println("|________________________________|");

                existenRegistro = true;                 // Luego de tener los datos ingresados se marcara que hay datos creados
                System.out.println("Datos creados con exito");
                
                break;                                 // Salimos del switch
            case 2:
                if (existenRegistro){                  //Si existen registros creados se mostrara el siguiente menu
                    System.err.println("____________________________");
                    System.out.println("| Sus datos actuales son    ");
                    System.out.println("| Nombre:" + nombreUsuario);
                    System.out.println("| Sueldo:" + Sueldo );
                    System.out.println("| Numero de telefono:" + numeroTelefono);
                    System.err.println("_____________________________");
                }       
                else{                                  //Si no se ha creado nada, no hay datos para mostrar
                    System.out.println("No hay datos registrados");
                } 
                break;
            case 3:                                   //-------EDITAR-------
                if(existenRegistro){                  //si exiten datos creados anteriormente se mostrara las siguientes preguntas
                 System.out.println("Ingrese sus nuevos Datos");
                
                 System.out.print("Nombre actual: " + nombreUsuario + "  1Nombre nuevo: ");
                 nombreUsuario = teclado.nextLine();
                 System.out.print("Nuevo sueldo:");
                 Sueldo = teclado.nextLine();
                 System.out.print("Nuevo numero de telefono: ");
                 numeroTelefono = teclado.nextInt();
                }
             else {                                  //si no existen datos no se editara nada
                    System.out.println("No hay datos para editar.");
                }
            break;    //salimos del switch
         case 4:                                     //------ELIMINAR----
            if (existenRegistro){                   //De haber registros guardados se eliminaran
                nombreUsuario = null;
                Sueldo = null;
                numeroTelefono = 0;
                System.out.println("Los datos han sido eliminados!");
                existenRegistro = false;            //Se cambiara existenRegistros a falso para volver a iniciar
            }
            else{
                System.out.println("No hay datos para eliminar"); //si no hay datos registrados no se eliminara nada
            }
            break;  //salimos del switch

         case 5:                        //------TERMINAR-----
            System.out.println("Cerrando el programa...");
            System.out.println("Porgrama cerrado!");
            break;

         default:                       //si se coloca otro numero fuera de (1-5) se mostrara lo siguiente
            System.out.println("opcion invalida, Ingrese otra opcion");

        } //Fin de switch
          }
    
    while(opcion!=5); //Mientras la "opcion" sea diferente a 5 se repetira el bloque do{}
}
}
