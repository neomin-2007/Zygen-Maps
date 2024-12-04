package org.zygen.maps.menu;

import org.zygen.maps.Cache;
import org.zygen.maps.action.EditorListener;
import org.zygen.maps.render.EditorPanel;

import javax.swing.*;
import java.awt.*;

public class PixelEditor
extends JFrame {

    public PixelEditor() {

        setTitle("Editor de Mapa");
        setSize(Cache.WIDTH, Cache.HEIGHT);
        setVisible(true);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(new Point(556, 200));

        addKeyListener(new EditorListener.KeyBoardEvent());
        addMouseListener(new EditorListener.MouseEvent());

        add(new EditorPanel(true));

        // Atualiza a interface para refletir as mudanças
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

}
