/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Delunado.Game2D;

import com.Delunado.Game2D.graphics.Screen;
import com.Delunado.Game2D.input.Keyboard;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Delunado
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    Random generator = new Random();
    //Leaving the buffers number to 3 is the best option. 2 is low, and more than 3 is not very useful.
    private static final int BS_BUFFERS_NUMBER = 3;
    private static final double FPS = 60.0;

    String title = "2D Game - @Delunad0";

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    
    private int xOffset, yOffset;
    
    //This variable is a thread where the game will be running
    private Thread gameThread;
    //It's like a window.
    private JFrame frame;
    //It indicates if the game is running or not
    private boolean running = false;

    private Screen screen;
    private Keyboard key;

    //We create a buffer where we store an image itself
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //Here, we convert the image to an array of the pixels of that image.
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        //We create a new Dimension object with our desired resolution
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        
        key = new Keyboard();
        addKeyListener(key);

    }

    /**
     * Starts the game thread.
     */
    public synchronized void Start() {
        running = true;
        gameThread = new Thread(this, "Display");
        gameThread.start();
    }

    /**
     * Stop the game thread.
     */
    public synchronized void Stop() {
        running = false;
        try {
            //As the running variable is false, we wait for the finishing of the thread.
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure the initial parameters of the game's window
     */
    public void ConfigureFrame() {
        //Important to avoid problems with resolutions, set false the resizable of the window.
        frame.setResizable(false);
        frame.setTitle(title);
        //It adds our game instance to the frame, meaning that the frame will fill the canvas with that.
        frame.add(this);
        //This makes the frame get the size that we have decided on setPreferredSize():
        frame.pack();
        //This is important because it that way, when you close the window it will finish the thread, not JUST close the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //This centers on the screen our window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //When the thread is started, our game loops in this method.
    public void run() {
        long lastTime = System.nanoTime();
        long FPSTimer = System.currentTimeMillis();
        final double ns = 1000000000.0 / FPS;

        double delta = 0; //Time between frames
        int frames = 0; //Number of frames
        int updates = 0; //Number of calls to Update
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            //We call Update() FPS times per second.
            while (delta >= 1) {
                Update();
                updates++;
                delta--;
            }

            Render();
            frames++;

            //Each second, this shows the actual FPS and UPS
            if (System.currentTimeMillis() - FPSTimer > 1000) {
                FPSTimer += 1000;
                frame.setTitle(title + " - " + updates + " UPS" + ", " + frames + " FPS");
                updates = frames = 0;
            }
        }
    }

    /**
     * This method will handle all the logic: inputs, etc. It will be called 60
     * times per second.
     */
    public void Update() {
        key.Update();
        ControlMap();
    }
    
    private void ControlMap(){ 
        if (key.up) yOffset--;
        if (key.down) yOffset++;
        if (key.left) xOffset--;
        if (key.right) xOffset++;
    }

    /**
     * This method will handle the graphical/rendering part. It will be called
     * all the times as posible per second.
     */
    public void Render() {
        //Here we are getting the BufferStrategy from the Canvas
        BufferStrategy bs = getBufferStrategy();
        //If the bs is null, then we create the Buffer Strategy.
        if (bs == null) {
            createBufferStrategy(BS_BUFFERS_NUMBER);
            return;
        }

        screen.Clear();
        screen.Render(xOffset, yOffset);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        //It "links" the data in the buffer with graphics, getting them
        Graphics g = bs.getDrawGraphics();

        //g.setColor(Color.getHSBColor(generator.nextFloat(), generator.nextFloat(), generator.nextFloat()));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        //It disposes all the graphics. We need to render all before calling it
        g.dispose();
        //It will show the buffer that has been calculated
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.ConfigureFrame();
        game.Start();
    }

}
