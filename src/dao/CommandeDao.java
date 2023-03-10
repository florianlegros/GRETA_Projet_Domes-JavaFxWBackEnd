package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Article;
import entity.Commande;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandeDao {
	private Connection con;
	private ObservableList<Commande> Commandes;

	public CommandeDao(Connection con) {
		this.con = con;
	}

	public void update(Commande cmd) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("UPDATE commande SET status = '" + cmd.getStatus() + "' WHERE id = " + cmd.getId() + ";");
	}

	public Commande trouve(int id) throws Exception {
		Commande result = null;
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from commande where id = " + id + ";");
		while (req.next()) {
			result = new Commande(req.getInt("id"), req.getString("date"), req.getString("adresse"),
					req.getString("status"),
					new Article(req.getInt("article_id"), req.getString("nom"), req.getDouble("prix"),
							req.getString("description"), req.getString("categorie"), req.getString("race"),
							req.getString("image")));
		}
		return result;
	}

	public List<Commande> trouveToutClient(int user) throws Exception {
		// select *
		List<Commande> results = new ArrayList<Commande>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery(
				"select * from commande JOIN article ON commande.id = article.id_commande JOIN adresse ON commande.adresse_id = adresse.id WHERE commande.user_id="
						+ user + ";");
		while (req.next()) {
			results.add(new Commande(req.getInt("commande.id"), req.getString("date"), req.getString("adresse"),
					req.getString("status"),
					new Article(req.getInt("article.id"), req.getString("nom"), req.getDouble("prix"),
							req.getString("description"), req.getString("categorie"), req.getString("race"),
							req.getString("image"))));
		}
		return results;
	}

	public ObservableList<String> getStatus() {

		try {
			String result = "";
			Statement canal = con.createStatement();
			ResultSet req = canal.executeQuery(
					"SELECT TRIM(TRAILING ')' FROM TRIM(LEADING '(' FROM TRIM(LEADING 'enum' FROM column_type))) column_type FROM information_schema.columns WHERE table_name = 'commande' AND column_name = 'status'; ");
			while (req.next()) {
				result = req.getString("column_type").replace("'", "");
			}
			List<String> resultAsList = Arrays.asList(result.split(","));
			return FXCollections.observableArrayList(resultAsList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ObservableList<Commande> getCommandes() {
		return Commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.Commandes = FXCollections.observableArrayList(commandes);
	}

}
