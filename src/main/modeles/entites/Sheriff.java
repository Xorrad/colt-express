package main.modeles.entites;

import main.modeles.Toigon;

public class Sheriff extends Entite {
    public String nom;

    public Sheriff(int num, Toigon toigon, String nom) {
        super(num, toigon, Type.SHERIFF);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
