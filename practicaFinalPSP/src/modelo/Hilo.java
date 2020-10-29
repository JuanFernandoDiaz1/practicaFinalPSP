package modelo;

import java.math.BigInteger;
import java.util.concurrent.Semaphore;

import controlador.GestorBBDD;
import vista.Formulario;

public class Hilo extends Thread {
	private int rangoInicial = 0;
	private int rangoFinal = 0;
	private Semaphore semaforo;

	public Hilo(int rangoInicial, int rangoFinal, Semaphore semaforo) {
		this.rangoFinal = rangoFinal;
		this.rangoInicial = rangoInicial;
		this.semaforo = semaforo;
	}

	public void run() {

		GestorBBDD gest = new GestorBBDD();
		Formulario form = new Formulario();

		
		for (int i = rangoInicial; i < rangoFinal; i++) {
			int ingresos = (int) (Math.random() * 1000 +10);
			if(ingresos>20) {
				ingresos-=10;
			}
			
			try {
				semaforo.acquire();
				String email = form.generarEmail().toLowerCase() + "@gmail.com";
				gest.insert(email, ingresos);
				
				semaforo.release();
			} catch (InterruptedException e) {

			}

		}

	}
}
