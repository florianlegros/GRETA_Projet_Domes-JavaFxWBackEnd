package fr.greta92.DomesFX;

import java.io.IOException;
import java.net.URL;

import dao.ArticleDao;
import dao.CommandeDao;
import dao.ConnectBd;
import dao.UserDao;
import entity.Article;
import entity.User;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainController {
	@FXML
	TextField id_article, nom_article, prix_article, race_article, id_client, username_client, email_client;
	@FXML
	TextArea description_article, image_article;
	@FXML
	ChoiceBox<String> categorie_article;
	@FXML
	Button btnsupr_article, btnedit_article, btnsupr_client, btnedit_client;
	@FXML
	ListView<Article> Articleliste;
	@FXML
	ListView<User> Clientliste;
	@FXML
	MenuItem menuArticlesClose, menuArticlesAdd, menuClientsClose, menuClientsCommande;
	@FXML
	AnchorPane articleform, clientform;
	@FXML
	HBox formedit_article, formedit_client;

	ArticleDao artd;
	UserDao userd;
	CommandeDao cmdd;

	@FXML
	public void initialize() {

		BooleanBinding noSelection_Article = Bindings.size(Articleliste.getSelectionModel().getSelectedItems())
				.isEqualTo(0);
		formedit_article.disableProperty().bind(noSelection_Article);

		Articleliste.getSelectionModel().getSelectedItems()
				.addListener((ListChangeListener.Change<? extends Article> e) -> {

					if (!e.getList().isEmpty()) {

						id_article.setText("" + e.getList().get(0).getId());
						nom_article.setText("" + e.getList().get(0).getNom());
						prix_article.setText("" + e.getList().get(0).getPrix());
						description_article.setText("" + e.getList().get(0).getDescription());
						categorie_article.setValue("" + e.getList().get(0).getCategorie());
						race_article.setText("" + e.getList().get(0).getRace());
						image_article.setText("" + e.getList().get(0).getImage());
					} else {

						id_article.setText("");
						nom_article.setText("");
						prix_article.setText("");
						description_article.setText("");
						categorie_article.setValue("");
						race_article.setText("");
						image_article.setText("");
					}
				});

		Articleliste.getSelectionModel().select(0);

		btnsupr_article.setOnAction(e -> {
			Alert confirmdel = new Alert(AlertType.CONFIRMATION);
			confirmdel.setContentText("Confirmer la suppression");
			confirmdel.showAndWait();
			if (confirmdel.getResult() == ButtonType.OK)
				try {
					artd.supprime(Articleliste.getSelectionModel().getSelectedItem());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});

		btnedit_article.setOnAction(e -> {
			Article article = Articleliste.getSelectionModel().getSelectedItem();
			article.setNom(nom_article.getText());
			article.setCategorie((String) categorie_article.getValue());
			article.setDescription(description_article.getText());
			article.setPrix(Double.valueOf(prix_article.getText()));
			article.setRace(race_article.getText());
			article.setImage(image_article.getText());
			try {
				artd.update(article);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert editinfo = new Alert(AlertType.INFORMATION);
			editinfo.setTitle("Modification effectué");
			editinfo.setContentText("" + article);
			editinfo.show();
			Articleliste.refresh();

		});

		menuArticlesClose.setOnAction(e -> {
			Platform.exit();
		});

		menuArticlesAdd.setOnAction(e -> {
			try {

				// créer un objet URL - chemin vers le fichier XML
				URL resource = getClass().getResource("ajouterArticle.fxml");
				// objet permet de creer objet java à partir de FXML
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(resource);
				AnchorPane root = (AnchorPane) loader.load();
				// scene.getProperties().put("Articles", artd);

				AjouterArticleController controller = (AjouterArticleController) loader.getController();
				controller.setArticleDao(artd);

				Scene scene = new Scene(root, 600, 600);
				Stage stage = new Stage();

				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		BooleanBinding noSelection_Client = Bindings.size(Clientliste.getSelectionModel().getSelectedItems())
				.isEqualTo(0);
		formedit_client.disableProperty().bind(noSelection_Client);
		menuClientsCommande.disableProperty().bind(noSelection_Client);

		Clientliste.getSelectionModel().getSelectedItems()
				.addListener((ListChangeListener.Change<? extends User> e) -> {

					if (!e.getList().isEmpty()) {

						id_client.setText("" + e.getList().get(0).getId());
						username_client.setText("" + e.getList().get(0).getUsername());
						email_client.setText("" + e.getList().get(0).getEmail());

					} else {

						id_client.setText("");
						username_client.setText("");
						email_client.setText("");
					}
				});

		Clientliste.getSelectionModel().select(0);

		btnsupr_client.setOnAction(e -> {
			Alert confirmdel = new Alert(AlertType.CONFIRMATION);
			confirmdel.setContentText("Confirmer la suppression");
			confirmdel.showAndWait();
			if (confirmdel.getResult() == ButtonType.OK)
				try {
					userd.supprime(Clientliste.getSelectionModel().getSelectedItem());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});

		btnedit_client.setOnAction(e -> {
			User user = Clientliste.getSelectionModel().getSelectedItem();
			user.setUsername(username_client.getText());
			user.setEmail(email_client.getText());

			try {
				userd.update(user);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert editinfo = new Alert(AlertType.INFORMATION);
			editinfo.setTitle("Modification effectué");
			editinfo.setContentText("" + user);
			editinfo.show();
			Articleliste.refresh();

		});

		menuClientsClose.setOnAction(e -> {
			Platform.exit();
		});

		menuClientsCommande.setOnAction(e -> {
			try {

				URL resource = getClass().getResource("commandeClient.fxml");

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(resource);
				AnchorPane root = (AnchorPane) loader.load();

				ConnectBd bd = new ConnectBd();
				bd.connect();
				cmdd = new CommandeDao(bd.getCon());
				cmdd.setCommandes(cmdd.trouveToutClient(Clientliste.getSelectionModel().getSelectedItem().getId()));

				CommandeClientController controller = (CommandeClientController) loader.getController();
				controller.setCommandeDao(cmdd);

				Scene scene = new Scene(root, 700, 400);
				Stage stage = new Stage();

				stage.setScene(scene);
				stage.show();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	public void setArticleDao(ArticleDao artd) {
		this.artd = artd;
		Articleliste.setItems(artd.getArticles());
		categorie_article.setItems(artd.getCategories());
	}

	public void setUserDao(UserDao userd) {
		this.userd = userd;
		Clientliste.setItems(userd.getUsers());

	}

}
