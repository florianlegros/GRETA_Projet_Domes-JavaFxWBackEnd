package fr.greta92.DomesFX;

import dao.ArticleDao;
import dao.CommandeDao;
import entity.Article;
import entity.Commande;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CommandeClientController {
	@FXML
	ListView<Commande> listcommandes;
	@FXML
	ChoiceBox<String> statusCommande;
	@FXML
	Button changeStatusCommande;
	CommandeDao cmdd;

	@FXML
	public void initialize() {

		listcommandes.getSelectionModel().getSelectedItems()
				.addListener((ListChangeListener.Change<? extends Commande> e) -> {
					if (!e.getList().isEmpty()) {
						statusCommande.setValue("" + e.getList().get(0).getStatus());
					} else {
						statusCommande.setValue("");
					}
				});
		changeStatusCommande.setOnAction(e -> {
					Commande commande = listcommandes.getSelectionModel().getSelectedItem();
					commande.setStatus((String) statusCommande.getValue());
					try {
						cmdd.update(commande);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Alert editinfo = new Alert(AlertType.INFORMATION);
					editinfo.setTitle("Modification effectué");
					editinfo.setContentText("" + commande);
					editinfo.show();
					listcommandes.refresh();
		
				});
	}

	public void setCommandeDao(CommandeDao cmdd) {
		this.cmdd = cmdd;
		listcommandes.setItems(cmdd.getCommandes());
		statusCommande.setItems(cmdd.getStatus());
	}
}
