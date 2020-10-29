package controlador;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import modelo.Hilo;

public class Gestor {

	//Metodo que lee todos los registros y calcula el tiempo que ha tardado
	public void leerSecuencial() {
		
		//Tiempo en el que inicia el metodo
		long tiempoInicio = System.currentTimeMillis();
		GestorBBDD gest = new GestorBBDD();
		//Realizamos el select
		gest.selectIngresos();
		//Tiempo en el que termina el metodo
		long tiempoFinal = System.currentTimeMillis();
		//Mostramos el tiempo tardado
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
	
	//---------------------------------------------------------------
	public void parte2() {
		long tiempoInicio = System.currentTimeMillis();
		
		klk();
		
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
	
	public void klk() {
		GestorBBDD gestorDataBase = new GestorBBDD();
		ArrayList<Hilo> hilos = new ArrayList<Hilo>();
		int suma=0;
		int rangoInicial = 0;
		int rangoFinal = 0;
		int numRegistros=gestorDataBase.numeroRegistros();
		
		int numHilos=5;
		int resultado = numRegistros / numHilos;
	
		int resto = numRegistros % numHilos;
		Semaphore semaforo = new Semaphore(1);
		for (int x = 0; x < 5; x++) {
			if (resto > 0 && x == numHilos - 1) {
				rangoInicial = resultado * x;
				rangoFinal = numRegistros+1;
			}else {
				rangoInicial = resultado * x;
				rangoFinal = resultado * (x + 1);
			}
		
			Hilo hilo = new Hilo(rangoInicial, rangoFinal, semaforo);
			hilo.start();
			System.out.println(hilo.getSuma());
		}
		
		for(int x = 0; x<hilos.size(); x++) {
			suma+=hilos.get(x).getSuma();
		}
		System.out.println(suma);
	}
}
