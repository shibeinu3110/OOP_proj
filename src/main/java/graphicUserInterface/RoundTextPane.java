package graphicUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundTextPane extends JTextPane {
    private int arcWidth;
    private int arcHeight;

    public RoundTextPane(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(0, 0, width, height, arcWidth, arcHeight);

        g2d.setClip(roundRect);
        super.paintComponent(g2d);

        g2d.dispose();
    }
}

