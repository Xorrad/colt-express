package main.vues;

import main.modeles.Modele;
import main.modeles.Toigon;

import javax.swing.*;
import java.awt.*;

public class VueTrain extends JPanel implements Observer {
    private static final int LARGEUR_WAGON = 100;
    private static final int HAUTEUR_WAGON = 50;
    private static final int MARGE_WAGON = 5;

    private Modele modele;

    public VueTrain(Modele modele) {
        this.modele = modele;
        this.modele.ajouteObserver(this);

        Dimension dim = new Dimension((LARGEUR_WAGON+MARGE_WAGON)*Modele.NB_WAGONS, 2*HAUTEUR_WAGON);
        this.setPreferredSize(dim);
    }

    @Override
    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for(int i = 0; i < this.modele.getNombreToigons(); i++) {
            Toigon toigon = this.modele.getToigon(i);
            paint(g, toigon, (i/2)*(LARGEUR_WAGON+MARGE_WAGON), ((i+1)%2)*HAUTEUR_WAGON);
        }
    }

    private void paint(Graphics g, Toigon toigon, int x, int y) {
        g.setColor(Color.BLACK);
        if(toigon.estToit())
            g.setColor(Color.RED);
        g.fillRect(x, y, LARGEUR_WAGON, HAUTEUR_WAGON);
    }
}
