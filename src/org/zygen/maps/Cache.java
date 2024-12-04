package org.zygen.maps;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cache {

    public static int WIDTH = 256;
    public static int HEIGHT = 256;

    public static int TILE_SIZE = 16;

    public static int selectedTile = 0;
    public static int[] MAP_ARRAY = new int[256];

    public static void init() {
        for (int i = 0; i < 256; i++) {
            MAP_ARRAY[i] = 200;
        }
    }
}
