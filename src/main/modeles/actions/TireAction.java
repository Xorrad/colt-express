package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.Toigon;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;

import java.util.ArrayList;

public class TireAction extends Action {
    private Direction dir;

    public TireAction(Bandit bandit, Direction dir) {
        super(bandit);
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void apply() {
        if(!this.canApply()) {
            System.out.println(this.bandit.getNom() + "ne peut pas tirer.");
            return;
        }
        this.bandit.setNombreBalles(this.bandit.getNombreBalles()-1);

        ArrayList<Bandit> bandits = this.bandit.getToigon().getVoisin(this.dir).getEntites(Entite.Type.BANDIT);
        for(int i = 0; i < bandits.size(); i++) {
            //g.setColor(Color.GREEN);
            bandits.get(i).deposeRandomTresor();
        }


    }

    @Override
    public boolean canApply(){

        return (this.bandit.getNombreBalles()>0 & this.bandit.getToigon().getVoisin(this.dir)!=null);
    }
}
