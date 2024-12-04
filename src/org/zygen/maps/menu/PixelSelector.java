package org.zygen.maps.menu;

import org.zygen.maps.Cache;
import org.zygen.maps.utilities.TimedInteger;
import org.zygen.maps.editor.SpriteSheet;
import org.zygen.maps.editor.SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PixelSelector extends JFrame {

    private final List<JButton> loadedTiles = new LinkedList<>();
    private final JLabel tileInfo;
    private final JLabel tileAmount;
    private final JLabel pageInfo;

    private final TimedInteger tilePage = new TimedInteger();

    public PixelSelector() {
        setTitle("Sprites");
        setSize(208, 256);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(836, 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tileInfo = new JLabel("Tilesets carregados: " + SpriteSheetEditor.loaded_spriteSheets.size());
        tileInfo.setBounds(40, 125, 250, 50);
        add(tileInfo);

        tileAmount = new JLabel("Tilesets totais: " + SpriteSheetEditor.loaded_tiles.size());
        tileAmount.setBounds(50, 145, 250, 50);
        add(tileAmount);

        pageInfo = new JLabel("Página atual: " + SpriteSheetEditor.getPage());
        pageInfo.setBounds(50, 165, 250, 50);
        add(pageInfo);

        drawButtons();
        drawTiles(false);
    }

    public void drawButtons() {
        final JButton lastTile = new JButton("<");
        lastTile.setBounds(0, 206, 100, 50);
        lastTile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tilePage.getValue() == SpriteSheetEditor.loaded_spriteSheets.get(0).length()) {
                    tilePage.setValue(0);
                } else {
                    tilePage.setValue(tilePage.getValue() - SpriteSheetEditor.getPreviousSprite().length());
                }

                SpriteSheetEditor.backPage();

                updatePageInfo();
                drawTiles(true);  // Limpa e redesenha os tiles
            }
        });
        add(lastTile);

        final JButton nextTile = new JButton(">");
        nextTile.setBounds(100, 206, 100, 50);
        nextTile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tilePage.getValue() == 0) {
                    tilePage.setValue(SpriteSheetEditor.getSprite().length());
                } else {
                    tilePage.setValue(SpriteSheetEditor.getSprite().length() + tilePage.getValue());
                }

                tilePage.setDuoValue(tilePage.getDuoValue() + tilePage.getValue());

                SpriteSheetEditor.nextPage();

                updatePageInfo();
                drawTiles(true);  // Limpa e redesenha os tiles
            }
        });
        add(nextTile);
    }

    private void updatePageInfo() {
        tileInfo.setText("Tilesets carregados: " + SpriteSheetEditor.loaded_spriteSheets.size());
        pageInfo.setText("Página atual: " + (SpriteSheetEditor.getPage()));
    }

    public void drawTiles(boolean cleanup) {
        if (cleanup) {
            // Remove os botões da interface e limpa a lista
            loadedTiles.forEach(this::remove);
            loadedTiles.clear();

            // Atualiza a interface para refletir a remoção
            revalidate();
            repaint();
        }

        SpriteSheet spriteSheet = SpriteSheetEditor.loaded_spriteSheets.get(SpriteSheetEditor.spriteSheet);
        if (spriteSheet == null) return;

        BufferedImage[] sprites = spriteSheet.getSprites();

        final int page = SpriteSheetEditor.getPage();

        final AtomicInteger tileIndex = new AtomicInteger(0);
        final AtomicInteger iconIndex = new AtomicInteger(0);

        if (page > 1) {
            tileIndex.set(tilePage.getValue());
        }

        for (int line = 0; line <= 6; line++) {
            for (int column = 0; column <= 12; column++) {
                if (iconIndex.get() >= sprites.length) continue;

                final BufferedImage bufferedImage = sprites[iconIndex.get()];
                if (bufferedImage == null) continue;

                final ImageIcon icon = new ImageIcon(bufferedImage);
                final JButton sprite = new JButton();
                sprite.setBounds(column * 16, line * 16, 16, 16);
                sprite.setIcon(icon);

                int indexed = (tileIndex.get());

                sprite.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Cache.selectedTile = indexed;
                    }
                });

                loadedTiles.add(sprite);
                add(sprite);

                tileIndex.set(tileIndex.get() + 1);
                iconIndex.set(iconIndex.get() + 1);
            }
        }

        // Atualiza a interface para refletir as mudanças
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }
}
