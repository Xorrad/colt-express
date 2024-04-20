package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Toigon;
import main.modeles.actions.Action;
import main.vues.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Bandit extends Entite {
    private String nom;
    private int nombreBalles;
    private ArrayList<Tresor> tresors;
    private Stack<Action> actions;
    private BufferedImage image;
    private Image icon;

    public Bandit(int num, Toigon toigon, String nom) {
        super(num, toigon, Entite.Type.BANDIT);
        this.nom = nom;
        this.nombreBalles = 5;
        this.tresors = new ArrayList<>();
        this.actions = new Stack<Action>();
        this.image = Assets.IMG_BANDIT;
        this.initImage();
    }

    public Bandit(int num, Toigon toigon, String nom, BufferedImage image) {
        super(num, toigon, Entite.Type.BANDIT);
        this.nom = nom;
        this.nombreBalles = 5;
        this.tresors = new ArrayList<>();
        this.actions = new Stack<Action>();
        this.image = image;
        this.initImage();
    }

    private void initImage() {
        if(this.image == null)
            return;

        // Fait une copie profonde de l'image de bandit.
        BufferedImage coloredImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(), this.image.getType());
        Graphics2D g2d = coloredImage.createGraphics();
        g2d.drawImage(this.image, 0, 0, null);
        g2d.dispose();
        this.image = coloredImage;

        Color c = getColor();
        int darkness = 80;

        // Change la couleur du blanc.
        for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {
                int rgb = this.image.getRGB(x, y);
                Color color = new Color(rgb, true);
                if(color.getRed() < 200 || color.getGreen() < 200 || color.getBlue() < 200)
                    continue;
                Color newColor = new Color(
                        Math.max((c.getRed()+color.getRed())/2 - darkness, 0),
                        Math.max((c.getGreen()+color.getGreen())/2 - darkness, 0),
                        Math.max((c.getBlue()+color.getBlue())/2 - darkness, 0),
                        color.getAlpha()
                );
                this.image.setRGB(x, y, newColor.getRGB());
            }
        }

        this.icon = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Image getIcon() {
        return icon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreBalles() {
        return nombreBalles;
    }

    public void setNombreBalles(int nombreBalles) {
        this.nombreBalles = nombreBalles;
    }

    public int getSommeTresor() {
        return this.tresors.stream()
                .mapToInt(tresor -> tresor.getValeur())
                .sum();
    }

    public void ajouteTresor(Tresor tresor) {
        this.tresors.add(tresor);
    }

    public void retireTresor(Tresor tresor) {
        this.tresors.remove(tresor);
    }

    public boolean possedeTresor(){//System.out.println(this.tresors.size());
        return !this.tresors.isEmpty();}

    public Tresor deposeRandomTresor() {
        if (!this.possedeTresor()){return null;}
        int n = new Random().nextInt(this.tresors.size());
        Tresor tresor = this.tresors.get(n);
        this.retireTresor(tresor);
        tresor.getToigon().ajouteEntite(tresor);
        return tresor;
    }

    public boolean contientAction(Action action) {
        return this.actions.contains(action);
    }

    public void ajouteAction(Action action) {
        this.actions.add(action);
    }

    public void retireAction(Action action) {
        this.actions.remove(action);
    }

    public Stack<Action> getActions() {
        return this.actions;
    }

    public int getNombreActions() {
        return this.actions.size();
    }

    public void joueAction() {
        this.actions.get(0).apply();
        this.actions.remove(0);
    }

    public Color getColor() {
        return Color.getHSBColor(((float) this.getNum() / (float) Modele.NB_BANDITS), 1, 1);
    }

    @Override
    public void deplace(Direction dir) {
//
        super.deplace(dir);

        // On change le toigon des trésors que le bandit porte.
        // Mais les trésors ne doivent pas être listés dans la liste
        // des entités du toigon (pour ne pas les ramasser).
        this.tresors.forEach(tresor -> {
            tresor.setToigon(this.getToigon());
        });
    }
}
