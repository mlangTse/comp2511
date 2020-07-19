package unsw.dungeon;

import java.io.File;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private ImageView imageView;

    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public ImageView getImage() {
        return imageView;
    }

    public void setImage(Image image, Boolean toBack) {
        if (imageView == null) {
            imageView = new ImageView(image);
        }
        imageView.setImage(image);
        if (toBack) {
            imageView.toBack();
        }
    }

    public void destory() {
        if (imageView == null) return;
        setImage(new Image((new File("images/dirt_0_new.png")).toURI().toString()), true);
    }
}
