package controlador;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import modelo.Hilo;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Registros: ");
		int numRegistros = teclado.nextInt();
		
		System.out.print("Hilos: ");
		int numHilos = teclado.nextInt();
		
		int inicial = 0;
		int valorFinal = 0;
		int resultado = numRegistros/numHilos;
		int resto=numRegistros%numHilos;
		Semaphore semaforo = new Semaphore(1);
		
		for(int x = 0; x<numHilos;x++) {
			if(resto>0&&x==numHilos-1) {
				resultado+=resto;
			}
			inicial=resultado*x;
			valorFinal=resultado*(x+1);
			System.out.println("Thread-" + x );
			System.out.println(inicial+" " + valorFinal + " " + resto);
			Hilo hilo = new Hilo(inicial, valorFinal, semaforo);
			hilo.start();
		}
	}
}
