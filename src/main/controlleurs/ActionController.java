package main.controlleurs;

import main.modeles.Modele;
import main.modeles.actions.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController extends Controller {
    private Action action;

    public ActionController(Modele modele, Action action) {
        super(modele);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.modele.getTourBandit().getActions().size() >= Modele.NB_ACTIONS)
            return;
        this.modele.getTourBandit().ajouteAction(this.action);
        this.modele.notifyObservers();
    }
}
