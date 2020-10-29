package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBBDD {

	public void selectIngresos() {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");
			Statement consulta = conexion.createStatement();
			ResultSet registro = consulta.executeQuery("select ingresos from empleados");
			int suma=0;
			while(registro.next()) {
				//System.out.println(registro.getInt("ingresos")+"");
				//System.out.println();
				suma+=registro.getInt("ingresos");
			}
			conexion.close();
			System.out.println("*********"+suma+"*********");
		} catch (SQLException e) {
			System.out.println("Error en la BBDD");
		}
		
		
	}
}
