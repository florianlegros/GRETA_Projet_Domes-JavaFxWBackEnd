package fr.greta92.DomesFX;

import java.io.IOException;
import java.net.URL;

import dao.ArticleDao;
import dao.ConnectBd;
import dao.EmployeeDao;
import dao.UserDao;
import entity.Employee;
import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	TextField email, password;

	@FXML
	Button btn_login;

	EmployeeDao empd;

	@FXML
	public void initialize() {
		btn_login.setOnAction(e -> {
			boolean check = false;
			Employee emp = new Employee(email.getText(), password.getText());

			try {
				System.out.println(emp.toString());
				if (empd.chercheLogin(emp.getEmail(), emp.getPassword())) {

					// créer un objet URL - chemin vers le fichier XML
					URL resource = getClass().getResource("main.fxml");
					// objet permet de creer objet java à partir de FXML
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(resource);
					TabPane root = (TabPane) loader.load();

					MainController controller = (MainController) loader.getController();

					ConnectBd bd = new ConnectBd();
					bd.connect();

					ArticleDao artd = new ArticleDao(bd.getCon());
					UserDao userd = new UserDao(bd.getCon());

					artd.setArticles(artd.trouveTout());
					userd.setUsers(userd.trouveTout());

					controller.setArticleDao(artd);
					controller.setUserDao(userd);
					
					
					
					((Stage) btn_login.getScene().getWindow()).setWidth(900);
					((Stage) btn_login.getScene().getWindow()).setHeight(600);
					
					btn_login.getScene().setRoot(root);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	public void setEmployeeDao(EmployeeDao empd) {
		this.empd = empd;

	}

}
