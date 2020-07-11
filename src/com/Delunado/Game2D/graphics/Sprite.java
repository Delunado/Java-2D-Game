/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D.graphics;

/**
 *
 * @author Delunado
 */
public class Sprite {
    
    private final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite ground = new Sprite(16, 0, 0, SpriteSheet.tiles);
    
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        this.SIZE = size;
        this.x = x*size;
        this.y = y*size;
        pixels = new int[SIZE*SIZE];
        this.sheet = sheet;
        
        Load();
    }
    
    public int GetSize(){
        return SIZE;
    }
    
    private void Load(){
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                //We save the correspondient pixels of that sprite in that object pixels array;
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }
    
}
