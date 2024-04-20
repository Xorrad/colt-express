package main.modeles.actions;

import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.modeles.entites.Tresor;
import main.vues.Assets;

import java.awt.*;

public class BraqueAction extends Action {
    public BraqueAction(Bandit bandit) {
        super(bandit);
    }

    @Override
    public Image getIcon() {
        return Assets.IMG_ACTIONS_BRAQUE;
    }

    @Override
    public void apply() {
        if(!this.canApply()) {
            System.out.println(this.bandit.getNom() + " n'a aucun trésor à braquer sur son wagon.");
            return;
        }

        Tresor tresor = this.bandit.getToigon().getRandomEntite(Entite.Type.TRESOR);
        tresor.getToigon().retireEntite(tresor);
        System.out.println(this.bandit.getNom() + " vole " + tresor.getNom() + " d'une valeur de $" + tresor.getValeur());
        this.bandit.ajouteTresor(tresor);
    }

    @Override
    public boolean canApply() {
        return this.bandit.getToigon().getEntites(Entite.Type.TRESOR).size() > 0;
    }
}
