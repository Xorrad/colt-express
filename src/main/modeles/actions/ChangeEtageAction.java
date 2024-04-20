package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;
import main.vues.Assets;

import java.awt.*;

public class ChangeEtageAction extends Action {
    @Override
    public Image getIcon() {
        return Assets.IMG_ACTIONS_ETAGES;
    }

    @Override
    public void apply(Bandit bandit) {
        if(bandit.getToigon().estToit()) {
            bandit.deplace(Direction.BAS);
            System.out.println(bandit.getNom() + " entre dans le wagon.");
        }
        else {
            bandit.deplace(Direction.HAUT);
            System.out.println(bandit.getNom() + " grimpe sur le toit.");
        }
    }

    @Override
    public boolean canApply(Bandit bandit) {
        return true;
    }
}
