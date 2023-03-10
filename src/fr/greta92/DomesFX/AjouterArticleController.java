package fr.greta92.DomesFX;

import dao.ArticleDao;
import entity.Article;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AjouterArticleController {
	@FXML
	TextField nom, prix, race;
	@FXML
	TextArea description, image;
	@FXML
	ChoiceBox<String> categorie;
	@FXML
	Button btnadd;
	@FXML
	AnchorPane form;

	ArticleDao artd;

	@FXML
	public void initialize() {

		btnadd.setOnAction(e -> {
			if (!nom.getText().isBlank()) {

				try {
					int id = artd.insere(new Article(nom.getText(), Double.valueOf(prix.getText()),
							description.getText(), (String) categorie.getValue(), race.getText(), image.getText()));

					Alert editinfo = new Alert(AlertType.INFORMATION);
					editinfo.setTitle("Nouveau article ajouté");
					editinfo.setContentText("" + artd.trouve(id));
					editinfo.showAndWait();
					((Stage) btnadd.getScene().getWindow()).close();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
	}

	public void setArticleDao(ArticleDao artd) {
		this.artd = artd;

	}
}
