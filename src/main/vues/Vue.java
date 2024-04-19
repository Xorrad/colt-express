package main.vues;

import main.modeles.Modele;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Vue implements Observer {
    private Modele modele;
    private JFrame frame;
    private VueTrain vueTrain;
    private VueCommandes vueCommandes;
    private VueBandits vueBandits;

    public Vue(Modele modele) {
        this.modele = modele;
        this.modele.ajouteObserver(this);

        this.frame = new JFrame();
        this.frame.setTitle("Colt Express");
        //this.frame.setLayout(new FlowLayout()); // Elements l'un à la suite de l'autre.
        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS)); // Elements l'un au dessus de l'autre

        // Initialise et ajoute les différentes vues.
        this.vueBandits = new VueBandits(modele);
        this.frame.add(this.vueBandits);
        this.vueTrain = new VueTrain(modele);
        this.frame.add(this.vueTrain);
        this.vueCommandes = new VueCommandes(modele, modele.getTourBandit());
        this.frame.add(this.vueCommandes);

        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack(); // Ajuste la taille de la fenêtre en fonction du contenu.
    }

    public void updateVueCommandes() {
        this.frame.remove(this.vueCommandes);
        this.vueCommandes = new VueCommandes(modele, modele.getTourBandit());
        this.frame.add(this.vueCommandes);
    }

    @Override
    public void update() {
        this.updateVueCommandes();
        this.frame.validate();
        this.frame.repaint();
    }
}
