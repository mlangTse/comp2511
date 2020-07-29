package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DungeonHelpController {
    @FXML
    public Button Menu;

    @FXML
	public void handle_menu() throws Exception {
        DungeonApplication menu = new DungeonApplication();
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
        menu.show();
    }

}