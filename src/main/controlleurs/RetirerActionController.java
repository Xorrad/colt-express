package main.controlleurs;

import main.modeles.Modele;
import main.modeles.actions.Action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RetirerActionController extends Controller implements MouseListener {
    private Action action;

    public RetirerActionController(Modele modele, Action action) {
        super(modele);
        this.action = action;
    }

    public void apply() {
        this.modele.getTourBandit().retireAction(this.action);
        this.modele.notifyObservers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.apply();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.apply();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
