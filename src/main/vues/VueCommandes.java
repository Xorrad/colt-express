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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class VueCommandes extends JPanel implements Observer {
    private Modele modele;
    private Bandit bandit;

    public VueCommandes(Modele modele, Bandit bandit) {
        this.modele = modele;
        this.bandit = bandit;
        this.setBackground(new Color(248, 248, 132));

        // Image de fond (cadre)
        JImage imageCadre = new JImage(Assets.IMG_CADRE);
        imageCadre.setLayout(new GridBagLayout());

        JLabel labelNom = new JLabel("Tour de " + bandit.getNom());
        imageCadre.add(labelNom);

        JButton boutonArriere = new JButton("<");
        boutonArriere.addActionListener(new ActionController(modele, new ChangeWagonAction(bandit, Direction.ARRIERE)));
        imageCadre.add(boutonArriere);

        JButton boutonAvant = new JButton(">");
        boutonAvant.addActionListener(new ActionController(modele, new ChangeWagonAction(bandit, Direction.AVANT)));
        imageCadre.add(boutonAvant);

        JButton boutonEtage = new JButton("v^");
        boutonEtage.addActionListener(new ActionController(modele, new ChangeEtageAction(bandit)));
        imageCadre.add(boutonEtage);

        JButton boutonBraquage = new JButton("B");
        boutonBraquage.addActionListener(new ActionController(modele, new BraqueAction(bandit)));
        imageCadre.add(boutonBraquage);

        JButton boutonFin = new JButton("Fin tour");
        boutonFin.addActionListener(new FinTourController(modele));
        imageCadre.add(boutonFin);

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
    }
}
