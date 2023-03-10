   package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectBd {
	private Connection con;
	
	public final Connection getCon() {
		return con;
	}

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection( "jdbc:mysql://localhost/DOMES?serverTimezone=UTC","root","");	
		} catch (Exception e) {
			System.out.println("Pb de connection " + e.getMessage());
		}
	}
}