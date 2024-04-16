package main.vues;

import main.modeles.Modele;

import javax.swing.*;

public class VueCommandes extends JPanel implements Observer {
    private Modele modele;

    public VueCommandes(Modele modele) {
        this.modele = modele;
        this.modele.ajouteObserver(this);
    }

    @Override
    public void update() {
        // Changement du joueur actif...
    }
}
