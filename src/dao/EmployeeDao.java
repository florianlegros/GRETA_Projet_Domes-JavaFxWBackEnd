package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Article;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeDao {
	private Connection con;

	public EmployeeDao(Connection con) {
		this.con = con;
	}


	public boolean chercheLogin(String email, String pass) throws Exception {

		Statement canal = con.createStatement();
		String req = "select * from employee where email = '" + email + "' and password='" + pass + "'    ";
		ResultSet res = canal.executeQuery(req);
		return res.next();

	}


}
