package main.modeles;

import main.modeles.entites.Entite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Toigon {
    private int numero;
    private boolean estToit;
    private HashMap<Entite.Type, ArrayList<Entite>> entites;
    private HashMap<Direction, Toigon> voisins;

    public Toigon(int numero, boolean estToit) {
        this.numero = numero;
        this.estToit = estToit;
        this.entites = new HashMap<>();
        this.voisins = new HashMap<>();

        for(Entite.Type type : Entite.Type.values())
            this.entites.put(type, new ArrayList<>());
    }

    public int getNumero() {
        return numero;
    }

    public boolean estToit() {
        return estToit;
    }

    public boolean estLocomotive() {
        return this.numero == 2*Modele.NB_WAGONS-2;
    }

    public void ajouteEntite(Entite entite) {
        this.entites.get(entite.getType()).add(entite);
    }

    public void retireEntite(Entite entite) {
        this.entites.get(entite.getType()).remove(entite);
    }

    public void retireEntites(Entite.Type type) {
        this.entites.get(type).clear();
    }

    public boolean contientEntite(Entite entite) {
        return this.entites.get(entite.getType()).contains(entite);
    }

    public <T> ArrayList<T> getEntites(Entite.Type type) {
        return (ArrayList<T>) this.entites.get(type);
    }

    public <T> T getRandomEntite(Entite.Type type) {
        // Peut lever une exception s'il n'y a aucune entité de ce type.
        int n = new Random().nextInt(this.entites.get(type).size());
        return (T) this.entites.get(type).get(n);
    }

    public Toigon getVoisin(Direction dir) {
        if(!this.voisins.containsKey(dir))
            return null;
        return this.voisins.get(dir);
    }

    public void setVoisin(Direction dir, Toigon voisin) {
        // La direction est retirée de la hashmap lorsqu'il n'y a pas de voisin.
        if(voisin == null) {
            this.voisins.remove(dir);
            return;
        }
        this.voisins.put(dir, voisin);
    }

}
