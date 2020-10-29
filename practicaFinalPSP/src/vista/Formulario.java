package vista;

import java.util.Random;

public class Formulario {

	public String generarEmail() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String email = "";
        Random rnd = new Random();
        while (email.length() < 10) {
            int index = (int) (rnd.nextFloat() * letras.length());
            email+=letras.charAt(index);
        }
        
        return email;
    }
}
