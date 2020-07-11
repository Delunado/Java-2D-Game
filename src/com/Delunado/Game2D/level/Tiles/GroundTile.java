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
public class GroundTile extends Tile{
    
    public GroundTile(Sprite sprite) {
        super(sprite);
    }
    
    @Override
    public void Render(int x, int y, Screen screen){
        screen.RenderTile(x, y, this);
    }
    
}
