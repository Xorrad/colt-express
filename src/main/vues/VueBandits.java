package main.vues;

import main.modeles.Modele;
import main.modeles.entites.Bandit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class VueBandits extends JPanel {
    private Modele modele;

    public VueBandits(Modele modele) {
        this.modele = modele;
        this.setOpaque(false);

        for(Bandit bandit : modele.getBandits().values()) {
            VueBandit vueBandit = new VueBandit(modele, bandit);
            vueBandit.setBorder(new EmptyBorder(0, 0, 10, 0));
            this.add(vueBandit);
        }
    }
}
