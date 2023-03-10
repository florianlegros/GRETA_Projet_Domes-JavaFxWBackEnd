module JavaFxWBackEnd {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	
	opens fr.greta92.DomesFX to javafx.graphics, javafx.fxml;
}
