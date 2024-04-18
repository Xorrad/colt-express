package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Toigon;
import main.modeles.actions.Action;

import java.util.ArrayList;
import java.util.Random;
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

    public void ajouteTresor(Tresor tresor) {
        this.tresors.add(tresor);
    }

    public void retireTresor(Tresor tresor) {
        this.tresors.remove(tresor);
    }

    public void deposeRandomTresor() {
        int n = new Random().nextInt(this.tresors.size());
        Tresor tresor = this.tresors.get(n);
        this.retireTresor(tresor);
        tresor.getToigon().ajouteEntite(tresor);
    }

    @Override
    public void deplace(Direction dir) {
        super.deplace(dir);

        // On change le toigon des trésors que le bandit porte.
        // Mais les trésors ne doivent pas être listés dans la liste
        // des entités du toigon (pour ne pas les ramasser).
        this.tresors.forEach(tresor -> {
            tresor.setToigon(this.getToigon());
        });
    }
}
