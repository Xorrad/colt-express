package main.modeles.entites;

import main.modeles.Toigon;
import main.modeles.actions.Action;

import java.util.ArrayList;
import java.util.Stack;

public class Bandit extends Entite {
    private String nom;
    private int nombreBalles;
    private ArrayList<Tresor> tresors;
    private Stack<Action> actions;

    public Bandit(int num, Toigon toigon, String nom) {
        super(num, toigon, Entite.Type.BANDIT);
        this.nom = nom;
        this.nombreBalles = 10;
        this.tresors = new ArrayList<>();
        this.actions = new Stack<Action>();
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
