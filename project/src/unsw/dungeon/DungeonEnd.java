package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DungeonEnd extends Application {
    Stage game;
    private String filename;
    private boolean notSuccess;

    public DungeonEnd(Stage stage, String filename, boolean notSuccess) {
        this.game = stage;
        this.filename = filename;
        this.notSuccess = notSuccess;
    }

    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        DungeonEndController controller = new DungeonEndController(game, filename, notSuccess);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonEndView.fxml"));

        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
        });
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void show() throws IOException {
        start(stage);
    }
}