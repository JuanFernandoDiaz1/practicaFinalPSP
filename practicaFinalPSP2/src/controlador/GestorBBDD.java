package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vista.Formulario;

public class GestorBBDD {
	//Metodo que realiza el select en la bbdd y suma los ingresos
	public void selectIngresos() {
		Formulario form = new Formulario();
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
			form.errorBBDD();
		}
	}
	
	
	//metodo que realiza el select en la bbdd y suma los ingresos dentro del rango que le pasemos
	public int selectIngresosConHilos(int rangoInicial, int rangoFinal) {
		Formulario form = new Formulario();
		int suma=0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			//sumamos los empleados desde un rango inicial y un rango final para que cada hilo haga x registros
			ResultSet registro = consulta.executeQuery("select ingresos from empleados where ID >= " + rangoInicial +" and ID < " + rangoFinal);
			while(registro.next()) {
				//sumamos los ingresos mientras haya registros
				suma+=registro.getInt("ingresos");
			}
			conexion.close();
		} catch (SQLException e) {
			form.errorBBDD();
		}
		return suma;
	}
	
	
	//metodo que nos devuelve el numero de registros que tenemos en la bbdd
	public int getNumeroRegistros() {
		Formulario form = new Formulario();
		int cont=0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select ingresos from empleados");
			while(registro.next()) {
				//mientras haya registros sumamos 1 al contador
				cont++;
			}
			conexion.close();
			
		} catch (SQLException e) {
			form.errorBBDD();
		}
		return cont;
	}
	
}
