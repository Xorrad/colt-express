package main.modeles.entites;

import main.modeles.Toigon;

import java.util.Random;

public class Tresor extends Entite {
    private String nom;
    private int valeur;

    public Tresor(int num, Toigon toigon, String nom, int valeur) {
        super(num, toigon, Entite.Type.TRESOR);
        this.nom = nom;
        this.valeur = valeur;
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

    public static Tresor genereTresor(int num, Toigon toigon) {
        Random ran = new Random();
        String nom = "";
        int valeur = 0;

        switch(ran.nextInt(2)) {
            case 0:
                nom = "Bourse";
                valeur = ran.nextInt( 500);
                break;
            case 1:
                nom = "Bijou";
                valeur = 500;
                break;
        }
        return new Tresor(num, toigon, nom, valeur);
    }
}
