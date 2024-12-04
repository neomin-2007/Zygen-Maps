package org.zygen.maps.editor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private BufferedImage[] sprites;

    public SpriteSheet(String sprite_pack) {
        try {
            spriteSheet = ImageIO.read(new File(sprite_pack));

            // Número de sprites por linha e coluna
            int rows = spriteSheet.getHeight() / 16;
            int cols = spriteSheet.getWidth() / 16;

            // Array para armazenar os sprites
            sprites = new BufferedImage[rows * cols];

            // Extrai cada sprite 16x16
            int index = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    sprites[index] = spriteSheet.getSubimage(x * 16, y * 16, 16, 16);
                    index++;
                }
            }
        } catch(Exception ignored) {
            System.out.println("Ocorreu um erro ao ler o sprite!");
        }

        SpriteSheetEditor.addSpriteSheet(this);
    }

    public int length() {
        return sprites.length;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }
}
