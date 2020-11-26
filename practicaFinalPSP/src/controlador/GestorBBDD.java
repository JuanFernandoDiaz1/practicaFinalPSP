package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBBDD {
	//Metodo que realiza el insert en la BBDDD
	public void insert(String email, int ingresos) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");

			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(
					"insert into EMPLEADOS (EMAIL, INGRESOS) values ('" + email + "', " + ingresos + ")");
			conexion.close();
			
			System.out.println("Registros insertados correctamente");
		} catch (SQLException e) {
			System.out.println("Error en la BBDD");
		}
	}
}
