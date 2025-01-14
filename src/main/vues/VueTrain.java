package main.vues;

import main.Assets;
import main.modeles.Modele;
import main.modeles.Toigon;
import main.modeles.entites.Bandit;
import main.modeles.entites.Entite;
import main.modeles.entites.Sheriff;
import main.modeles.entites.Tresor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class VueTrain extends JPanel implements Observer {
    private Modele modele;

    public VueTrain(Modele modele) {
        this.modele = modele;
        this.modele.ajouteObserver(this);

        Dimension dim = new Dimension((Assets.IMG_WAGON.getWidth())*Modele.NB_WAGONS + 10, 2*Assets.IMG_WAGON.getHeight() + 10);
        this.setPreferredSize(dim);
        this.setBorder(new EmptyBorder(10, 0, 0, 0));
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i = 0; i < this.modele.getNombreToigons(); i++) {
            Toigon toigon = this.modele.getToigon(i);
            paintToigon(g, toigon, (i/2)*(Assets.IMG_WAGON.getWidth()),
                    (i%2 == 0) ? Assets.IMG_WAGON.getHeight() : 0
            );
        }
    }

    private void paintToigon(Graphics g, Toigon toigon, int x, int y) {
        if(!toigon.estToit()) {
            g.drawImage(toigon.estLocomotive() ? Assets.IMG_LOCOMOTIVE : Assets.IMG_WAGON, x, y, this);
        }
        else {
            // À conserver pour test/debug.
            //g.setColor(Color.RED);
            //g.drawRect(x, y, Assets.IMG_WAGON.getWidth(), Assets.IMG_WAGON.getHeight());
        }

        // On recuppère la liste des personnages sur le toigon.
        ArrayList<Bandit> bandits = toigon.getEntites(Entite.Type.BANDIT);
        ArrayList<Sheriff> sheriffs = toigon.getEntites(Entite.Type.SHERIFF);
        ArrayList<Tresor> tresors = toigon.getEntites(Entite.Type.TRESOR);

        // On définie les positions minimales et maximales
        // pour les personnages, pour pouvoir les répartir
        // uniformement sur la surface (toit ou sol) de leur toigon.
        // On ajoute également une légère marge aux bords.
        int marge = 30;
        int xMin = marge;
        int xMax = toigon.estWagonLocomotive() ? (int) ((float) Assets.IMG_LOCOMOTIVE.getWidth() / 2.25f) - marge : Assets.IMG_WAGON.getWidth() - marge;
        int step = Math.max(1, (xMax - xMin) / Modele.NB_BANDITS);

        // On décale légèrement les personnages verticalement
        // pour qu'ils soient bien "posés" sur le sol ou toit.
        int offsetToit = toigon.estToit() ? 12 : -12;

        // Dessine les bandits
        // La position d'un bandit dans un wagon est fixe pour mieux suivre leurs déplacements/actions.
        for (int i = 0; i < bandits.size(); i++) {
            g.setColor(bandits.get(i).getColor());
            g.drawImage(bandits.get(i).getImage(), x + xMin + bandits.get(i).getNum()*step, y + 40 + offsetToit, 40, 40, this);
        }

        // Dessine les sheriffs
        for (int i = 0; i < sheriffs.size(); i++) {
            g.drawImage(Assets.IMG_SHERIFF, x + xMin + sheriffs.get(i).getNum()*step, y + 40 + offsetToit, 40, 40, this);
        }

        // Dessine les trésors
        if(tresors.size() > 0) {
            step = Math.max(1, (xMax - xMin) / tresors.size());
            for (int i = 0; i < tresors.size(); i++) {
                g.drawImage(tresors.get(i).getIcon(), x + xMin + i*step, y + 55 + offsetToit, 25, 25, this);
            }
        }
    }
}
