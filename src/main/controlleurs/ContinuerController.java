package main.controlleurs;

import main.modeles.Modele;
import main.modeles.Phase;

import java.awt.event.ActionEvent;

public class ContinuerController extends Controller {
    private int numBandit;

    public ContinuerController(Modele modele) {
        super(modele);
        this.numBandit = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.modele.joueAction(this.numBandit);
        this.numBandit = (this.numBandit+1) % Modele.NB_BANDITS;

        // On prend le bandit suivant qui a une action à jouer.
        while(this.numBandit < Modele.NB_BANDITS
                && this.modele.getBandits().get(this.numBandit).getNombreActions() == 0
        ) this.numBandit++;

        // Quand on a réalisé toutes les premières actions de chaque bandit, on passe aux deuxiemes etc...
        if(this.numBandit == Modele.NB_BANDITS) {
            this.numBandit = 0;

            // Quand toutes les actions de tous les bandits ont été jouées, on passe
            // à la phase de choix.
            if(!this.modele.resteActionAJouee())
                this.modele.changePhase();
        }
    }
}
