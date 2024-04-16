package main.modeles;

import java.util.ArrayList;

public class Bandit extends Entite {
    private String nom;
    private int nombreBalles;
    private ArrayList<Tresor> tresors;

    public Bandit(Toigon toigon, String nom) {
        super(toigon, Entite.Type.BANDIT);
        this.nom = nom;
        this.nombreBalles = 10;
        this.tresors = new ArrayList<>();
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
        return 0;
    }
}
