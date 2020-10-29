package modelo;

import controlador.GestorBBDD;

public class Hilo extends Thread {

	public void run() {
		GestorBBDD gest = new GestorBBDD();
		gest.selectIngresos();
	}
}
