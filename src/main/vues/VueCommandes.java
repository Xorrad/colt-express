package main.vues;

import main.controlleurs.*;
import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Phase;
import main.modeles.actions.BraqueAction;
import main.modeles.actions.ChangeEtageAction;
import main.modeles.actions.ChangeWagonAction;
import main.modeles.actions.TireAction;
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

    private JLabel labelPhase;
    private JPanel panelCommandes;

    private JPanel panelCommandesBandit;
    private JLabel labelNom;

    public VueCommandes(Modele modele, Bandit bandit) {
        this.modele = modele;
        this.bandit = bandit;
        this.modele.ajouteObserver(this);
        this.setBackground(new Color(248, 248, 132));

        // Image de fond (cadre)
        JImage imageCadre = new JImage(Assets.IMG_CADRE_GRAND);
        imageCadre.setLayout(new FlowLayout());

        this.panelCommandes = new JPanel();
        this.panelCommandes.setOpaque(false);
        this.panelCommandes.setVisible(false);
        this.panelCommandes.add(Box.createRigidArea(new Dimension(397, 100)));

        this.panelCommandesBandit = new JPanel();
        this.panelCommandesBandit.setOpaque(false);
        //this.panelCommandesBandit.setLayout(new BoxLayout(this.panelCommandesBandit, BoxLayout.Y_AXIS));

        JPanel panelInfo = new JPanel();
        panelInfo.setOpaque(false);
        panelInfo.setBorder(new EmptyBorder(0, 30, 0, 0));
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        this.labelPhase = new JLabel("Phase de choix");
        this.labelPhase.setFont(Assets.FONT_RIOGRANDE);
        this.labelPhase.setForeground(new Color(207, 178, 47));
        panelInfo.add(this.labelPhase);

        JIcon boutonContinuer = new JIcon(Assets.IMG_ACTIONS_INCONNUE, 1);
        boutonContinuer.addMouseListener(new ContinuerController(modele));
        boutonContinuer.addMouseListener(new BoutonAnimationController(boutonContinuer));
        this.panelCommandes.add(boutonContinuer);

        this.labelNom = new JLabel(bandit.getNom());
        this.labelNom.setForeground(bandit.getColor().darker());
        this.labelNom.setFont(Assets.FONT_RIOGRANDE);
        panelInfo.add(this.labelNom);

        imageCadre.add(panelInfo);

        // Boutons actions tir.
        JPanel panelTir = new JPanel();
        panelTir.setOpaque(false);
        panelTir.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        JIcon boutonTirArriere = new JIcon(Assets.IMG_ACTIONS_TIRE_ARRIERE, 1);
        boutonTirArriere.addMouseListener(new ActionController(modele, new TireAction(Direction.ARRIERE)));
        boutonTirArriere.addMouseListener(new BoutonAnimationController(boutonTirArriere));
        panelTir.add(boutonTirArriere, c);

        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        JIcon boutonTirAvant = new JIcon(Assets.IMG_ACTIONS_TIRE_AVANT, 1);
        boutonTirAvant.addMouseListener(new ActionController(modele, new TireAction(Direction.AVANT)));
        boutonTirAvant.addMouseListener(new BoutonAnimationController(boutonTirAvant));
        panelTir.add(boutonTirAvant, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        JIcon boutonTirHaut = new JIcon(Assets.IMG_ACTIONS_TIRE_HAUT, 1);
        boutonTirHaut.addMouseListener(new ActionController(modele, new TireAction(Direction.HAUT)));
        boutonTirHaut.addMouseListener(new BoutonAnimationController(boutonTirHaut));
        panelTir.add(boutonTirHaut, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        JIcon boutonTirBas = new JIcon(Assets.IMG_ACTIONS_TIRE_BAS, 1);
        boutonTirBas.addMouseListener(new ActionController(modele, new TireAction(Direction.BAS)));
        boutonTirBas.addMouseListener(new BoutonAnimationController(boutonTirBas));
        panelTir.add(boutonTirBas, c);

        // Boutons actions deplacements.
        JIcon boutonArriere = new JIcon(Assets.IMG_ACTIONS_MOVE_ARRIERE, 1);
        boutonArriere.addMouseListener(new ActionController(modele, new ChangeWagonAction(Direction.ARRIERE)));
        boutonArriere.addMouseListener(new BoutonAnimationController(boutonArriere));
        this.panelCommandesBandit.add(boutonArriere);

        JIcon boutonAvant = new JIcon(Assets.IMG_ACTIONS_MOVE_AVANT, 1);
        boutonAvant.addMouseListener(new ActionController(modele, new ChangeWagonAction(Direction.AVANT)));
        boutonAvant.addMouseListener(new BoutonAnimationController(boutonAvant));
        this.panelCommandesBandit.add(boutonAvant);

        JIcon boutonEtage = new JIcon(Assets.IMG_ACTIONS_ETAGES, 1);
        boutonEtage.addMouseListener(new ActionController(modele, new ChangeEtageAction()));
        boutonEtage.addMouseListener(new BoutonAnimationController(boutonEtage));
        this.panelCommandesBandit.add(boutonEtage);

        // Bouton autres.
        JIcon boutonBraquage = new JIcon(Assets.IMG_ACTIONS_BRAQUE, 1);
        boutonBraquage.addMouseListener(new ActionController(modele, new BraqueAction()));
        boutonBraquage.addMouseListener(new BoutonAnimationController(boutonBraquage));
        this.panelCommandesBandit.add(boutonBraquage);

        this.panelCommandesBandit.add(panelTir);

        JIcon boutonFin = new JIcon(Assets.IMG_ACTIONS_INCONNUE, 1);
        boutonFin.addMouseListener(new FinTourController(modele));
        boutonFin.addMouseListener(new BoutonAnimationController(boutonFin));
        this.panelCommandesBandit.add(boutonFin);

        imageCadre.add(this.panelCommandes);
        imageCadre.add(this.panelCommandesBandit);

        JImage imageTitre = new JImage(Assets.IMG_TITRE);
        imageTitre.setPreferredSize(new Dimension(200, 100));
        imageTitre.setBorder(new CompoundBorder(
                new EmptyBorder(0, 0, 0, 0),
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

            this.labelPhase.setText("Phase de choix");

            // Met à jour le nom du bandit dans le texte.
            this.labelNom.setText(this.bandit.getNom());
            this.labelNom.setForeground(this.bandit.getColor().darker());
            this.labelNom.setVisible(true);
        }
        else if(this.modele.getPhaseJeu() == Phase.ACTIONS) {
            this.panelCommandes.setVisible(true);
            this.panelCommandesBandit.setVisible(false);

            this.labelNom.setText("     ");
            this.labelPhase.setText("Phase de jeu");
        }

        this.revalidate();
        this.repaint();
    }
}
