package main.modeles.entites;

import main.modeles.Toigon;
import main.Assets;

import java.awt.*;
import java.util.Random;

public class Tresor extends Entite {
    private String nom;
    private int valeur;
    private Image icon;

    public Tresor(int num, Toigon toigon, String nom, int valeur, Image icon) {
        super(num, toigon, Entite.Type.TRESOR);
        this.nom = nom;
        this.valeur = valeur;
        this.icon = icon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Image getIcon() {
        return icon;
    }

    public static Tresor genereTresor(int num, Toigon toigon) {
        Random ran = new Random();
        String nom = "";
        int valeur = 0;
        Image icon = Assets.IMG_TRESORS_BOURSE;

        switch(ran.nextInt(2)) {
            case 0:
                nom = "Bourse";
                valeur = ran.nextInt( 500);
                icon = Assets.IMG_TRESORS_BOURSE;
                break;
            case 1:
                nom = "Bijoux";
                valeur = 500;
                icon = Assets.IMG_TRESORS_BIJOUX;
                break;
        }
        return new Tresor(num, toigon, nom, valeur, icon);
    }
}
