package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.Assets;

import java.awt.*;

public class ChangeEtageAction extends Action {

    @Override
    public Image getIcon() {
        return Assets.IMG_ACTIONS_ETAGES;
    }

    @Override
    public void apply(Bandit bandit) {
        if(!this.canApply(bandit)) {
            System.out.println(bandit.getNom() + " ne peut pas descendre car le Shériff est dans le wagon.");
            return;
        }
        //throw new RuntimeException(this.bandit.getNom() + " ne peut pas changer d'étage sur la locomotive.");

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
        return bandit.getToigon().getHautBas().getEntites(Entite.Type.SHERIFF).isEmpty();
    }
}
