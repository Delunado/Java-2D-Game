/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D.level;

import com.Delunado.Game2D.graphics.Screen;

/**
 *
 * @author Delunado
 */
public class Level {
    
    protected int width, height;
    protected int[] tiles;
    
    public Level(int width, int height){
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        GenerateLevel();
    }
    
    public Level(String path){
        LoadLevel(path);
    }
    
    protected void GenerateLevel(){
        
    }
    
    private void LoadLevel(String path){
        
    }
    
    private void Time(){
        
    }
    
    public void Update(){
        
    }
    
    public void Render(int xScroll, int yScroll, Screen screen){
        //Here we are getting the corners of the screen.
        int x0 = xScroll >> 4; //4 because the sprites are 16 size
        int x1 = (xScroll + screen.width) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height) >> 4;
    }
    
}
