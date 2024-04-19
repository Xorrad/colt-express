package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Toigon;
import main.modeles.actions.Action;

import java.awt.*;
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
        this.nombreBalles = 5;
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

    public void deposeRandomTresor() {
        int n = new Random().nextInt(this.tresors.size());
        Tresor tresor = this.tresors.get(n);
        this.retireTresor(tresor);
        tresor.getToigon().ajouteEntite(tresor);
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
        super.deplace(dir);

        // On change le toigon des trésors que le bandit porte.
        // Mais les trésors ne doivent pas être listés dans la liste
        // des entités du toigon (pour ne pas les ramasser).
        this.tresors.forEach(tresor -> {
            tresor.setToigon(this.getToigon());
        });
    }
}
