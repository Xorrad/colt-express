package main.vues;

import main.modeles.Modele;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS)); // Elements l'un au dessus de l'autre
        this.frame.setBackground(new Color(248, 248, 132));

        // Les components sont structurés de la façon suivante:
        // JFrame:
        //   - VueBackground:
        //      - VueBandits
        //      - VueTrain
        //   - VueCommandes
        // Donc on ajoute la VueBandits et VueTrain à VueBackground
        // Ces deux vues sont transparentes (i.e setOpaque(false)) pour qu'on puisse
        // voir l'image de fond dessiner par VueBackground au travers.
        VueBackground contentPane = new VueBackground(Assets.IMG_BG);
        contentPane.setBorder(new EmptyBorder(10, 50, 10, 50)); // On ajoute du "padding" au fond pour pas que le train et les cadres des bandits soient trop proche de la fenêtre.
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Elements l'un au dessus de l'autre

        // Initialise et ajoute les différentes vues au VueBackground.
        this.vueBandits = new VueBandits(modele);
        contentPane.add(this.vueBandits);
        this.vueTrain = new VueTrain(modele);
        contentPane.add(this.vueTrain);
        this.frame.add(contentPane);

        // Les commandes ne sont pas dessinées par dessus le fond, donc on les ajoute directement à JFrame.
        this.vueCommandes = new VueCommandes(modele, modele.getTourBandit());
        this.frame.add(this.vueCommandes);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack(); // Ajuste la taille de la fenêtre en fonction du contenu.
        this.frame.setVisible(true);
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
