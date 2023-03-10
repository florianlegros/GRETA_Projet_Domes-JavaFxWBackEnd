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

public class UserDao {
	private Connection con;
	private ObservableList<User> users;

	public UserDao(Connection con) {
		this.con = con;
	}

	public void insere(User user) throws Exception {
		// insert intto ..
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("insert into user (username,password,email) VALUES (" + user.getUsername()
				+ "," + user.getPassword() + "," + user.getEmail() + ");");
	}

	public void supprime(User user) throws Exception {
		// delete ..
		Statement canal = con.createStatement();
		canal.execute("delete from user where id = " + user.getId() + ";");
		users.remove(user);
	}

	public void update(User user) throws Exception {
		// delete ..
		Statement canal = con.createStatement();
		canal.execute("UPDATE user SET username = \"" + user.getUsername() + "\", email = '" + user.getEmail()
				+ "' WHERE user.id = " + user.getId() + "; ");

	}

	public User trouve(int id) throws Exception {
		// select where id =
		User result = null;
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from user where id = " + id + ";");
		while (req.next()) {
			result = new User(req.getInt("id"), req.getString("username"), req.getString("password"),
					req.getString("email"));
		}
		return result;
	}

	public List<User> trouveTout() throws Exception {
		// select *
		List<User> results = new ArrayList<User>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from user" + ";");
		while (req.next()) {
			results.add(new User(req.getInt("id"), req.getString("username"), req.getString("password"),
					req.getString("email")));
		}
		return results;
	}

	public boolean chercheLogin(String user, String pass) throws Exception {

		Statement canal = con.createStatement();
		String req = "select * from user where username = '" + user + "' and password='" + pass + "'    ";
		ResultSet res = canal.executeQuery(req);
		return res.next();

	}

	public ObservableList<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = FXCollections.observableArrayList(users);
	}

}
