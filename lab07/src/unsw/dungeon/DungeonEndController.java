package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DungeonEndController {
    @FXML
    public Button Mune;
    @FXML
    public Button Exit;
    @FXML
    public Button Again;

    Stage game;
    private String filename;

    public DungeonEndController(Stage stage, String filename) {
        this.game = stage;
        this.filename = filename;
    }

	public void handle_mune() throws Exception {
        DungeonMain mune = new DungeonMain();
        Stage stage = (Stage) Mune.getScene().getWindow();
        stage.close();
        this.game.close();
        mune.show();
    }

	public void handle_exit() throws Exception {
        Stage exit = (Stage) Mune.getScene().getWindow();
        game.close();
        exit.close();
    }

	public void handle_again() throws Exception {
        DungeonApplication game = new DungeonApplication(filename);
        Stage again = (Stage) Again.getScene().getWindow();
        again.close();
        this.game.close();
        game.show();
    }
}
