package main.controlleurs;

import main.modeles.Modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FinTourController extends Controller implements MouseListener {
    public FinTourController(Modele modele) {
        super(modele);
    }

    public void prochainJoueur() {
        this.modele.avanceTour();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.prochainJoueur();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.prochainJoueur();
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
