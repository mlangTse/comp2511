package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DungeonApplictionController {
    @FXML
    public Button Maze;
    @FXML
    public Button Boulder;
    @FXML
    public Button Advanced;

	public void handle_maze() throws Exception {
        Stage stage = (Stage) Maze.getScene().getWindow();
        if (Maze.getText().equals("Play")) {
            Maze.setText("Easy");
            Boulder.setText("Medium");
            Advanced.setText("Hard");
            return;
        }
        DungeonGame game = new DungeonGame();
        game.setFilename("maze.json");
        stage.close();
        game.show();
    }

	public void handle_boulder() throws Exception {
        Stage stage = (Stage) Boulder.getScene().getWindow();
        if (Boulder.getText().equals("Help")) {
            return;
        }
        DungeonGame game = new DungeonGame();
        game.setFilename("boulders.json");
        stage.close();
        game.show();
    }

	public void handle_advanced() throws Exception {
        Stage stage = (Stage) Advanced.getScene().getWindow();
        if (Advanced.getText().equals("Exit")) {
            stage.close();
            return;
        }
        DungeonGame game = new DungeonGame();
        game.setFilename("advanced.json");
        stage.close();
        game.show();
    }
}