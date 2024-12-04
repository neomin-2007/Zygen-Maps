package org.zygen.maps.menu;

import org.zygen.maps.Cache;
import org.zygen.maps.action.EditorListener;
import org.zygen.maps.editor.Layer;
import org.zygen.maps.editor.LayerEditor;
import org.zygen.maps.editor.SpriteSheetEditor;
import org.zygen.maps.render.EditorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PixelLayer
extends JFrame {

    private final JLabel layerName;
    private final JTextField layerOption;

    public PixelLayer() {

        setTitle("Layers");
        setSize(600, 100);
        setVisible(true);;
        setLayout(null);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocation(new Point(556, 515));

        layerName = new JLabel("Layer selecionado: " + LayerEditor.getSelectedLayer().getName());
        layerName.setBounds(10, -15, 250, 50);
        add(layerName);

        final JButton layerLater = new JButton("<");
        layerLater.setBounds(60, 20, 45, 25);
        layerLater.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayerEditor.backLayer();
                sendRepaint();
            }
        });
        add(layerLater);

        final JButton layerNext = new JButton(">");
        layerNext.setBounds(120, 20, 45, 25);
        layerNext.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayerEditor.nextLayer();
                sendRepaint();
            }
        });
        add(layerNext);

        layerOption = new JTextField("Nome do Layer");
        layerOption.setBounds(390, -5, 125, 25);
        add(layerOption);

        final JButton layerAdd = new JButton("+");
        layerAdd.setBounds(512, -5, 45, 25);
        layerAdd.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String layerString = layerOption.getText();
                if (layerString.isEmpty()) return;
                new Layer(layerString);
                sendRepaint();

                LayerEditor.loaded_layers.forEach(layer -> {
                    System.out.println(layer.getName());
                });
            }
        });
        add(layerAdd);

        final JButton layerRemove = new JButton("-");
        layerRemove.setBounds(558, -5, 45, 25);
        layerRemove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String layerString = layerOption.getText();
                if (layerString.isEmpty()) return;
                LayerEditor.removeLayer(layerString);
                sendRepaint();
            }
        });
        add(layerRemove);

        repaint();

    }

    private void sendRepaint() {
        SwingUtilities.invokeLater(() -> {

            layerName.setText("Layer selecionado: " + LayerEditor.getSelectedLayer().getName());

            revalidate();
            repaint();
        });
    }

}
