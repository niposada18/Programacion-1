import java.util.Scanner;
public class Menudatos {
    public static String[] nombre = new String[100];    // guardar el nombre de cada empleado (capacidad máxima: 100)
    public static float[] sueldo = new float[100];  //guardar el sueldo de cada empleado (mismo índice que 'nombre')
    public static String[] cargo = new String[100]; //guardar el cargo de cada empleado (mismo índice que 'nombre')
    public static int contador = 0;
    public static Scanner entrada = new Scanner(System.in);

    /**
     * Punto de entrada del programa. Muestra el menú repetidamente
     * hasta que el usuario elija la opción 5 (Terminar).
     */
    public static void main(String[] args) {
        int opcion; // Guarda la opción elegida por el usuario en cada vuelta del menú

        do {
            encabezado();           
            opcion = menu("1- Crear\n2- Ver\n3- Editar\n4- Borrar\n5- Salir", 5); // Se pasamos el texto de las opciones y la cantidad de opciones válidas (5)
            switch (opcion) {
                case 1:
                    menuCrear();   // Registrar uno o varios empleados nuevos
                    break;
                case 2:
                    menuVer();     // Mostrar todos los empleados registrados
                    break;
                case 3:
                    menuEditar();  // Modificar los datos de un empleado existente
                    break;
                case 4:
                    menuBorrar();  // Eliminar un empleado de la lista
                    break;
                case 5: // "Salir"
                    System.out.println("\n¡Gracias por utilizar el programa!");
                    break;
                default:
                    // Si el usuario ingresa un número fuera del 1-5
                    // (por ejemplo 8 o -1), caemos aquí.
                    System.out.println("\nOpción inválida, intente de nuevo.");
            }
        } while (opcion != 5); // Repetimos el menú mientras no se elija "Terminar"
        entrada.close();
    }

    /* Imprime un encabezado decorativo. Solo es imagen
      no afecta la lógica del programa.     */
    public static void encabezado() {
        System.out.println("\n---------------------------");
        System.out.println("| -Datos de empleados ");
        System.out.println("-----------------------------");
    }

    public static int menu(String opciones, int cantidadOpciones) {
        System.out.println(opciones);
        System.out.print("Seleccione una opción: ");

        // hasNextInt() revisa si lo próximo que el usuario escribió
        // es un número entero, SIN consumirlo todavía.
        // Si escribió letras, entramos al while y le pedimos de nuevo.
        while (!entrada.hasNextInt()) {
            System.out.println("Por favor ingrese un número válido.");
            entrada.next(); // descartamos lo que escribió mal (ej: "hola")
            System.out.print("Seleccione una opción: ");
        }

        int opcion = entrada.nextInt(); // ahora sí leemos el número
        entrada.nextLine(); 

        // Validamos también que el número esté dentro del rango permitido
        // (1 a cantidadOpciones). Si el usuario ingresa, por ejemplo, 9,
        // no lo dejamos pasar: se lo volvemos a pedir.
        while (opcion < 1 || opcion > cantidadOpciones) {
            System.out.println("Opción fuera de rango. Intente de nuevo.");
            System.out.println(opciones);
            System.out.print("Seleccione una opción: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                entrada.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = entrada.nextInt();
            entrada.nextLine();
        }

        return opcion;
    }

    public static void menuCrear() {
        // Si ya no hay espacio en los arrays (los 100 lugares ocupados),
        // avisamos y salimos sin hacer nada más.
        if (contador >= nombre.length) {
            System.out.println("Error: El sistema alcanzó la capacidad máxima (100 empleados).");
            return;
        }

        System.out.print("\n¿Cuántos empleados desea crear? ");
        while (!entrada.hasNextInt()) {
            System.out.println("Ingrese un número válido.");
            entrada.next();
            System.out.print("¿Cuántos empleados desea crear? ");
        }
        int cantidad = entrada.nextInt();
        entrada.nextLine(); // limpiar el "Enter" pendiente, igual que en menu()

        if (cantidad <= 0) {
            System.out.println("Cantidad inválida.");
            return;
        }

        // Calculamos cuántos espacios libres quedan realmente.
        // Ejemplo: si el array tiene 100 lugares y ya hay 98 empleados,
        // solo quedan 2 espacios, aunque el usuario pida crear 5.
        int espacioDisponible = nombre.length - contador;
        if (cantidad > espacioDisponible) {
            System.out.println("Solo hay espacio para " + espacioDisponible + " empleado(s) más. Se crearán esos.");
            cantidad = espacioDisponible; // ajustamos para no salirnos del array
        }

        // Bucle que registra "cantidad" empleados nuevos, uno por uno.
        // Noten que NO reiniciamos 'contador' a 0 en ningún momento:
        // seguimos usándolo y aumentándolo, por eso no se pierde nada.
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- REGISTRAR EMPLEADO #" + (contador + 1) + " ---");
            leerDatos(contador); // guarda nombre, cargo y sueldo en la posición 'contador'
            contador++;          // avanzamos a la siguiente posición libre
        }

        System.out.println("\n-> " + cantidad + " empleado(s) registrado(s) con éxito.");
    }


    // ------------------- VER (Read) -------------------

