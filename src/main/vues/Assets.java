package main.vues;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.HashMap;

public class Assets {

    public static HashMap<String, BufferedImage> BANDITS;

    public static BufferedImage IMG_BG;
    public static BufferedImage IMG_CADRE;
    public static BufferedImage IMG_CADRE_GRAND;
    public static BufferedImage IMG_BALLE;
    public static BufferedImage IMG_TITRE;
    public static BufferedImage IMG_WAGON;
    public static BufferedImage IMG_LOCOMOTIVE;

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

    // Les musiques sont fusionnées dans un même flux qui est joué à l'infini.
    public static AudioInputStream AUDIO_MUSIQUES;
    public static AudioInputStream AUDIO_TRAIN;

    // Initialise la table des bandits statiquement car les assets ne sont pas
    // chargés lors de l'execution des tests.
    static {
        BANDITS = new HashMap<>();
        AUDIO_MUSIQUES = null;
    }

    public static void chargeAssets() {
        Assets.chargeBandits();
        Assets.chargeMusiques();

        try {
            IMG_BG = loadImage("background.png");
            IMG_CADRE = loadImage("cadre.png");
            IMG_CADRE_GRAND = loadImage("cadre_grand.png");
            IMG_BALLE = loadImage("balle.png");
            IMG_TITRE = loadImage("titre.png");

            IMG_WAGON = loadImage("wagon.png");
            IMG_LOCOMOTIVE = loadImage("locomotive.png");

            IMG_BANDIT = loadImage("bandit.png");
            IMG_SHERIFF = loadImage("sheriff.png");

            IMG_ACTIONS_BRAQUE = loadImage("action_braque.png");
            IMG_ACTIONS_ETAGES = loadImage("action_etages.png");
            IMG_ACTIONS_MOVE_ARRIERE = loadImage("action_move_arriere.png");
            IMG_ACTIONS_MOVE_AVANT = loadImage("action_move_avant.png");
            IMG_ACTIONS_TIRE_ARRIERE = loadImage("action_tire_arriere.png");
            IMG_ACTIONS_TIRE_AVANT = loadImage("action_tire_avant.png");
            IMG_ACTIONS_TIRE_BAS = loadImage("action_tire_bas.png");
            IMG_ACTIONS_TIRE_HAUT = loadImage("action_tire_haut.png");
            IMG_ACTIONS_INCONNUE = loadImage("action_inconnue.png");

            FONT_WESTERNBANG = loadFont("WesternBangBang-Regular.ttf");
            FONT_RIOGRANDE = loadFont("RioGrande.ttf");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void chargeBandits() {
        try {
            File dir = new File(Assets.class.getResource("/resources/images/bandits/").getFile());
            for (File file : dir.listFiles()) {
                if (file.isDirectory() || !file.getName().endsWith(".png"))
                    continue;
                String banditName = file.getName();
                // Capitalise la premier lettre et retire le ".png"
                banditName = Character.toUpperCase(banditName.charAt(0)) + banditName.substring(1, banditName.length() - 4);
                BANDITS.put(banditName, ImageIO.read(file));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void chargeMusiques() {
        ajouteMusique("cactus-train.wav");
        ajouteMusique("desert-monolith.wav");
        ajouteMusique("outlaw-ride.wav");
        ajouteMusique("desert-monolith.wav");

        AUDIO_TRAIN = loadSound("train.wav");
    }

    public static BufferedImage loadImage(String nom) throws IOException {
        return ImageIO.read(Assets.class.getResource("/resources/images/" + nom));
    }

    public static Font loadFont(String nom) throws IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResource("/resources/fonts/" + nom).openStream()).deriveFont(24f);
        ge.registerFont(font);
        return font;
    }

    public static AudioInputStream loadSound(String nom) {
        try {
            return AudioSystem.getAudioInputStream(Assets.class.getResource("/resources/musics/" + nom));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ajouteMusique(String nom) {
        try {
            // On charge le fichier audio dans un flux
            // puis on ajoute ce flux à la bande de musique existante.
            AudioInputStream flux = loadSound(nom);

            if(AUDIO_MUSIQUES == null) {
                AUDIO_MUSIQUES = flux;
            }
            else {
                AUDIO_MUSIQUES = new AudioInputStream(
                        new SequenceInputStream(AUDIO_MUSIQUES, flux),
                        AUDIO_MUSIQUES.getFormat(),
                        AUDIO_MUSIQUES.getFrameLength() + AUDIO_MUSIQUES.getFrameLength()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
