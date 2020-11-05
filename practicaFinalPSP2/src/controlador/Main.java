package controlador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Parte 1");
		Gestor gestor = new Gestor();
		gestor.leerSecuencial();
		System.out.println("*********************");
		
		System.out.println("");
		System.out.println("Parte 2");
		gestor.leerConcurrente();		
	}
}
