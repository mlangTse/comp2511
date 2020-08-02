package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class DungeonHelpController {
    @FXML
    public Button Menu;
    @FXML
    public ImageView exit;
    @FXML
    public ImageView treasure;
    @FXML
    public ImageView floorswitch;
    @FXML
    public ImageView boulder;
    @FXML
    public ImageView enemy;
    @FXML
    public ImageView portal;
    @FXML
    public ImageView sword;
    @FXML
    public ImageView potion;
    @FXML
    public ImageView door;
    @FXML
    public ImageView key;

    @FXML
	public void handle_menu() throws Exception {
        DungeonApplication menu = new DungeonApplication();
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
        menu.show();
    }

    @FXML
    public void initialize() {
        Image keyImage = new Image((new File("images/key.png")).toURI().toString());
        Image doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        Image potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        Image swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        Image portalImage = new Image((new File("images/portal.png")).toURI().toString());
        Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        Image boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        Image floorswitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        Image treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        Image exitImage = new Image((new File("images/exit.png")).toURI().toString());
        key.setImage(keyImage);
        door.setImage(doorImage);
        potion.setImage(potionImage);
        sword.setImage(swordImage);
        portal.setImage(portalImage);
        enemy.setImage(enemyImage);
        boulder.setImage(boulderImage);
        floorswitch.setImage(floorswitchImage);
        treasure.setImage(treasureImage);
        exit.setImage(exitImage);
    }
}