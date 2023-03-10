package fr.greta92.DomesFX;

import java.net.URL;

import dao.ArticleDao;
import dao.CommandeDao;
import dao.ConnectBd;
import dao.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	ArticleDao artd;
	UserDao userd;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			AnchorPane root = (AnchorPane)FXMLLoader.<AnchorPane>load(getClass().getResource("Main.fxml"));

			// créer un objet URL - chemin vers le fichier XML
			URL resource = getClass().getResource("main.fxml");
			// objet permet de creer objet java à partir de FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(resource);
			TabPane root = (TabPane) loader.load();
			
			MainController controller = (MainController) loader.getController();
			
			controller.setArticleDao(artd);
			controller.setUserDao(userd);
			
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Panneau D'administration");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws Exception {
		super.init();
		ConnectBd bd = new ConnectBd();
		bd.connect();
		artd = new ArticleDao(bd.getCon());
		artd.setArticles(artd.trouveTout());
		userd = new UserDao(bd.getCon());
		userd.setUsers(userd.trouveTout());


		
	}

	@Override
	public void stop() throws Exception {
		super.stop();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
