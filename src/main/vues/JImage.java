package main.vues;

import javax.swing.*;
import java.awt.*;

public class JImage extends JComponent {
    private Image image;
    private int padding;

    public JImage(Image image) {
        this.image = image;
        this.padding = 0;
    }

    public Image getImage() {
        return image;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int p = this.padding/2;
        g.drawImage(this.image, p, p, getWidth() - this.padding, getHeight() - this.padding, this);
    }
}
