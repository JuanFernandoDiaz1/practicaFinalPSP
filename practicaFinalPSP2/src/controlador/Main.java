package controlador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gestor gestor = new Gestor();
		
		System.out.println("Parte 1");
		gestor.leerSecuencial();
		System.out.println();
		System.out.println("*********************");
		
		System.out.println("");
		System.out.println("Parte 2");
		gestor.leerConcurrente();		
	}
}
