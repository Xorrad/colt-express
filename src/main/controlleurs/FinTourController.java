package main.controlleurs;

import main.modeles.Modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinTourController extends Controller {
    public FinTourController(Modele modele) {
        super(modele);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.modele.avanceTour();
    }
}
