package main.vues;

import main.controlleurs.ActionController;
import main.controlleurs.FinTourController;
import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.actions.BraqueAction;
import main.modeles.actions.ChangeEtageAction;
import main.modeles.actions.ChangeWagonAction;
import main.modeles.entites.Bandit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class VueBandit extends JPanel implements Observer {
    private Modele modele;
    private Bandit bandit;

    private JLabel labelImg; // A changer en image du personnage.
    private JPanel panelInfo;
    private JLabel labelNom;
    private JLabel labelSomme;
    private JLabel labelBalles;

    private JPanel panelActions;

    private final int PADDING = 20;

    public VueBandit(Modele modele, Bandit bandit) {
        this.modele = modele;
        this.bandit = bandit;
        this.modele.ajouteObserver(this);

        // Lire https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        this.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints(
//                0, 0, // grid x,y
//                1, 1, // grid width,height
//                0.5, 0.5, // weight x,y
//                GridBagConstraints.CENTER,
//                GridBagConstraints.HORIZONTAL,
//                new Insets(0,0,0,0),
//                0, 0
//        );
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.panelInfo = new JPanel();
        this.panelActions = new JPanel();

        // Image du personnage
        this.labelImg = new JLabel("IMG");
        this.labelImg.setBorder(new EmptyBorder(PADDING/2, PADDING, PADDING/2, PADDING/2));
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.labelImg, c);

        // Information du joueur (nom, somme, balles...)
        this.labelNom = new JLabel(bandit.getNom());
        this.panelInfo.add(this.labelNom);

        this.labelSomme = new JLabel("$" + bandit.getSommeTresor());
        this.panelInfo.add(this.labelSomme);

        this.labelBalles = new JLabel(".".repeat(bandit.getNombreBalles()));
        this.panelInfo.add(this.labelBalles);

        this.panelInfo.setLayout(new BoxLayout(this.panelInfo, BoxLayout.Y_AXIS)); // Elements l'un au dessus de l'autre
        this.panelInfo.setBorder(new EmptyBorder(PADDING/2, PADDING/2, PADDING/2, PADDING));
        c.gridx = 1;
        c.gridy = 0;
        this.add(this.panelInfo, c);

        // Actions du joueur
        //this.panelActions.add();
        this.panelActions.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(2, 0, 0, 0, this.bandit.getColor()),
                new EmptyBorder(PADDING/2, PADDING, PADDING/2, PADDING))
        );
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(this.panelActions, c);

//        this.setBorder(BorderFactory.createCompoundBorder(
//                new MatteBorder(2, 2, 2, 2, this.bandit.getColor()),
//                new EmptyBorder(PADDING/2, PADDING, PADDING/2, PADDING))
//        );
//
        this.setBorder(new MatteBorder(2, 2, 2, 2, this.bandit.getColor()));
    }

    @Override
    public void update() {
        this.labelNom.setText(this.bandit.getNom());
        this.labelSomme.setText("$" + this.bandit.getSommeTresor());
        //this.repaint();
    }
}
