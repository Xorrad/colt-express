package main.modeles;

public class Tresor extends Entite {
    private String nom;
    private int valeur;

    public Tresor(Toigon toigon, String nom, int valeur) {
        super(toigon, Entite.Type.TRESOR);
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
}
