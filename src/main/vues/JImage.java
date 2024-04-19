package main.vues;

import javax.swing.*;
import java.awt.*;

public class JImage extends JComponent {
    private Image image;

    public JImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
    }
}
