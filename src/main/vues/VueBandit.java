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

public class VueBandit extends JPanel implements Observer {
    private Modele modele;
    private Bandit bandit;

    public VueBandit(Modele modele, Bandit bandit) {
        this.modele = modele;
        this.bandit = bandit;

        JLabel labelNom = new JLabel("Tour de " + bandit.getNom());
        this.add(labelNom);

        JButton boutonArriere = new JButton("<");
        boutonArriere.addActionListener(new ActionController(modele, new ChangeWagonAction(bandit, Direction.ARRIERE)));
        this.add(boutonArriere);

        JButton boutonAvant = new JButton(">");
        boutonAvant.addActionListener(new ActionController(modele, new ChangeWagonAction(bandit, Direction.AVANT)));
        this.add(boutonAvant);

        JButton boutonEtage = new JButton("v^");
        boutonEtage.addActionListener(new ActionController(modele, new ChangeEtageAction(bandit)));
        this.add(boutonEtage);

        JButton boutonBraquage = new JButton("B");
        boutonBraquage.addActionListener(new ActionController(modele, new BraqueAction(bandit)));
        this.add(boutonBraquage);

        JButton boutonFin = new JButton("Fin tour");
        boutonFin.addActionListener(new FinTourController(modele));
        this.add(boutonFin);
    }

    @Override
    public void update() {
    }
}
