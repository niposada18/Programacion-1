//Actividad 
import java.util.Scanner; //importamos libreria para leer datos del teclado
 
public class Actividad2 {
	private static final Scanner scanner = new Scanner(System.in);
 
	public static void main(String[] args) {
		int opcion;
      
		do {  //bucle principal se repetira todo mientras la opcion no sea 3 (salir)
			opcion = menu("1- Calcular un área\n2- Calcular un volumen\n3- Salir", 3);
            //dependiendo de la eleccion se mostraran los menus de eleccion o el mensaje de salida del programa
			switch (opcion) {
				case 1: menuAreas();  //
                 break;
				case 2: menuVolumenes();
                 break;
				case 3: System.out.println("\n¡Gracias por utilizar el programa!");
                 break;
			}
		} while (opcion != 3);
 
		scanner.close();  //se cerrara al terminar el programa 
	}
    
    //encabezado decorativo que aparecera en la parte de arriba de cada menu
	public static void encabezado() {
		System.out.println("\n---------------------------");
		System.out.println("| -Áreas y Volúmenes- ");
		System.out.println("-----------------------------");
	}
/*
Funcion que imprime un menu de n numero de opciones(determinado por nroOpciones)
 */
	public static int menu(String texto, int nroOpciones) {
		int opcion;
		do {
			encabezado();
			System.out.println(texto);
			System.out.print("Seleccione una opción: ");
                //Si se escribe algo que no es numero entero se descarta y se volvera a pedir un numero
			while (!scanner.hasNextInt()) {
				System.out.print("Ingrese un número entero válido: ");
				scanner.next();
			}
			opcion = scanner.nextInt();
            
           //se valida si la opcion esta dentro del rango permitido de nroOpciones
			if (opcion > nroOpciones) {
				System.out.println("Opción fuera de rango. Intente nuevamente.");
			}
		} while (opcion > nroOpciones); //se repite hasta que la opcion sea valida
 
		return opcion;
	}
    
    //menu para el calculo de areas
    //se pedira que escoja la figura, menu() hara que la opcion sea entre 1 y 4
	public static void menuAreas() {
		int opcion = menu("1- Cuadrado\n2- Triángulo\n3- Círculo\n4- Hexágono", 4);
		double resultado;
 
		switch (opcion) {
			case 1:     //----Cuadrado
				double ladoC = leerPositivo("Lado: ");
				resultado = ladoC * ladoC;    //La area de un cuadrado es lado^2
				mostrarResultado("Área del cuadrado", resultado);
			break;
			case 2:    //----Triangulo
				double base = leerPositivo("Base: ");
				double altura = leerPositivo("Altura: ");
				resultado = base * altura / 2;
				mostrarResultado("Área del triángulo", resultado);
			break;
			case 3:   //----Circulo
				double radio = leerPositivo("Radio: ");
				resultado = Math.PI * radio * radio;   //Area de un circulo = pi*radio^2
				mostrarResultado("Área del círculo", resultado);
			break;
			case 4:   //---- Hexagono
				double lado = leerPositivo("Lado: ");
				resultado = (3 * Math.sqrt(3) / 2) * lado * lado; //Area de un hexagono
				mostrarResultado("Área del hexágono", resultado);
			break; 
		}  //fin del switch para calculo de areas
	}
 
    //Menu para calculo de volumen de figuras
    //Se pedira elejir una forma y luego las medidas correspondientes
	public static void menuVolumenes() {
		int opcion = menu("1- Cubo\n2- Cilindro\n3- Esfera", 3);
		double resultado;
 
		switch (opcion) {
			case 1:  //----Cubo
				double lado = leerPositivo("Lado: ");
				resultado = lado * lado * lado;
				mostrarResultado("Volumen del cubo", resultado);
			break;
			case 2:  //----Cilindro
				double radio2 = leerPositivo("Radio: ");   //Uso de radio2 para radio (aclara variables)
				double altura = leerPositivo("Altura: ");
				resultado = Math.PI * radio2 * radio2 * altura;
				mostrarResultado("Volumen del cilindro", resultado);
			break;
			case 3:  //----Esfera
				double radio = leerPositivo("Radio: ");
				resultado = (4.0 / 3) * Math.PI * radio * radio * radio;
				mostrarResultado("Volumen de la esfera", resultado);
			break;
		}  //Fin del switch para calculo de volumen
	}
 

    /* Pide un valor en forma de numero valido hasta que sea mayor que cero y un double
    se usa para las medidas (lado, radios, alturas)
     */
	public static double leerPositivo(String mensaje) {
		double valor;
		do {
			System.out.print(mensaje);   //si se ingresa texto en vez de numero se descarta y se volvera a intentar
			while (!scanner.hasNextDouble()) {
				System.out.print("Ingrese un número válido: ");
				scanner.next();
			}
			valor = scanner.nextDouble();
			if (valor <= 0) {
				System.out.println("El valor debe ser mayor que cero.");
			}
		} while (valor <= 0);  //repetir hasta que el valor sea mayor que cero
		return valor;
	}
  //se imprimira el resultado final con dformato de 2 decimales 
	public static void mostrarResultado(String figura, double resultado) {
		System.out.printf("%s: %.2f unidades%n", figura, resultado);
	}
}
 