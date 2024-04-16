package main.modeles;

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

    public enum Type {
        BANDIT,
        SHERIFF,
        TRESOR;
    }
}
