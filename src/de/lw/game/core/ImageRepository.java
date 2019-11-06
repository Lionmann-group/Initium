package de.lw.game.core;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageRepository {

    private static ImageRepository instance;
    private  final Map<String, Image> map = new HashMap<>(32);

    public static ImageRepository getInstance() {
        if (instance == null) {
            instance = new ImageRepository();
        }

        return instance;
    }

    public Image loadImage(String path) {
        Image image = map.get(path);

        if (image == null) {
            image = imageReader(path);
            map.put(path, image);
        }

        return image;
    }

    private Image imageReader(String texture) {
        Image img = null;

        try {
            img = ImageIO.read(Class.forName(ImageRepository.class.getName()).getResource(texture));
            // System.out.println(img + "Love");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return img;
    }

}
