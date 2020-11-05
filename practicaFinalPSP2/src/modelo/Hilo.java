package modelo;

import controlador.GestorBBDD;

public class Hilo extends Thread {
	private int suma;
	private int rangoInicial = 0;
	private int rangoFinal = 0;
	
	//constructor por defecto
	public Hilo() {
		this.rangoFinal = 0;
		this.rangoInicial = 0;
		this.suma = 0;
	}

	//constructor al que le pasamos las siguientes variables
	public Hilo(int rangoInicial, int rangoFinal) {
		this.rangoFinal = rangoFinal;
		this.rangoInicial = rangoInicial;
	}

	public void run() {
		//la suma sera la suma de todos los registros que sume este hilo
		GestorBBDD gestor = new GestorBBDD();
		//le pasamos rango inicial y final para controlar el numero de registros que suma
		suma += gestor.selectIngresosConHilos(rangoInicial, rangoFinal);
	}
	
	//con este metodo recogemos la suma
	public int getSuma() {
		return suma;
	}

}
