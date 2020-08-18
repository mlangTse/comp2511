package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonGame extends Application {
    private String filename = "maze.json";
    private ArrayList<String> files = new ArrayList<String>();
    private int fileIndex;
    Stage stage = new Stage();

    public DungeonGame(String filename) {
        this.filename = filename;
    }

    public DungeonGame(ArrayList<String> files, int fileIndex) {
        this.files = files;
        this.fileIndex = fileIndex;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader;
        if (!files.isEmpty()) {
            dungeonLoader = new DungeonControllerLoader(files, fileIndex);
        } else {
            dungeonLoader = new DungeonControllerLoader(filename);
        }

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
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
