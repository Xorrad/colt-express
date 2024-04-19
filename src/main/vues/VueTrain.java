package main.vues;

import main.modeles.Modele;
import main.modeles.Toigon;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.modeles.entites.Sheriff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueTrain extends JPanel implements Observer {
    private static final int LARGEUR_WAGON = 100;
    private static final int HAUTEUR_WAGON = 50;
    private static final int MARGE_WAGON = 5;

    private Modele modele;

    public VueTrain(Modele modele) {
        this.modele = modele;
        this.modele.ajouteObserver(this);

        Dimension dim = new Dimension((LARGEUR_WAGON+MARGE_WAGON)*Modele.NB_WAGONS + 10, 2*HAUTEUR_WAGON + 10);
        this.setPreferredSize(dim);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i = 0; i < this.modele.getNombreToigons(); i++) {
            Toigon toigon = this.modele.getToigon(i);
            paintToigon(g, toigon, (i/2)*(LARGEUR_WAGON+MARGE_WAGON), (int) (0.9*getHeight()) - (1 + (i%2))*HAUTEUR_WAGON);
        }
    }

    private void paintToigon(Graphics g, Toigon toigon, int x, int y) {
        g.setColor(Color.BLACK);
        if(toigon.estToit())
            g.setColor(Color.RED);
        g.drawRect(x, y, LARGEUR_WAGON, HAUTEUR_WAGON);

        // Dessine des cercles pour les bandits
        ArrayList<Bandit> bandits = toigon.getEntites(Entite.Type.BANDIT);
        for(int i = 0; i < bandits.size(); i++) {
            //g.setColor(Color.GREEN);
            g.setColor(bandits.get(i).getColor());
            g.drawImage(bandits.get(i).getImage(), x + i*42 + 2, y + 8, 40, 40, this);
            //g.fillOval(x + i*20, y + HAUTEUR_WAGON/2, 20, 20);
        }

        // Dessine des cercles pour les bandits
        ArrayList<Sheriff> sheriffs = toigon.getEntites(Entite.Type.SHERIFF);
        for(int i = 0; i < sheriffs.size(); i++) {
            g.setColor(Color.YELLOW);
            g.fillRect(x + LARGEUR_WAGON - (i+1)*30, y + HAUTEUR_WAGON/2 - 15, 30, 30);
        }
    }
}
