package main.vues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage IMG_BG;
    public static BufferedImage IMG_CADRE;
    public static BufferedImage IMG_BANDIT;

    public static Font FONT_WESTERNBANG;
    public static Font FONT_RIOGRANDE;

    public static void chargeAssets() {
        try {
            IMG_BG = ImageIO.read(Assets.class.getResource("/resources/images/background.png"));
            IMG_CADRE = ImageIO.read(Assets.class.getResource("/resources/images/cadre.png"));
            IMG_BANDIT = ImageIO.read(Assets.class.getResource("/resources/images/bandit.png"));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            FONT_WESTERNBANG = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResource("/resources/fonts/WesternBangBang-Regular.ttf").openStream()).deriveFont(24f);
            ge.registerFont(FONT_WESTERNBANG);
            FONT_RIOGRANDE = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResource("/resources/fonts/RioGrande.ttf").openStream()).deriveFont(24f);
            ge.registerFont(FONT_RIOGRANDE);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
