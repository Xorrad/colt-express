package main.vues;

import javax.swing.*;
import java.awt.*;

public class JIcon extends JComponent {
    private Image image;
    private int nombre;

    public JIcon(Image image, int nombre) {
        this.image = image;
        this.nombre = nombre;
        this.setPreferredSize(new Dimension(image.getWidth(null) * this.nombre, image.getHeight(null)));
    }

    public Image getImage() {
        return image;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < this.nombre; i++) {
            g.drawImage(this.image, i*this.image.getWidth(null), 0, this);
        }
    }
}
