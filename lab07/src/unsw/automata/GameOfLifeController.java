package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button Tick;
    @FXML
    private Button Play;

    private GameOfLife game;

    private final Timeline timeline;

    public GameOfLifeController() {
        game = new GameOfLife();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t) {
                game.tick();
            }
        };
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), onFinished));
    }

    @FXML
    public void initialize() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                CheckBox checkBox = new CheckBox();
                GridPane.setColumnIndex(checkBox, x);
                GridPane.setRowIndex(checkBox, y);
                game.cellProperty(x, y).bindBidirectional(checkBox.selectedProperty());
                this.gridPane.getChildren().add(checkBox);
            }
        }
    }

    @FXML
    public void pressTick() {
        game.tick();
    }

    @FXML
    public void pressPlay() {
        if (Play.getText().equals("Stop")) {
            timeline.stop();
            Play.setText("Play");
            return;
        }
        timeline.play();
        Play.setText("Stop");
    }
}

