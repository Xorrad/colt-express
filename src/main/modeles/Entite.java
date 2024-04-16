package main.modeles;

// Classe abstraite pour les Bandits, Sheriffs et Tresors.
public abstract class Entite {
    private Toigon toigon;
    private Type type;

    public Entite(Toigon toigon, Type type) {
        this.toigon = toigon;
        this.type = type;
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
