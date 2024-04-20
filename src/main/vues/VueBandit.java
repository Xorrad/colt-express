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
        this.setOpaque(false);

        // Lire https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        //this.setLayout(new GridBagLayout());
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

        // Image de fond (cadre)
        JImage imageCadre = new JImage(Assets.IMG_CADRE);
        imageCadre.setLayout(new GridBagLayout());

        this.panelInfo = new JPanel();
        this.panelInfo.setOpaque(false);
        this.panelActions = new JPanel();
        this.panelActions.setOpaque(false);

        // Image du personnage
        this.labelImg = new JLabel(new ImageIcon(bandit.getIcon()));
        this.labelImg.setBorder(new EmptyBorder(PADDING/2, PADDING, PADDING/2, PADDING/2));
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        imageCadre.add(this.labelImg, c);

        // Information du joueur (nom, somme, balles...)
        this.labelNom = new JLabel(bandit.getNom());
        this.labelNom.setForeground(bandit.getColor());
        this.panelInfo.add(this.labelNom);

        this.labelSomme = new JLabel("$" + bandit.getSommeTresor());
        this.panelInfo.add(this.labelSomme);

        this.labelBalles = new JLabel(".".repeat(bandit.getNombreBalles()));
        this.panelInfo.add(this.labelBalles);

        this.panelInfo.setLayout(new BoxLayout(this.panelInfo, BoxLayout.Y_AXIS)); // Elements l'un au dessus de l'autre
        this.panelInfo.setBorder(new EmptyBorder(PADDING, PADDING/2, PADDING/2, PADDING));
        c.gridx = 1;
        c.gridy = 0;
        imageCadre.add(this.panelInfo, c);

        // Actions du joueur
//        this.panelActions.add();
        this.panelActions.setBorder(new EmptyBorder(PADDING/2, PADDING, PADDING/2, PADDING));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        imageCadre.add(this.panelActions, c);

        this.add(imageCadre);
    }

    @Override
    public void update() {
        this.labelNom.setText(this.bandit.getNom());
        this.labelSomme.setText("$" + this.bandit.getSommeTresor());
        //this.repaint();
    }
}
