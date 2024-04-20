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

    public ChangeWagonAction(Direction dir) {
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
    public void apply(Bandit bandit) {
        if(!this.canApply(bandit)) {
            if(this.dir == Direction.AVANT) {
                System.out.println(bandit.getNom() + " est déjà sur la locomotive.");
            }
            else {
                System.out.println(bandit.getNom() + " est déjà sur le dernier wagon.");
            }
            return;
        }
        //throw new RuntimeException(this.bandit.getNom() + " ne peut pas aller au wagon en " + this.dir.name());

        bandit.deplace(this.dir);
        System.out.println(bandit.getNom() + " passe au wagon " + this.dir.name().toLowerCase() + ".");
    }

    @Override
    public boolean canApply(Bandit bandit) {
        return bandit.getToigon().getVoisin(this.dir) != null;
    }
}
