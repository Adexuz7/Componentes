package dad.javafx.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		ListSelector<String> listSelector = new ListSelector<String>();
		DateChooser dateChooser = new DateChooser();
		
		
		
		listSelector.setLeftTitle("Jugadores");
		listSelector.getLeftItems().addAll(
				"Perico",
				"Palotes",
				"Menganita",
				"Fulanito"
				);
		
		listSelector.setRightTitle("Participantes");
		
		BorderPane root = new BorderPane();
		root.setCenter(dateChooser);
		
		Scene scene = new Scene(root, 640, 480);
		primaryStage.setTitle("Custom components test app");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
