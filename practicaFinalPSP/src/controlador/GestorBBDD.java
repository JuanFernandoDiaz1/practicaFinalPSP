package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBBDD {
	public void insert(String email, int ingresos) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP",
					"DAM2020_PSP");

			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into EMPLEADOS (email, ingresos) values ('"
					+ email + "', "+ ingresos+")");
			conexion.close();
			System.out.println("Insertado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
