package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DungeonEndController {
    @FXML
    public Text gameStatus;
    @FXML
    public Button Mune;
    @FXML
    public Button Exit;
    @FXML
    public Button Again;

    Stage game;
    private String filename;
    private boolean notSuccess;

    public DungeonEndController(Stage stage, String filename, boolean notSuccess) {
        this.game = stage;
        this.filename = filename;
        this.notSuccess = notSuccess;
    }

    @FXML
    public void initialize() {
        if (notSuccess) {
            gameStatus.setText("Game Over!");
        }
    }

	public void handle_mune() throws Exception {
        DungeonMain mune = new DungeonMain();
        Stage stage = (Stage) Mune.getScene().getWindow();
        stage.close();
        game.close();
        mune.show();
    }

	public void handle_exit() throws Exception {
        Stage exit = (Stage) Mune.getScene().getWindow();
        game.close();
        exit.close();
    }

	public void handle_again() throws Exception {
        DungeonApplication new_game = new DungeonApplication();
        new_game.setFilename(filename);
        Stage again = (Stage) Again.getScene().getWindow();
        again.close();
        game.close();
        new_game.show();
    }
}
