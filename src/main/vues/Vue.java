package main.vues;

import main.modeles.Modele;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private VueTrain train;
    private VueCommandes commandes;

    public Vue(Modele modele) {
        this.frame = new JFrame();
        this.frame.setTitle("Colt Express");
        this.frame.setLayout(new FlowLayout()); // Elements l'un à la suite de l'autre.

        // Initialise et ajoute les différentes vues.
        this.train = new VueTrain(modele);
        this.frame.add(this.train);
        this.commandes = new VueCommandes(modele);
        this.frame.add(this.commandes);

        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack(); // Ajuste la taille de la fenêtre en fonction du contenu.
    }
}
