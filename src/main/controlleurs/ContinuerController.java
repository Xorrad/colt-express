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
        this.modele.joueActions(this.numBandit);
        this.numBandit = (this.numBandit+1) % Modele.NB_BANDITS;
        if(this.modele.getPhaseJeu() == Phase.CHOIX)
            this.numBandit = 0;
    }
}
