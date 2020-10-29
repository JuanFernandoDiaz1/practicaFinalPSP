package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBBDD {
	//Metodo que realiza el select en la bbdd y suma los ingresos
	public void selectIngresos() {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select ingresos from empleados");
			int suma=0;
			while(registro.next()) {
				//sumamos los ingresos mientras haya registros
				suma+=registro.getInt("ingresos");
			}
			conexion.close();
			System.out.println("*********"+suma+"*********");
		} catch (SQLException e) {
			System.out.println("Error en la BBDD");
		}
	}
	
	
	public int selectIngresos2(int rangoInicial, int rangoFinal) {
		int suma=0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select ingresos from empleados where ID >= " + rangoInicial +" and ID < " + rangoFinal);
			while(registro.next()) {
				//sumamos los ingresos mientras haya registros
				suma+=registro.getInt("ingresos");
			}
			conexion.close();
			System.out.println(rangoInicial + "-" + rangoFinal+ " " + "*********"+suma+"*********");
		} catch (SQLException e) {
			System.out.println("Error en la BBDD");
		}
		return suma;
	}
	
	
	public int numeroRegistros() {
		int cont=0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select ingresos from empleados");
			while(registro.next()) {
				cont++;
			}
			conexion.close();
			
		} catch (SQLException e) {
			System.out.println("Error en la BBDD");
		}
		return cont;
	}
	
}
