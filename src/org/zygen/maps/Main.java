package org.zygen.maps;

import org.zygen.maps.editor.Layer;
import org.zygen.maps.menu.PixelEditor;
import org.zygen.maps.menu.PixelLayer;
import org.zygen.maps.menu.PixelSelector;

public class Main {

    public static void main(String[] args) {

        new PixelEditor();
        new PixelSelector();

        new Layer("BASE_LAYER");
        new PixelLayer();

        Cache.init();

    }
}
