package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GamePanel  extends JPanel implements Runnable {
    // SCREEN SETTINGS: this sets the size of the game screen, which is cool I guess
    final int originalTileSize = 16; // 16x16 tile


    final public int maxScreenCol = 16;
    final public int maxScreenRow = 9;
    double scale = 3;
    public int res = 10;
    int tileSize =(int) (originalTileSize * scale); // size of 1 tile
    int screenWidth = tileSize * maxScreenCol; // changes depending on the selected resolution
    int screenHeight = tileSize * maxScreenRow; // changes depending on the selected resolution

    // World settings
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;
    int worldWidth = tileSize * maxWorldCol;
    int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;
    // a thread is used to create the illusion of passage of time, X amount of static images
    // will be rendered on the screen, giving you the impression of movement
    Thread gameThread;
    TileManager tileMan = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);


    public GamePanel() {
        setResolution(res);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        // remember to implement mouse functionality;
        this.addMouseListener(null);
        // up there buddy
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setResolution(int res){
        try {
            String[] values={"","",""};
            int lineNum = 0;
        InputStream is = getClass().getResourceAsStream("/video/resolution.txt");
        //buffered reader allows us to read its contents
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(lineNum < 13) {
            String line = br.readLine();
            lineNum++;
            if(lineNum == res){

                values = line.split(" ");
            }
        }
        screenHeight = Integer.parseInt(values[1]);
        screenWidth = Integer.parseInt(values[0]);
        scale = Double.parseDouble(values[2]);
        tileSize = (int) (originalTileSize * scale);
        worldWidth = tileSize * maxWorldCol;
        worldHeight = tileSize * maxWorldRow;

        System.out.println(screenHeight + " " + screenWidth + " " +scale);
        br.close();
        } catch(IOException e){
            System.err.println("Not found");
        }

    }
    //Sleep Method, but seems inefficient for me, movement is choppy
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // draw screen every amount of this time
        double nextDrawTime = System.nanoTime() + drawInterval;
        //variables to display fps
        long currentFPS = 0;
        long lastTime = 0;
        long timer = 0;
         while(gameThread != null){
             long currentTime = System.nanoTime();

              // System.out.println("Running"); test code, to see if it actually runs
             // Update information on the screen at every frame, like character position
             update();
             timer += (System.nanoTime() - lastTime);
             //draw screen with updated info repaint is just java mumbo jumbo, it calls the draw function
             repaint();
             currentFPS++;
             if(timer >= 1000000000) {
                 System.out.println("FPS: " + currentFPS + " X: " + (player.screenX) + " Y: " + (player.screenY));
                 currentFPS = 0;
                 timer = 0;
                 lastTime = 0;
             }
             lastTime = currentTime;
             try {
             double remainingTime = nextDrawTime - System.nanoTime();
             remainingTime /= 1000000;

             if(remainingTime < 0) remainingTime = 0;

             nextDrawTime += drawInterval;


                 Thread.sleep((long) remainingTime);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
    }
 // delta/accumulation method, still not working as intended, might be hardware issue
 /*   @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount ++;

            }
            if(timer >= 1000000000) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }


    } */

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        // this is just a java convention (remember it)
        super.paintComponent(g);
        // paint test tile
        // convert graphics, to graphics2d, which has more functions
        Graphics2D g2 = (Graphics2D) g;
        tileMan.draw(g2);
        player.draw(g2);
        // program works without, but it's good practice to save memory
        g2.dispose();


    }
    public int getTileSize() {
        return this.tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}
