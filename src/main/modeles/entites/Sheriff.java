package main.modeles.entites;

import main.modeles.Direction;
import main.modeles.Toigon;

import java.util.ArrayList;

public class Sheriff extends Entite {
    public String nom;
    public static final double NERVOSITE_SHERIFF = 0.3;

    public Sheriff(int num, Toigon toigon, String nom) {
        super(num, toigon, Type.SHERIFF);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    //@Override
    public void deplace(){
        if (Math.random()<this.NERVOSITE_SHERIFF){
            if (Math.random()<0.5){
                super.deplace(Direction.AVANT);
            }
            else{
                super.deplace((Direction.ARRIERE));
            }
            ArrayList<Bandit> bandits = this.getToigon().getEntites(Entite.Type.BANDIT);
            for(int i = 0; i < bandits.size(); i++) {
                //g.setColor(Color.GREEN);
                if (!bandits.get(i).getToigon().estToit()){
                    bandits.get(i).deposeRandomTresor();
                    bandits.get(i).deplace(Direction.HAUT);
                }


            }

        }
    }

}
