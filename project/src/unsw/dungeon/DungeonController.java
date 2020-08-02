package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private String filename;

    private ArrayList<String> files = new ArrayList<String>();
    private int fileIndex;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, String filename) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.filename = filename;
    }

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, ArrayList<String> files, int fileIndex) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.files = files;
        this.filename = files.get(fileIndex);
        this.fileIndex = fileIndex;
    }

    public void setPlayerInfo(int x, IntegerProperty siP) {
        Text text = new Text();
        SimpleIntegerProperty showing = new SimpleIntegerProperty(0);
        siP.bindBidirectional(showing);
        siP.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                text.setText(": " + newValue.intValue());
            }
        });
        text.setText(": " + siP.intValue());
        text.setFont(new Font(14));
        squares.add(text, x + 1, 1);
    }

    @FXML
    public void initialize() {
        DungeonController thisOne = this;
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Button menu = new Button("Menu");
        Button restart = new Button("Restart");

        menu.setOnAction(e->{
            DungeonApplication Menu = new DungeonApplication();
            Stage stage = (Stage) squares.getScene().getWindow();
            stage.close();
            try {
                Menu.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        restart.setOnAction(e->{
            DungeonGame game;
            if (!files.isEmpty()) {
                game = new DungeonGame(files, fileIndex);
            } else {
                game = new DungeonGame(filename);
            }
            Stage stage = (Stage) squares.getScene().getWindow();
            stage.close();
            try {
                game.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        squares.add(menu, 0, 0, 2, 1);
        squares.add(restart, 2, 0, 2, 1);

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 2; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (Entity e : dungeon.getEntities()) {
            if (e instanceof Sword && e.getY() == 1) {
                setPlayerInfo(e.getX(), player.SwordTime());
            }
            if (e instanceof Key && e.getY() == 1) {
                setPlayerInfo(e.getX(), player.numberOfKey());
            }
            if (e instanceof Treasure && e.getY() == 1) {
                setPlayerInfo(e.getX(), player.numberOfTreasure());
            } else if (e instanceof Potion && e.getY() == 1) {
                setPlayerInfo(e.getX(), player.numberOfPotion());
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        // set player to front
        player.getImage().toFront();

        player.IsDestroyed().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    try {
                        thisOne.gameEnd();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
        if (dungeon.check_progress()) {
            if (!files.isEmpty() && fileIndex < files.size() - 1) {
                nextLevel();
                return;
            }
            gameEnd();
        }
    }

    public void gameEnd() throws IOException {
        Stage stage = (Stage) squares.getScene().getWindow();
        boolean notSuccess = player.IsDestroyed().getValue();

        DungeonEnd end;
        if (!files.isEmpty()) {
            end = new DungeonEnd(stage, files, fileIndex, notSuccess);
        } else {
            end = new DungeonEnd(stage, filename, notSuccess);
        }
        end.show();
    }

    public void nextLevel() throws IOException {
        Stage stage = (Stage) squares.getScene().getWindow();
        DungeonGame next = new DungeonGame(files, fileIndex+1);
        stage.close();
        next.show();
    }
}

