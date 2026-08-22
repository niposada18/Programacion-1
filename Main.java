import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        String nombreUsuario = null;
        String Sueldo = null;
        int numeroTelefono = 0;
        boolean existenRegistro = false;
        do{
            System.out.println("///////////////////////////////");
        System.out.println("______________");
        System.out.println("|    MENU      |");
        System.out.println("|  1. Crear    | ");
        System.out.println("|  2. Ver      |");
        System.out.println("|  3. Editar   |");
        System.out.println("|  4. Eliminar |");
        System.out.println("|  5. Terminar |");
        System.out.println("________________");
        
        System.out.println("Ingrese una opcion: ");
        opcion = teclado.nextInt();
        teclado.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("_________________________________");
                System.out.print("| Ingrese su nombre de usuario:  ");
                nombreUsuario = teclado.nextLine();
                System.out.print("| ingrese sueldo:                ");
                Sueldo = teclado.nextLine();
                System.out.print("| Ingrese numero de telefono:     ");
                numeroTelefono = teclado.nextInt();
                System.out.println("|________________________________|");

                existenRegistro = true;
                System.out.println("Datos creados con exito");
                
                break;
            case 2:
                if (existenRegistro){
                    System.err.println("____________________________");
                    System.out.println("| Sus datos actuales son    ");
                    System.out.println("| Nombre:" + nombreUsuario);
                    System.out.println("| Sueldo:" + Sueldo );
                    System.out.println("| Numero de telefono:" + numeroTelefono);
                    System.err.println("_____________________________");
                }       
                else{
                    System.out.println("No hay datos registrados");
                } 
                break;
            case 3:
                if(existenRegistro){
                 System.out.println("Ingrese sus nuevos Datos");
                
                 System.out.print("Nombre actual: " + nombreUsuario + "  1Nombre nuevo: ");
                 nombreUsuario = teclado.nextLine();
                 System.out.print("Nuevo sueldo:");
                 Sueldo = teclado.nextLine();
                 System.out.print("Nuevo numero de telefono: ");
                 numeroTelefono = teclado.nextInt();
                }
             else {
                    System.out.println("No hay datos para editar.");
                }
            break;
        case 4:
            if (existenRegistro){
                nombreUsuario = null;
                Sueldo = null;
                numeroTelefono = 0;
                System.out.println("Los datos han sido eliminados!");
                existenRegistro = false;
            }
            else{
                System.out.println("No hay datos para eliminar");
            }
            break;

        case 5:
            System.out.println("Cerrando el programa...");
            System.out.println("Porgrama cerrado!");
            break;

        }
          }
    
    while(opcion!=5);
}
}
