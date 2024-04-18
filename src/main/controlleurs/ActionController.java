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
        // Je suppose qu'on ne peut faire qu'une seule fois la même action???
        if(!this.action.getBandit().contientAction(this.action)) {
            this.action.getBandit().ajouteAction(this.action);
        }
    }
}
