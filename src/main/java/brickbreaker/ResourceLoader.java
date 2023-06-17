package brickbreaker;

import java.util.Arrays;

import brickbreaker.common.GameImages;
import brickbreaker.common.GameObjectsImages;
import javafx.scene.image.Image;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public final class ResourceLoader {

    private static ResourceLoader instance;

    /**
     * @return the instance of ResourceLoader if it not exists yet.
     */
    public static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }

        return instance;
    }
    
    /**
     * Method to load the json file.
     */
    public void start() {

        Arrays.stream(GameImages.values()).forEach(value -> {
            value.setImage(new Image(ClassLoader.getSystemResourceAsStream(value.getFilePath())));
        });

        Arrays.stream(GameObjectsImages.values()).forEach(value -> {
            value.setImage(new Image(ClassLoader.getSystemResourceAsStream(value.getFilePath())));
        });
    }

}
