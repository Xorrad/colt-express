package main.modeles.actions;

import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.modeles.entites.Tresor;
import main.Assets;

import java.awt.*;

public class BraqueAction extends Action {
    @Override
    public Image getIcon() {
        return Assets.IMG_ACTIONS_BRAQUE;
    }

    @Override
    public void apply(Bandit bandit) {
        if(!this.canApply(bandit)) {
            System.out.println(bandit.getNom() + " n'a aucun trésor à braquer sur son wagon.");
            return;
        }

        Tresor tresor = bandit.getToigon().getRandomEntite(Entite.Type.TRESOR);
        tresor.getToigon().retireEntite(tresor);
        System.out.println(bandit.getNom() + " vole " + tresor.getNom() + " d'une valeur de $" + tresor.getValeur());
        bandit.ajouteTresor(tresor);
    }

    @Override
    public boolean canApply(Bandit bandit) {
        return bandit.getToigon().getEntites(Entite.Type.TRESOR).size() > 0;
    }
}
