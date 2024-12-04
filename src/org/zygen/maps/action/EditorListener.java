package org.zygen.maps.action;

import org.zygen.maps.Cache;
import org.zygen.maps.editor.LayerEditor;
import org.zygen.maps.editor.SpriteSheetEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorListener {

    public static class MouseEvent
    implements MouseListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            int baseX = e.getX();
            int baseY = e.getY();

            // Corrigindo: col é baseado no eixo X e row é baseado no eixo Y
            int col = baseX / 16;
            int row = baseY / 16;

            // Calcula o índice correto no array unidimensional
            int index = row * (256 / 16) + col;

            LayerEditor.getSelectedLayer().getMAP_ARRAY()[index] = Cache.selectedTile;

        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {

        }
    }

    public static class KeyBoardEvent
    implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
