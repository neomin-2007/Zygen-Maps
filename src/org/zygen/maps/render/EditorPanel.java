package org.zygen.maps.render;

import org.zygen.maps.Cache;
import org.zygen.maps.editor.Layer;
import org.zygen.maps.editor.LayerEditor;
import org.zygen.maps.editor.SpriteSheet;
import org.zygen.maps.editor.SpriteSheetEditor;

import javax.swing.*;
import java.awt.*;

public class EditorPanel
extends JPanel {

    final SpriteSheet spriteSheet = new SpriteSheet("tilesets/Grass.png");
    final SpriteSheet spriteSheet2 = new SpriteSheet("tilesets/Grass_Things.png");
    final SpriteSheet spriteSheet3 = new SpriteSheet("tilesets/Hills.png");
    final SpriteSheet spriteSheet4 = new SpriteSheet("tilesets/Water.png");
    final SpriteSheet spriteSheet5 = new SpriteSheet("tilesets/Fences.png");
    final SpriteSheet spriteSheet6 = new SpriteSheet("tilesets/Void.png");

    public EditorPanel(boolean repeatable) {
        if (!repeatable) return;

        final Timer repaintTask = new Timer(5, t -> repaint());
        repaintTask.start();

    }

    @Override
    public void paint(Graphics g) {

        for (Layer layer : LayerEditor.loaded_layers) {
            for (int column = 0; column <= 16; column++) {
                for (int line = 0; line <= 16; line++) {

                    int tile_index = Math.min(255, (line * 16) + column);

                    final Image tile = SpriteSheetEditor.loaded_tiles.get(layer.getMAP_ARRAY()[tile_index]);
                    g.drawImage(tile, (column * Cache.TILE_SIZE), (line * Cache.TILE_SIZE), null);
                }
            }
        }

    }
}
