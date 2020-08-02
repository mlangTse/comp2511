package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DungeonEndController {
    @FXML
    public Text gameStatus;
    @FXML
    public Button Menu;
    @FXML
    public Button Exit;
    @FXML
    public Button Again;
    @FXML
    public ImageView Pointer;

    Stage game;
    private String filename;
    private ArrayList<String> files = new ArrayList<>();;
    private int fileIndex;
    private boolean notSuccess;

    public DungeonEndController(Stage stage, String filename, boolean notSuccess) {
        this.game = stage;
        this.filename = filename;
        this.notSuccess = notSuccess;
    }

    public DungeonEndController(Stage stage, ArrayList<String> files, int fileIndex, boolean notSuccess) {
        this.game = stage;
        this.files = files;
        this.fileIndex = fileIndex;
        this.filename = files.get(fileIndex);
        this.notSuccess = notSuccess;
	}

	@FXML
    public void initialize() {
        if (notSuccess) {
            gameStatus.setText("   Game Over!");
            ((Text) gameStatus).setFill(Paint.valueOf("#000000"));
        }
        Image gnomeImage = new Image((new File("images/gnome.png")).toURI().toString());
        Pointer.setImage(gnomeImage);
    }

    @FXML
	public void handle_menu() throws Exception {
        DungeonApplication menu = new DungeonApplication();
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
        game.close();
        menu.show();
    }

    @FXML
	public void handle_exit() throws Exception {
        Stage exit = (Stage) Exit.getScene().getWindow();
        game.close();
        exit.close();
    }

    @FXML
	public void handle_again() throws Exception {
        DungeonGame new_game;
        if (!files.isEmpty()) {
            new_game = new DungeonGame(files, fileIndex);
        } else {
            new_game = new DungeonGame(filename);
        }
        Stage again = (Stage) Again.getScene().getWindow();
        again.close();
        game.close();
        new_game.show();
    }

    @FXML
    public void handlePointer(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                if (Pointer.getLayoutX() + 20 == Menu.getLayoutX()) return;
                if (Pointer.getLayoutX() + 20 == Exit.getLayoutX()) {
                    Pointer.setLayoutX(Menu.getLayoutX() - 20);
                } else if (Pointer.getLayoutX() + 20 == Again.getLayoutX()) {
                    Pointer.setLayoutX(Exit.getLayoutX() - 20);
                }
                break;
            case RIGHT:
                if (Pointer.getLayoutX() + 20 == Again.getLayoutX()) return;
                if (Pointer.getLayoutX() + 20 == Exit.getLayoutX()) {
                    Pointer.setLayoutX(Again.getLayoutX() - 20);
                } else if (Pointer.getLayoutX() + 20 == Menu.getLayoutX()) {
                    Pointer.setLayoutX(Exit.getLayoutX() - 20);
                }
                break;
            default:
                break;
        }
    }
}
