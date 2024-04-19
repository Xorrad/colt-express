package main.vues;

import javax.imageio.ImageIO;
import java.awt.*;

public class Assets {

    public static Image IMG_BG;
    public static Image IMG_RAILS;
    public static Image IMG_CADRE;

    public static void chargeAssets() {
        try {
            IMG_BG = ImageIO.read(Assets.class.getResource("/resources/images/background.png"));
            IMG_RAILS = ImageIO.read(Assets.class.getResource("/resources/images/rails.png"));
            IMG_CADRE = ImageIO.read(Assets.class.getResource("/resources/images/cadre.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
