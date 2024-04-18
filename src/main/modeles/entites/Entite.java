package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Toigon;

// Classe abstraite pour les Bandits, Sheriffs et Tresors.
public abstract class Entite {
    private int num;
    private Toigon toigon;
    private Type type;

    public Entite(int num, Toigon toigon, Type type) {
        this.num = num;
        this.toigon = toigon;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public Toigon getToigon() {
        return toigon;
    }

    public void setToigon(Toigon toigon) {
        this.toigon = toigon;
    }

    public Type getType() {
        return type;
    }

    public boolean peutDeplacer(Direction dir) {
        return this.toigon.getVoisin(dir) != null;
    }

    public void deplace(Direction dir) {
        if(!this.peutDeplacer(dir))
            throw new RuntimeException("Aucun toigon displonible dans la direction " + dir.name());
        this.toigon.retireEntite(this);
        this.toigon = this.toigon.getVoisin(dir);
        this.toigon.ajouteEntite(this);
    }

    public enum Type {
        BANDIT,
        SHERIFF,
        TRESOR;
    }
}
