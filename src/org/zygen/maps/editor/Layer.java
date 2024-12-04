package org.zygen.maps.editor;

import java.util.LinkedList;
import java.util.List;

public class Layer {

    private final String name;
    private final int id;

    private int[] MAP_ARRAY = new int[256];

    public Layer(String name) {
        this.name = name;
        this.id = LayerEditor.loaded_layers.size()-1;

        for (int i = 0; i < 256; i++) {
            MAP_ARRAY[i] = 222;
        }

        LayerEditor.addLayer(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int[] getMAP_ARRAY() {
        return MAP_ARRAY;
    }
}
