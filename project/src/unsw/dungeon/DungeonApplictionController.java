package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DungeonApplictionController {
    @FXML
    public Button Maze;
    @FXML
    public Button Boulder;
    @FXML
    public Button Advanced;
    @FXML
    public ImageView Pointer;
    @FXML
    public ImageView Menu;

    @FXML
    public void initialize() {
        Image keyImage = new Image((new File("images/key.png")).toURI().toString());
        Image menuImage = new Image((new File("images/main.png")).toURI().toString());
        Pointer.setImage(keyImage);
        Menu.setImage(menuImage);
    }

    @FXML
	public void handle_maze() throws Exception {
        Stage stage = (Stage) Maze.getScene().getWindow();
        if (Maze.getText().equals("Play")) {
            Maze.setText("Easy");
            Boulder.setText("Medium");
            Advanced.setText("Hard");
            return;
        }
        DungeonGame game = new DungeonGame("boulders.json");
        stage.close();
        game.show();
    }

    @FXML
	public void handle_boulder() throws Exception {
        Stage stage = (Stage) Boulder.getScene().getWindow();
        if (Boulder.getText().equals("Help")) {
            DungeonHelp help = new DungeonHelp();
            help.show();
            stage.close();
            return;
        }
        DungeonGame game = new DungeonGame("mediumDungeon.json");
        stage.close();
        game.show();
    }

    @FXML
	public void handle_advanced() throws Exception {
        Stage stage = (Stage) Advanced.getScene().getWindow();
        if (Advanced.getText().equals("Exit")) {
            stage.close();
            return;
        }
        ArrayList<String> mutli_game = new ArrayList<String>();
        mutli_game.add("maze.json");
        mutli_game.add("boulders.json");
        mutli_game.add("advanced.json");
        DungeonGame game = new DungeonGame(mutli_game, 0);
        stage.close();
        game.show();
    }

    @FXML
    public void handlePointer(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (Pointer.getLayoutY() - 2 == Maze.getLayoutY()) return;
                if (Pointer.getLayoutY() - 2 == Boulder.getLayoutY()) {
                    Pointer.setLayoutY(Maze.getLayoutY() + 2);
                } else if (Pointer.getLayoutY() - 2 == Advanced.getLayoutY()) {
                    Pointer.setLayoutY(Boulder.getLayoutY() + 2);
                }
                break;
            case DOWN:
                if (Pointer.getLayoutY() - 2 == Advanced.getLayoutY()) return;
                if (Pointer.getLayoutY() - 2 == Boulder.getLayoutY()) {
                    Pointer.setLayoutY(Advanced.getLayoutY() + 2);
                } else if (Pointer.getLayoutY() - 2 == Maze.getLayoutY()) {
                    Pointer.setLayoutY(Boulder.getLayoutY() + 2);
                }
                break;
            default:
                break;
        }
    }
}