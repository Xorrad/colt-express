package main.controlleurs;

import main.modeles.Modele;
import main.modeles.actions.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionController extends Controller implements MouseListener {
    private Action action;

    public ActionController(Modele modele, Action action) {
        super(modele);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void ajouteAction() {
        if(this.modele.getTourBandit().getActions().size() >= Modele.NB_ACTIONS)
            return;
        this.modele.getTourBandit().ajouteAction(this.action);
        this.modele.notifyObservers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ajouteAction();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.ajouteAction();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
