/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D.graphics;

import com.Delunado.Game2D.level.Tiles.Tile;
import java.util.Random;

/**
 *
 * @author Delunado
 */
public class Screen {

    private Random random = new Random();

    public int width, height;
    public int[] pixels;

    private int mapSize = 64, mapSizeMask = mapSize - 1;
    private int tilesSize = 16;
    public int[] tiles = new int[mapSize * mapSize];

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];

        for (int i = 0; i < mapSize * mapSize; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void Clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void Render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yp = y + yOffset;
            if (yp < 0 || yp >= height) {
                continue;
            }
            for (int x = 0; x < width; x++) {
                int xp = x + xOffset;
                if (xp < 0 || xp >= width) {
                    continue;
                }
                pixels[xp + yp * width] = Sprite.ground.pixels[(x & Sprite.ground.GetSize() - 1) + (y & Sprite.ground.GetSize() - 1) * Sprite.ground.GetSize()];
            }
        }
    }

    public void RenderTile(int xp, int yp, Tile tile) {
        for (int y = 0; y < tile.sprite.GetSize(); y++) {
            //The actual y position is the absolute y position (yp) + the y based on the sprite Size
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.GetSize(); x++) {
                int xa = x + xp;
                //Very important line. 
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) break;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.GetSize()];
            }
        }
    }
    
    
}
