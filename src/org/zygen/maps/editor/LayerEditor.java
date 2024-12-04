package org.zygen.maps.editor;

import java.util.LinkedList;
import java.util.List;

public class LayerEditor {

    public static List<Layer> loaded_layers = new LinkedList<>();
    public static int selectedLayer;

    public static void nextLayer() {
        try {
            final Layer findedLayer = loaded_layers.get(selectedLayer+1);
            if (findedLayer != null) {
                selectedLayer++;
            }
        } catch(IndexOutOfBoundsException exception) {
            return;
        }
    }

    public static void backLayer() {
        if (selectedLayer > 0) {
            selectedLayer--;
        }
    }

    public static void addLayer(Layer layer) {

        boolean confirmation = true;
        for (Layer saved_layers : loaded_layers) {
            if (saved_layers.getName().equalsIgnoreCase(layer.getName())) {
                confirmation = false;
                break;
            }
        }

        if (confirmation) loaded_layers.add(layer);
    }

    public static void removeLayer(String layer_name) {
        for (Layer layer : loaded_layers) {
            if (layer.getName().equalsIgnoreCase(layer_name)) {

                if (layer.equals(getSelectedLayer())) {
                    selectedLayer--;
                }

                loaded_layers.add(layer);
                break;
            }
        }
    }

    public static void removeLayer(int index) {
        loaded_layers.remove(loaded_layers.get(index));
    }

    public static Layer getSelectedLayer() {
        return loaded_layers.get(selectedLayer);
    }
}
