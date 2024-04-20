package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Toigon;

import java.util.ArrayList;

public class Sheriff extends Entite {
    public String nom;
    public static final double NERVOSITE_SHERIFF = 1.0;

    public Sheriff(int num, Toigon toigon, String nom) {
        super(num, toigon, Type.SHERIFF);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void joue(){
        if (Math.random()<this.NERVOSITE_SHERIFF){
            this.deplace(Math.random() <= 0.5 ? Direction.ARRIERE : Direction.AVANT);
            System.out.println("le Shériff se déplace.");
            ArrayList<Bandit> bandits = this.getToigon().getEntites(Entite.Type.BANDIT);
            for(int i = 0; i < bandits.size(); i++) {
                if ((!bandits.get(i).getToigon().estToit()) && (bandits.get(i).possedeTresor())){
                    Tresor tresor = bandits.get(i).deposeRandomTresor();
                    System.out.print("Le Shériff force ");
                    System.out.print(bandits.get(i).getNom());
                    System.out.print(" à lâcher un(e)");
                    System.out.print(tresor.getNom());
                    System.out.print(" qui vaut: ");
                    System.out.print(tresor.getValeur());
                    System.out.println(".");
                    bandits.get(i).deplace(Direction.HAUT);
                } else if ((!bandits.get(i).getToigon().estToit())) {
                    System.out.print("Le Shériff force ");
                    System.out.print(bandits.get(i).getNom());
                    System.out.println(" à s'échapper sur le toit.");
                    bandits.get(i).deplace(Direction.HAUT);
                }
            }
        }
    }

}
