package main.vues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.HashMap;

public class Assets {

    public static HashMap<String, BufferedImage> BANDITS;

    public static BufferedImage IMG_BG;
    public static BufferedImage IMG_CADRE;
    public static BufferedImage IMG_BALLE;
    public static BufferedImage IMG_TITRE;

    public static BufferedImage IMG_BANDIT;
    public static BufferedImage IMG_SHERIFF;

    public static BufferedImage IMG_ACTIONS_BRAQUE;
    public static BufferedImage IMG_ACTIONS_ETAGES;
    public static BufferedImage IMG_ACTIONS_MOVE_ARRIERE;
    public static BufferedImage IMG_ACTIONS_MOVE_AVANT;
    public static BufferedImage IMG_ACTIONS_TIRE_ARRIERE;
    public static BufferedImage IMG_ACTIONS_TIRE_AVANT;
    public static BufferedImage IMG_ACTIONS_TIRE_BAS;
    public static BufferedImage IMG_ACTIONS_TIRE_HAUT;
    public static BufferedImage IMG_ACTIONS_INCONNUE;

    public static Font FONT_WESTERNBANG;
    public static Font FONT_RIOGRANDE;

    public static void chargeAssets() {
        try {
            IMG_BG = ImageIO.read(Assets.class.getResource("/resources/images/background.png"));
            IMG_CADRE = ImageIO.read(Assets.class.getResource("/resources/images/cadre.png"));
            IMG_BALLE = ImageIO.read(Assets.class.getResource("/resources/images/balle.png"));
            IMG_TITRE = ImageIO.read(Assets.class.getResource("/resources/images/titre.png"));

            IMG_BANDIT = ImageIO.read(Assets.class.getResource("/resources/images/bandit.png"));
            IMG_SHERIFF = ImageIO.read(Assets.class.getResource("/resources/images/sheriff.png"));

            IMG_ACTIONS_BRAQUE = ImageIO.read(Assets.class.getResource("/resources/images/action_braque.png"));
            IMG_ACTIONS_ETAGES = ImageIO.read(Assets.class.getResource("/resources/images/action_etages.png"));
            IMG_ACTIONS_MOVE_ARRIERE = ImageIO.read(Assets.class.getResource("/resources/images/action_move_arriere.png"));
            IMG_ACTIONS_MOVE_AVANT = ImageIO.read(Assets.class.getResource("/resources/images/action_move_avant.png"));
            IMG_ACTIONS_TIRE_ARRIERE = ImageIO.read(Assets.class.getResource("/resources/images/action_tire_arriere.png"));
            IMG_ACTIONS_TIRE_AVANT = ImageIO.read(Assets.class.getResource("/resources/images/action_tire_avant.png"));
            IMG_ACTIONS_TIRE_BAS = ImageIO.read(Assets.class.getResource("/resources/images/action_tire_bas.png"));
            IMG_ACTIONS_TIRE_HAUT = ImageIO.read(Assets.class.getResource("/resources/images/action_tire_haut.png"));
            IMG_ACTIONS_INCONNUE = ImageIO.read(Assets.class.getResource("/resources/images/action_inconnue.png"));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            FONT_WESTERNBANG = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResource("/resources/fonts/WesternBangBang-Regular.ttf").openStream()).deriveFont(24f);
            ge.registerFont(FONT_WESTERNBANG);
            FONT_RIOGRANDE = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResource("/resources/fonts/RioGrande.ttf").openStream()).deriveFont(24f);
            ge.registerFont(FONT_RIOGRANDE);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        Assets.chargeBandits();
    }

    public static void chargeBandits() {
        BANDITS = new HashMap<>();

        try {
            File dir = new File(Assets.class.getResource("/resources/images/bandits/").getFile());
            for (File file : dir.listFiles()) {
                if (file.isDirectory() || !file.getName().endsWith(".png"))
                    continue;
                String banditName = file.getName();
                // Capitalise la premier lettre et retire le ".png"
                banditName = Character.toUpperCase(banditName.charAt(0)) + banditName.substring(1, banditName.length() - 4);
                BANDITS.put(banditName, ImageIO.read(file));
                System.out.println(banditName + " " + BANDITS.get(banditName));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
