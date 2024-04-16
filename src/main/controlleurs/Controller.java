package main.controlleurs;

import main.modeles.Modele;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
    protected Modele modele; // Protected pour pouvoir y acceder dans les sous-classes.

    public Controller(Modele modele) {
        this.modele = modele;
    }
}
