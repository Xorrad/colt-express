package main.vues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage IMG_BG;
    public static BufferedImage IMG_CADRE;
    public static BufferedImage IMG_BANDIT;

    public static void chargeAssets() {
        try {
            IMG_BG = ImageIO.read(Assets.class.getResource("/resources/images/background.png"));
            IMG_CADRE = ImageIO.read(Assets.class.getResource("/resources/images/cadre.png"));
            IMG_BANDIT = ImageIO.read(Assets.class.getResource("/resources/images/bandit.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
