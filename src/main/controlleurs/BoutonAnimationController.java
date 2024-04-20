package main.controlleurs;

import main.modeles.Modele;
import main.modeles.actions.Action;
import main.vues.JIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoutonAnimationController implements MouseListener {
    private JIcon icon;

    public BoutonAnimationController(JIcon icon) {
        this.icon = icon;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.icon.setLocation(this.icon.getLocation().x, this.icon.getLocation().y - 5);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.icon.setLocation(this.icon.getLocation().x, this.icon.getLocation().y + 5);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
