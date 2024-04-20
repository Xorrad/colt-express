package main.vues;

import main.controlleurs.ActionController;
import main.controlleurs.ContinuerController;
import main.controlleurs.FinTourController;
import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Phase;
import main.modeles.actions.BraqueAction;
import main.modeles.actions.ChangeEtageAction;
import main.modeles.actions.ChangeWagonAction;
import main.modeles.entites.Bandit;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VueCommandes extends JPanel implements Observer {
    private Modele modele;
    private Bandit bandit;

    private JPanel panelCommandes;

    private JPanel panelCommandesBandit;
    private JLabel labelNom;

    public VueCommandes(Modele modele, Bandit bandit) {
        this.modele = modele;
        this.bandit = bandit;
        this.modele.ajouteObserver(this);
        this.setBackground(new Color(248, 248, 132));

        // Image de fond (cadre)
        JImage imageCadre = new JImage(Assets.IMG_CADRE);
        //imageCadre.setLayout(new GridBagLayout());
        imageCadre.setLayout(new FlowLayout());

        this.panelCommandes = new JPanel();
        this.panelCommandes.setOpaque(false);
        this.panelCommandes.setVisible(false);

        this.panelCommandesBandit = new JPanel();
        this.panelCommandesBandit.setOpaque(false);

        JLabel labelPhase = new JLabel("Phase de jeu");
        imageCadre.add(labelPhase);

        JButton boutonContinuer = new JButton("Continuer");
        boutonContinuer.addActionListener(new ContinuerController(modele));
        this.panelCommandes.add(boutonContinuer);

        this.labelNom = new JLabel(bandit.getNom());
        this.labelNom.setForeground(bandit.getColor().darker());
        this.panelCommandesBandit.add(this.labelNom);

        JButton boutonArriere = new JButton("<");
        boutonArriere.addActionListener(new ActionController(modele, new ChangeWagonAction(Direction.ARRIERE)));
        this.panelCommandesBandit.add(boutonArriere);

        JButton boutonAvant = new JButton(">");
        boutonAvant.addActionListener(new ActionController(modele, new ChangeWagonAction(Direction.AVANT)));
        this.panelCommandesBandit.add(boutonAvant);

        JButton boutonEtage = new JButton("v^");
        boutonEtage.addActionListener(new ActionController(modele, new ChangeEtageAction()));
        this.panelCommandesBandit.add(boutonEtage);

        JButton boutonBraquage = new JButton("B");
        boutonBraquage.addActionListener(new ActionController(modele, new BraqueAction()));
        this.panelCommandesBandit.add(boutonBraquage);

        JButton boutonFin = new JButton("Fin tour");
        boutonFin.addActionListener(new FinTourController(modele));
        this.panelCommandesBandit.add(boutonFin);

        imageCadre.add(this.panelCommandes);
        imageCadre.add(this.panelCommandesBandit);

        JImage imageTitre = new JImage(Assets.IMG_TITRE);
        imageTitre.setPreferredSize(new Dimension(200, 100));
        imageTitre.setBorder(new CompoundBorder(
                new EmptyBorder(7, 0, 7, 0),
                new MatteBorder(0, 7, 0, 0, new Color(120, 72, 31))
        ));
        imageTitre.setPadding(20);
        imageCadre.add(imageTitre);

        this.add(imageCadre);
    }

    @Override
    public void update() {

        if(this.modele.getPhaseJeu() == Phase.CHOIX && this.bandit != this.modele.getTourBandit()) {
            this.panelCommandes.setVisible(false);
            this.panelCommandesBandit.setVisible(true);
            this.bandit = this.modele.getTourBandit();

            // Met à jour le nom du bandit dans le texte.
            this.labelNom.setText(this.bandit.getNom());
            this.labelNom.setForeground(this.bandit.getColor().darker());
        }
        else if(this.modele.getPhaseJeu() == Phase.ACTIONS) {
            this.panelCommandes.setVisible(true);
            this.panelCommandesBandit.setVisible(false);
        }

        this.revalidate();
        this.repaint();
    }
}
