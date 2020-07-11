/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Delunado
 */
public class SpriteSheet {
    
    private String path;
    private final int SIZE; //Size of the spritesheet
    public int[] pixels;
    
    //IMPORTANT USE BACKSLASH / AT THE BEGGINING OF THE PATH
    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
    
    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
        
        Load();
    }
    
    public int getSize(){
        return SIZE;
    }
    
    private void Load(){
        try {
            //Cargamos la imagen que este en el path
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            //Esto hace que la imagen se "traduzca" a pixeles y la guardames en el array pixels.
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
