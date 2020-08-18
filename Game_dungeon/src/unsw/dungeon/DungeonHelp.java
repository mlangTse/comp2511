package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonHelp extends Application {
    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        DungeonHelpController dungeonLoader = new DungeonHelpController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonHelpView.fxml"));

        loader.setController(dungeonLoader);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void show() throws IOException {
        start(stage);
    }
}