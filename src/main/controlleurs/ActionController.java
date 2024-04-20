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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.action.getBandit().getActions().size() >= 3)
            return;
        this.action.getBandit().ajouteAction(this.action);
        this.modele.notifyObservers();
    }
}
