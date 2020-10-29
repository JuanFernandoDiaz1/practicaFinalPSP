package modelo;

import java.util.concurrent.Semaphore;

import controlador.GestorBBDD;



public class Hilo extends Thread {
	int suma=0;
	private int rangoInicial = 0;
	private int rangoFinal = 0;
	private Semaphore semaforo;

	public Hilo(int rangoInicial, int rangoFinal, Semaphore semaforo) {
		this.rangoFinal = rangoFinal;
		this.rangoInicial = rangoInicial;
		this.semaforo = semaforo;
	}

	public void run() {
		try {
			semaforo.acquire();
			GestorBBDD gestor = new GestorBBDD();
			suma=gestor.selectIngresos2(rangoInicial, rangoFinal);
			semaforo.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getSuma() {
		return suma;
	}
}
