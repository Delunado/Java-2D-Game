/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D.level.Tiles;

import com.Delunado.Game2D.graphics.Screen;
import com.Delunado.Game2D.graphics.Sprite;

/**
 *
 * @author Delunado
 */
public class Tile {
    
    public int x, y;
    public Sprite sprite;
    
    public static Tile grass = new GroundTile(Sprite.ground);
    
    public Tile(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void Render(int x, int y, Screen screen){
    }
    
    public boolean Solid(){
        //By default its not solid
        return false;
    }
    
}
