package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 *
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities = new ArrayList<>();
    // Images
    private Image playerImage = new Image((new File("images/human_new.png")).toURI().toString());
    private Image wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
    private Image exitImage = new Image((new File("images/exit.png")).toURI().toString());
    private Image treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
    private Image doorCloseImage = new Image((new File("images/closed_door.png")).toURI().toString());
    private Image keyImage = new Image((new File("images/key.png")).toURI().toString());
    private Image boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
    private Image switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
    private Image portalImage = new Image((new File("images/portal.png")).toURI().toString());
    private Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
    private Image swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
    private Image invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);

    }

    public DungeonControllerLoader(JSONObject json) {
        super(json);
        entities = new ArrayList<>();
    }

    public DungeonControllerLoader(ArrayList<String> files, int fileIndex) throws FileNotFoundException {
        super(files, fileIndex);
	}

	@Override
    public void onLoad(Player player) {
        player.setImage(playerImage, false);
        ImageView view = player.getImage();
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        wall.setImage(wallImage, false);
        ImageView view = wall.getImage();
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        exit.setImage(exitImage, false);
        ImageView view = exit.getImage();
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        treasure.setImage(treasureImage, false);
        ImageView view = treasure.getImage();
        addEntity(treasure, view);
    }

    @Override
    public void onLoad(Door door) {
        door.setImage(doorCloseImage, true);
        ImageView view = door.getImage();
        addEntity(door, view);
    }

    @Override
    public void onLoad(Key key) {
        key.setImage(keyImage, false);
        ImageView view = key.getImage();
        addEntity(key, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        boulder.setImage(boulderImage, false);
        ImageView view = boulder.getImage();
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Floorswitch floorswitch) {
        floorswitch.setImage(switchImage, true);
        ImageView view = floorswitch.getImage();
        addEntity(floorswitch, view);
    }

    @Override
    public void onLoad(Portal portal) {
        portal.setImage(portalImage, true);
        ImageView view = portal.getImage();
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        enemy.setImage(enemyImage, false);
        ImageView view = enemy.getImage();
        addEntity(enemy, view);
    }

    @Override
    public void onLoad(Sword sword) {
        sword.setImage(swordImage, false);
        ImageView view = sword.getImage();
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Potion potion) {
        potion.setImage(invincibilityImage, false);
        ImageView view = potion.getImage();
        addEntity(potion, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        if (super.getFiles().isEmpty())
            return new DungeonController(load(), entities, super.getFilename());
        return new DungeonController(load(), entities, super.getFiles(), super.getIndex());
    }
}
