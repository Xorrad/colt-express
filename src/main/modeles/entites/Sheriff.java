package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Toigon;

import java.util.ArrayList;

public class Sheriff extends Entite {
    public String nom;

    public Sheriff(int num, Toigon toigon, String nom) {
        super(num, toigon, Type.SHERIFF);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void joue(){
        if (Math.random() < Modele.NERVOSITE_SHERIFF){
            Direction dir = (Math.random() <= 0.5 ? Direction.ARRIERE : Direction.AVANT);
            if (dir == Direction.AVANT && this.getToigon().getVoisin(dir) == null) {
                this.deplace(Direction.ARRIERE);
            }
            else if (dir == Direction.ARRIERE && this.getToigon().getVoisin(dir) == null) {
            this.deplace(Direction.AVANT);
            }
            else {
                this.deplace(dir);
            }


            //this.deplace(Math.random() <= 0.5 ? Direction.ARRIERE : Direction.AVANT);
            System.out.println("le Shériff se déplace.");
            // On fait une copie de la liste car elle est modifiée lorsqu'on déplace un bandit sur le toit.
            ArrayList<Bandit> bandits = new ArrayList<>(this.getToigon().getEntites(Entite.Type.BANDIT));
            for(Bandit bandit : bandits) {
                if (bandit.possedeTresor()){
                    Tresor tresor = bandit.deposeRandomTresor();
                    System.out.println("Le Shériff force "+bandit.getNom()+" à lâcher un(e)"+tresor.getNom()+" qui vaut $"+tresor.getValeur()+".");
                }
                System.out.println("Le Shériff force "+bandit.getNom()+" à s'échapper sur le toit.");
                bandit.deplace(Direction.HAUT);
            }
        }
    }

}
