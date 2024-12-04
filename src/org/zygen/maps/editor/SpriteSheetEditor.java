package org.zygen.maps.editor;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SpriteSheetEditor {

    public static List<SpriteSheet> loaded_spriteSheets = new LinkedList<>();
    public static List<BufferedImage> loaded_tiles = new LinkedList<>();
    public static int spriteSheet = 0;

    public static int getPage() {
        return spriteSheet + 1;
    }

    public static void nextPage() {
        if (loaded_spriteSheets.get(getPage()) != null) {
            spriteSheet++;
        }
    }
    public static void backPage() {
        if (loaded_spriteSheets.get(spriteSheet-1) != null) {
            spriteSheet--;
        }
    }

    public static SpriteSheet getSprite() {
        return loaded_spriteSheets.get(spriteSheet);
    }

    public static SpriteSheet getNextSprite() {

        if (loaded_spriteSheets.size() == 1) {
            return loaded_spriteSheets.get(0);
        }

        if (loaded_spriteSheets.get(getPage()) == null) {
            return getPreviousSprite();
        }

        return loaded_spriteSheets.get(getPage());
    }

    public static SpriteSheet getPreviousSprite() {

        if (loaded_spriteSheets.size() == 1) {
            return loaded_spriteSheets.get(0);
        }

        if (loaded_spriteSheets.get(spriteSheet-1) == null) {
            return getNextSprite();
        }

        return loaded_spriteSheets.get(spriteSheet-1);
    }

    public static void addSpriteSheet(SpriteSheet spriteSheet) {
        loaded_spriteSheets.add(spriteSheet);
        loaded_tiles.addAll(Arrays.asList(spriteSheet.getSprites()));
    }
}
