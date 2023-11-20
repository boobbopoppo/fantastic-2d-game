package Entity;

import Abilities.Abilities;
import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Abilities.Dash;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Abilities abilities;

    public int screenX;
    public int screenY;
    //variables for the roll ability
    int eyeFrame = 0;
    long rollLastCD = 0;
    long cdInMillisRoll = 5000;
    boolean invicibility = false;

    // checks for collision
    Dash dodge = new Dash();

    public Player(GamePanel gp, KeyHandler keyH, Abilities abilities){
        this.gp = gp;
        this.keyH = keyH;
        this.abilities = abilities;
        gp.setResolution(gp.res);

        screenX = gp.getScreenWidth() / 2 -(gp.getTileSize()/ 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize()/ 2);

        solidArea = new Rectangle(gp.getTileSize()/6, gp.getTileSize()/3, (int) (gp.getTileSize()/1.5), (int) (gp.getTileSize()/1.5));



        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.getTileSize() * 4;
        worldY = gp.getTileSize() * 4;
        speed = (gp.getScreenWidth()/200);
        direction = "down";
    }

    public void update() {
        if (keyH.isKeyPressed == false) {
            spriteNum = 3;
        }
        // camera becomes fixed, if you approach the world's edge
        // fix in update, you can see the black border, and it's very bad
        if (worldX - gp.getScreenWidth() / 2 <= 0) {
            screenX = worldX;
        } else if (worldX - gp.getScreenWidth() / 2 > 0) {
            screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        }
        if ((worldX + (gp.getScreenWidth() / 2)- gp.getWorldWidth() - (gp.getTileSize()/ 2)>= 0)) {
            screenX += (worldX + (gp.getScreenWidth() / 2) - gp.getWorldWidth());
        }
        if (worldY - gp.getScreenHeight() / 2 <= 0) {
            screenY = worldY;
        } else if (worldY - (gp.getScreenHeight() / 2) > 0) {
            screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
        }
        if(worldY + (gp.getScreenHeight() / 2)- gp.getWorldHeight() - (gp.getTileSize()/ 2) >= -(gp.getScreenHeight() +gp.getTileSize())) {
            screenY += worldY + (gp.getScreenHeight() / 2) - gp.getWorldHeight() +gp.getScreenHeight();
        }
        // manage the shift button
        if(keyH.shiftPressed) dodge.effect(keyH, this, gp);

        // sets movement of the player and decides partially which sprite is gonna be used
        if (keyH.upPressed){
            direction = "up";
            spriteCounter++;
        } else if (keyH.downPressed) {
            direction = "down";
            spriteCounter++;
        } else if (keyH.rightPressed) {
            direction = "right";
            spriteCounter++;
        } else if (keyH.leftPressed) {
            direction = "left";
            spriteCounter++;
        }
        // CHECK TILE COLLISION

        collisionOn = false;
        gp.cC.CheckTile(this);

        // if collision is false player can move, otherwise it can't
        if(collisionOn == false) {

            switch(direction){
                case "up":
                    if(keyH.upPressed) worldY -= speed;
                    break;
                case "down":
                    if(keyH.downPressed) worldY += speed;
                    break;
                case "left":
                    if(keyH.leftPressed) worldX -= speed;
                    break;
                case "right":
                    if(keyH.rightPressed) worldX += speed;
                    break;
            }
        }

        if(spriteNum == 3 && spriteCounter == 1){
            spriteNum = 1;
        }
        if(spriteCounter > 15) {
            if(spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        // draws sprite during walking animation
        switch(direction) {
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                if(spriteNum == 3) image = upIdle;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = downIdle;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = leftIdle;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = rightIdle;
                break;

        }
        // draws sprite during the roll "animation"
        if(keyH.shiftPressed) dodge.draw(image, keyH, this);
        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walking_back_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walking_back_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walking_front_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walking_front_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walking_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walking_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_right.png"));
            downIdle = ImageIO.read(getClass().getResourceAsStream("/player/idle_front.png"));
            upIdle = ImageIO.read(getClass().getResourceAsStream("/player/idle_back.png"));
            leftIdle = ImageIO.read(getClass().getResourceAsStream("/player/idle_left.png"));
            rightIdle = ImageIO.read(getClass().getResourceAsStream("/player/idle_right.png"));
            rolling_f_1 = ImageIO.read(getClass().getResourceAsStream("/player/rolling_forward_1.png"));
            rolling_f_2 =ImageIO.read(getClass().getResourceAsStream("/player/rolling_forward_2.png"));
            rolling_f_3 =ImageIO.read(getClass().getResourceAsStream("/player/rolling_forward_3.png"));
            rolling_f_4 =ImageIO.read(getClass().getResourceAsStream("/player/idle_front.png"));



        } catch(IOException e){

        }
    }
}
