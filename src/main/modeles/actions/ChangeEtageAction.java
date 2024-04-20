package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.vues.Assets;

import java.awt.*;

public class ChangeEtageAction extends Action {

    @Override
    public Image getIcon() {
        return Assets.IMG_ACTIONS_ETAGES;
    }



    public ChangeEtageAction(Bandit bandit) {
        super(bandit);
    }

    @Override
    public void apply() {
        if(!this.canApply()) {
            System.out.println(this.bandit.getNom() + " ne peut pas descendre car le Shériff est dans le wagon.");
            return;
        }
        //throw new RuntimeException(this.bandit.getNom() + " ne peut pas changer d'étage sur la locomotive.");

        if(this.bandit.getToigon().estToit()) {
            this.bandit.deplace(Direction.BAS);
            System.out.println(this.bandit.getNom() + " entre dans le wagon.");
        }
        else {
            this.bandit.deplace(Direction.HAUT);
            System.out.println(this.bandit.getNom() + " grimpe sur le toit.");
        }
    }

    @Override
    public boolean canApply() {
        return this.bandit.getToigon().getHautBas().getEntites(Entite.Type.SHERIFF).isEmpty();
    }
}
