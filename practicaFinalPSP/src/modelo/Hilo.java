package modelo;

import java.util.concurrent.Semaphore;
import controlador.GestorBBDD;
import vista.Formulario;

public class Hilo extends Thread {
	private int rangoInicial = 0;
	private int rangoFinal = 0;
	private Semaphore semaforo;

	//Constructor al que le pasamos rango inicial, rango final y un semaforo
	public Hilo(int rangoInicial, int rangoFinal, Semaphore semaforo) {
		this.rangoFinal = rangoFinal;
		this.rangoInicial = rangoInicial;
		this.semaforo = semaforo;
	}

	public void run() {
		GestorBBDD gestor = new GestorBBDD();
		Formulario form = new Formulario();
		//El hilo insertara tantos numeros de registros como rango tenga
		for (int i = rangoInicial; i < rangoFinal; i++) {
			//Genero los ingresos aleatoriamente
			int ingresos = (int) (Math.random() * 1000 + 10);
			//le resto 10 a ingresos para que nunca pase de 1000
			if (ingresos > 20) {
				ingresos -= 10;
			}
			try {
				semaforo.acquire();
				//Creo el email y le añado gmail.com
				String email = form.generarEmail().toLowerCase() + "@gmail.com";
				//Inserto el email y los ingresos
				gestor.insert(email, ingresos);
				semaforo.release();
			} catch (InterruptedException e) {
				System.out.println("Proceso interrumpido");
			}
		}
	}
}
