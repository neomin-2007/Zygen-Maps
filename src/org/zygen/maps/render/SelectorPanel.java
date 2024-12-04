package org.zygen.maps.render;

import javax.swing.*;
import java.awt.*;

public class SelectorPanel
extends JPanel {

    public SelectorPanel(boolean repeatable) {
        if (!repeatable) return;

        final Timer repaintTask = new Timer(5, t -> repaint());
        repaintTask.start();

    }

    @Override
    public void paint(Graphics g) {

        for (int column = 0; column <= 8; column++) {
            for (int line = 0; line <= 16; line++) {
                g.drawRect(column * 16, line * 16, 16, 16);
            }
        }

    }
}
