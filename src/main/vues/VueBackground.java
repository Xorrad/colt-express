package main.vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueBackground extends JImage {
    private int offset;

    public VueBackground(Image image) {
        super(image);
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
        g.drawImage(getImage(), this.offset + getWidth(), 0, getWidth(), getHeight(), this);
        g.drawImage(getImage(), this.offset + 1, 0, getWidth(), getHeight(), this);
    }
}
