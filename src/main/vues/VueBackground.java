package main.vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueBackground extends JComponent {
    private Image image;
    private int offset;

    public VueBackground(Image image) {
        this.image = image;
        this.offset = 0;

        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Max pour éviter la division par 0 à la première itération.
                offset = (offset-1) % Math.max(getWidth(), 1);
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, this.offset + getWidth(), 0, getWidth(), getHeight(), this);
        g.drawImage(this.image, this.offset + 1, 0, getWidth(), getHeight(), this);

        // https://stackoverflow.com/questions/19480076/java-animation-stutters-when-not-moving-mouse-cursor
        // "This flushs the graphics buffer which some systems like Linux use."
        // Règle les problèmes de "lag" sur l'animation du fond d'écran.
        Toolkit.getDefaultToolkit().sync();
    }
}
