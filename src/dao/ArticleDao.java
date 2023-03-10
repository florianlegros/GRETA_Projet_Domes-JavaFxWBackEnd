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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArticleDao {
	private Connection con;
	private ObservableList<Article> Articles;
	
	public ArticleDao(Connection con) {
		this.con = con;
	}

	public int insere(Article art) {
		// insert intto ..

		try {
			ResultSet result;
			PreparedStatement prep;
			String query = "insert into article (nom,prix,description,categorie,race,image) VALUES " + "(\""
					+ art.getNom() + "\",\"" + art.getPrix() + "\",\"" + art.getDescription() + "\",\""
					+ art.getCategorie() + "\",\"" + art.getRace() + "\",\"" + art.getImage() + "\");";

			prep = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.execute();
			result = prep.getGeneratedKeys();

			if (result.next() && result != null) {
				art.setId(result.getInt(1));
			}

			Articles.add(art);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return art.getId();
	}

	public void supprime(Article art) throws Exception {
		// delete ..
		Statement canal = con.createStatement();
		canal.execute("delete from article where id = " + art.getId() + ";");
		Articles.remove(art);
	}

	public void update(Article art) throws Exception {
		// delete ..
		Statement canal = con.createStatement();
		canal.execute("UPDATE article SET nom = \"" + art.getNom() + "\", prix = \"" + art.getPrix()
				+ "\", description = \"" + art.getDescription() + "\", categorie = \"" + art.getCategorie()
				+ "\", race = \"" + art.getRace() + "\", image = \"" + art.getImage() + "\" " + "WHERE article.id = "
				+ art.getId() + "; ");
		
	}

	public Article trouve(int id) throws Exception {
		// select where id =
		Article result = null;
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from article where id = " + id + ";");
		while (req.next()) {
			result = new Article(req.getInt("id"), req.getString("nom"), req.getDouble("prix"),
					req.getString("description"), req.getString("categorie"), req.getString("race"),
					req.getString("image"));
		}
		return result;
	}

	public List<Article> trouveTout() throws Exception {
		// select *
		List<Article> results = new ArrayList<Article>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from article" + ";");
		while (req.next()) {
			results.add(new Article(req.getInt("id"), req.getString("nom"), req.getDouble("prix"),
					req.getString("description"), req.getString("categorie"), req.getString("race"),
					req.getString("image")));
		}
		return results;
	}
	
	public ObservableList<String> getCategories() {

		try {
			String result = "";
			Statement canal = con.createStatement();
			ResultSet req = canal.executeQuery("SELECT TRIM(TRAILING ')' FROM TRIM(LEADING '(' FROM TRIM(LEADING 'enum' FROM column_type))) column_type FROM information_schema.columns WHERE table_name = 'article' AND column_name = 'categorie'; ");
			while (req.next()) {
				result = req.getString("column_type").replace("'","");
			}
			List<String> resultAsList = Arrays.asList(result.split(","));
			return FXCollections.observableArrayList(resultAsList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ObservableList<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.Articles = FXCollections.observableArrayList(articles);
	}

}
