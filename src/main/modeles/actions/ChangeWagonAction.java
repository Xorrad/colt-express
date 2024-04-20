package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;

import main.vues.Assets;

import java.awt.*;
import main.modeles.entites.Entite;
import main.vues.Assets;

import java.awt.*;

public class ChangeWagonAction extends Action {
    private Direction dir;

    public ChangeWagonAction(Bandit bandit, Direction dir) {
        super(bandit);
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public Image getIcon() {
        if(this.dir == Direction.ARRIERE)
            return Assets.IMG_ACTIONS_MOVE_ARRIERE;
        return Assets.IMG_ACTIONS_MOVE_AVANT;
    }

    @Override
    public void apply() {
        if(!this.canApply()) {
            if (this.bandit.getToigon().getVoisin(dir)==null || !this.bandit.getToigon().getVoisin(dir).getEntites(Entite.Type.SHERIFF).isEmpty()){
                System.out.println(this.bandit.getNom() + " est bloqué par le Shérif");
            }

            else if(this.dir == Direction.AVANT) {
                System.out.println(this.bandit.getNom() + " est déjà sur la locomotive.");
            }
            else {
                System.out.println(this.bandit.getNom() + " est déjà sur le dernier wagon.");
            }
            return;
        }
        //throw new RuntimeException(this.bandit.getNom() + " ne peut pas aller au wagon en " + this.dir.name());

        this.bandit.deplace(this.dir);
        System.out.println(this.bandit.getNom() + " passe au wagon " + this.dir.name().toLowerCase() + ".");
    }

    @Override
    public boolean canApply() {
        return (this.bandit.getToigon().getVoisin(dir)==null || this.bandit.getToigon().getVoisin(dir).getEntites(Entite.Type.SHERIFF).isEmpty());
    }
}
