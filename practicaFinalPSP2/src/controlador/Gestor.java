package controlador;

import modelo.Hilo;

public class Gestor {

	
	public void parte1() {
		GestorBBDD gest = new GestorBBDD();
		long tiempoInicio = System.currentTimeMillis();
		gest.selectIngresos();
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
	
	public void parte2() {
		long tiempoInicio = System.currentTimeMillis();
		Hilo hilo = new Hilo();
		Hilo hilo2 = new Hilo();
		Hilo hilo3 = new Hilo();
		Hilo hilo4 = new Hilo();
		Hilo hilo5 = new Hilo();
		
		hilo.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		hilo5.start();
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
}
