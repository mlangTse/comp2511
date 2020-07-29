package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DungeonMainController {
    @FXML
    public Button Maze;
    @FXML
    public Button Boulder;
    @FXML
    public Button Advanced;

	public void handle_maze() throws Exception {
        DungeonApplication game = new DungeonApplication("maze.json");
        Stage stage = (Stage) Maze.getScene().getWindow();
        stage.close();
        game.show();
    }

	public void handle_boulder() throws Exception {
        DungeonApplication game = new DungeonApplication("boulders.json");
        Stage stage = (Stage) Boulder.getScene().getWindow();
        stage.close();
        game.show();
    }

	public void handle_advanced() throws Exception {
        DungeonApplication game = new DungeonApplication("advanced.json");
        Stage stage = (Stage) Advanced.getScene().getWindow();
        stage.close();
        game.show();
    }
}