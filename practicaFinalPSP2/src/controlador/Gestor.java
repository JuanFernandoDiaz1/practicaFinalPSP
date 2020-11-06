package controlador;

import java.util.ArrayList;
import modelo.Hilo;

public class Gestor {

	//Metodo que lee todos los registros y calcula el tiempo que ha tardado
	public void leerSecuencial() {
		
		//Tiempo en el que inicia el metodo
		long tiempoInicio = System.currentTimeMillis();
		GestorBBDD gest = new GestorBBDD();
		int numeroRegistros = gest.getNumeroRegistros();
		//Realizamos el select si hay registros en la BBDD
		if(numeroRegistros==0){
			System.out.println("No hay registros en la BBDD");
		}else {
			int suma=gest.selectIngresos(1, gest.getNumeroRegistros()+1);
			System.out.println("Suma de los ingresos: "+suma);
		}
		
		//Tiempo en el que termina el metodo
		long tiempoFinal = System.currentTimeMillis();
		//Mostramos el tiempo tardado
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
	
	//---------------------------------------------------------------
	public void leerConcurrente() {
		long tiempoInicio = System.currentTimeMillis();
		
		sumarRegistros();
		
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Tiempo que tardo: " + (tiempoFinal - tiempoInicio) + " milisegundos");
	}
	
	public void sumarRegistros() {
		GestorBBDD gestorDataBase = new GestorBBDD();
		ArrayList<Hilo> hilos = new ArrayList<>();
		int rangoInicial = 0;
		int rangoFinal = 0;
		//sacamos el numero de registros que tenemos
		int numRegistros=gestorDataBase.getNumeroRegistros();
		int numHilos=5;
		//si hay registros en la bbdd continuamos
		if(numRegistros==0) {
			System.out.println("No hay registros en la BBDD");
		}else {
			//hacemos el calculo de registros que hara cada hilo
			int resultado = numRegistros / numHilos;
			
			//sacamos el resto para que ningun falle ningun registro
			int resto = numRegistros % numHilos;

			//bucle que ejecuta los 5 hilos
			for (int x = 0; x < 5; x++) {
				//si es el ultimo hilo y el resto es mayor a cero el rango final sera el numero de registros para que el ultimo hilo
				//haga los registros restantes
				if ( x == numHilos - 1) {
					rangoInicial = resultado * x;
					rangoFinal = numRegistros+1;
				}else {
					rangoInicial = resultado * x;
					rangoFinal = resultado * (x + 1);
				}
					//lanzamos los hilos
					Hilo hilo = new Hilo(rangoInicial, rangoFinal);
					hilo.start();
					hilos.add(hilo);
			}
			int suma=0;
			for(int x=0;x<hilos.size();x++) {
				try {
					hilos.get(x).join();
					suma+=hilos.get(x).getSuma();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//mostramos la suma
			System.out.println("Suma de los ingresos: " + suma);
		}
		
	}
}
