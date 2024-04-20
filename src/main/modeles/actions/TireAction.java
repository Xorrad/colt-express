package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.Toigon;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.modeles.entites.Tresor;
import main.vues.Assets;

import java.awt.*;
import java.util.ArrayList;

public class TireAction extends Action {
    private Direction dir;

    public TireAction(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public Image getIcon() {
        switch(this.dir) {
            case AVANT:
                return Assets.IMG_ACTIONS_TIRE_AVANT;
            case ARRIERE:
                return Assets.IMG_ACTIONS_TIRE_ARRIERE;
            case HAUT:
                return Assets.IMG_ACTIONS_TIRE_HAUT;
            case BAS:
                return Assets.IMG_ACTIONS_TIRE_BAS;
        }
        return Assets.IMG_ACTIONS_TIRE_BAS;
    }

    @Override
    public void apply(Bandit bandit) {
        if(!this.canApply(bandit)) {
            System.out.println(bandit.getNom() + " ne peut pas tirer.");
            return;
        }
        bandit.setNombreBalles(bandit.getNombreBalles()-1);

        ArrayList<Bandit> bandits = bandit.getToigon().getVoisin(this.dir).getEntites(Entite.Type.BANDIT);
        for(int i = 0; i < bandits.size(); i++) {
            Tresor tresor = bandits.get(i).deposeRandomTresor();
            if (tresor==null){
                System.out.println(bandits.get(i).getNom()+" se fait tirer dessus par "+ bandit.getNom()+ " mais il ne portait rien.");
            }
            else{
                System.out.println(bandits.get(i).getNom()+" se fait tirer dessus par "+ bandit.getNom()+ " et lâche un(e) "+ tresor.getNom()+" qui vaut $"+ tresor.getValeur()+".");
            }
        }
    }

    @Override
    public boolean canApply(Bandit bandit){
        return (bandit.getNombreBalles()>0 && bandit.getToigon().getVoisin(this.dir)!=null);
    }
}