    /**
     * Recorre todos los empleados registrados (desde la posición 0
     * hasta contador-1) y muestra sus datos usando verDatos().
     */
    public static void menuVer() {
        if (contador == 0) {
            System.out.println("\nLa lista de empleados está vacía.");
            return;
        }
        System.out.println("\n--- LISTA DE EMPLEADOS REGISTRADOS ---");
        for (int i = 0; i < contador; i++) {
            System.out.println("\nID / Posición: " + i);
            verDatos(i);
            System.out.println("---------------------------------");
        }
    }


    // ------------------- EDITAR (Update) -------------------

    /**
     * Permite modificar los datos de un empleado ya existente.
     * Primero muestra la lista completa (para que el usuario vea
     * qué posición corresponde a quién) y luego pide el índice
     * a editar.
     */
    public static void menuEditar() {
        if (contador == 0) {
            System.out.println("\nNo hay empleados para actualizar.");
            return;
        }

        menuVer(); // mostramos la lista para que el usuario elija con seguridad

        System.out.print("\nIngrese la posición/ID del empleado a modificar (0 a " + (contador - 1) + "): ");
        if (!entrada.hasNextInt()) {
            System.out.println("Entrada inválida.");
            entrada.nextLine(); // descartamos lo que se escribió mal
            return;
        }
        int index = entrada.nextInt();
        entrada.nextLine();

        // Validamos que el índice esté dentro del rango de empleados
        // realmente registrados (no del tamaño total del array, que es 100,
        // sino de 'contador', que es cuántos hay de verdad).
        if (index >= 0 && index < contador) {
            System.out.println("\nDatos actuales:");
            verDatos(index);
            System.out.println("\nIngrese los nuevos datos:");
            leerDatos(index); // sobrescribe nombre, cargo y sueldo en esa posición
            System.out.println("-> Empleado actualizado con éxito.");
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }


    // ------------------- BORRAR (Delete) -------------------

    /**
     * Elimina un empleado de la lista. Como los arrays no pueden
     * "encoger" en Java, lo que hacemos es DESPLAZAR todos los
     * empleados que están después del eliminado, una posición
     * hacia la izquierda, para cerrar el hueco. Al final,
     * reducimos 'contador' en 1.
     *
     * Ejemplo con 4 empleados (posiciones 0,1,2,3) si borramos el 1:
     *   Antes:   [Ana, Luis, Marta, Pedro]
     *   Pasamos Marta a la posición 1, Pedro a la posición 2
     *   Después: [Ana, Marta, Pedro, (vacío)]
     *   contador pasa de 4 a 3
     */
    public static void menuBorrar() {
        if (contador == 0) {
            System.out.println("\nNo hay empleados registrados para eliminar.");
            return;
        }

        menuVer(); // mostramos la lista para elegir con seguridad

        System.out.print("\nIngrese la posición/ID del empleado a eliminar (0 a " + (contador - 1) + "): ");
        if (!entrada.hasNextInt()) {
            System.out.println("Entrada inválida.");
            entrada.nextLine();
            return;
        }
        int index = entrada.nextInt();
        entrada.nextLine();

        if (index >= 0 && index < contador) {
            // Desplazamos cada elemento una posición hacia la izquierda,
            // empezando desde la posición eliminada.
            for (int i = index; i < contador - 1; i++) {
                nombre[i] = nombre[i + 1];
                cargo[i] = cargo[i + 1];
                sueldo[i] = sueldo[i + 1];
            }

            // Limpiamos la última posición, que quedó duplicada
            // después del desplazamiento (ya no debe "contar" como dato válido).
            nombre[contador - 1] = null;
            cargo[contador - 1] = null;
            sueldo[contador - 1] = 0;

            contador--; // ahora hay un empleado menos
            System.out.println("-> Empleado eliminado correctamente.");
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }


    // ------------------- MÉTODOS DE APOYO (helpers) -------------------

    /**
     * Imprime en pantalla los datos (nombre, cargo, sueldo) del
     * empleado ubicado en la posición 'nroEmpleado'.
     *
     * @param nroEmpleado índice del empleado dentro de los arrays
     */
    public static void verDatos(int nroEmpleado) {
        System.out.println("Nombre del empleado: " + nombre[nroEmpleado]);
        System.out.println("Cargo del empleado: " + cargo[nroEmpleado]);
        System.out.println("Sueldo del empleado: " + sueldo[nroEmpleado]);
    }


    public static void leerDatos(int nroEmpleado) {
        System.out.println("Ingrese su nombre: ");
        nombre[nroEmpleado] = entrada.nextLine();

        System.out.println("Ingrese su cargo: ");
        cargo[nroEmpleado] = entrada.nextLine();

        System.out.println("Ingrese su sueldo: ");
        // Validamos que lo ingresado sea un número decimal válido.
        // Si el usuario escribe letras por error, se lo volvemos a pedir
        // en vez de que el programa se caiga con una excepción.
        while (!entrada.hasNextFloat()) {
            System.out.println("Ingrese un número válido para el sueldo.");
            entrada.next(); // descartamos el valor inválido
        }
        sueldo[nroEmpleado] = entrada.nextFloat();
        entrada.nextLine(); // limpiamos el "Enter" pendiente, como en los otros métodos
    }
}
