package vista;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import modelo.Hilo;

public class Formulario {

	public String generarEmail() {
		// Creo un string con todas las letras y numeros del 0 al 9
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String email = "";
		Random rnd = new Random();
		while (email.length() < 10) {
			// Creo un indice que pasara a una posicion aleatoria del string letras
			int index = (int) (rnd.nextFloat() * letras.length());
			// Almaceno el email en el string email
			email += letras.charAt(index);
		}
		// Devuelvo el email
		return email;
	}

	public void insertarRegistros() {
		Scanner teclado = new Scanner(System.in);
		boolean valid = false;
		int numRegistros = 0;
		int numHilos = 0;

		// Pido el numero de registros asegurandome de que es un numero
		while (!valid) {
			try {
				System.out.print("Registros: ");
				numRegistros = teclado.nextInt();
				valid = true;
				System.out.println("");
			} catch (InputMismatchException e) {
				teclado.nextLine();
				System.out.println("Introduce un numero valido");
				System.out.println("");
			}

		}
		// Pido el numero de hilos asegurandome de que es un numero
		valid = false;
		while (!valid) {
			try {
				System.out.print("Hilos: ");
				numHilos = teclado.nextInt();
				valid = true;
				System.out.println("");
			} catch (InputMismatchException e) {
				teclado.nextLine();
				System.out.println("Introduce un numero valido");
				System.out.println("");
			}
		}

		int rangoInicial = 0;
		int rangoFinal = 0;
		// La variable resultado nos ayudara a calcular los rangos sobre los que vamos a
		// trabajar
		int resultado = numRegistros / numHilos;
		// la variable resto son el numero de registros que se quedarian fuera del rango
		int resto = numRegistros % numHilos;
		Semaphore semaforo = new Semaphore(1);
		// Controlamos el numero de hilos que trabajaran
		for (int x = 0; x < numHilos; x++) {
			// Si el resto es mayor a 0 se lo sumamos al ultimo hilo para que haga estos
			// ultimos registros
			if (resto > 0 && x == numHilos - 1) {
				resultado += resto;
			}
			// Calculo los rangos
			rangoInicial = resultado * x;
			rangoFinal = resultado * (x + 1);
			// Inicio el hilo
			Hilo hilo = new Hilo(rangoInicial, rangoFinal, semaforo);
			hilo.start();
		}
	}
}
