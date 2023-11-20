package Abilities;

import Entity.Player;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.image.BufferedImage;

public class Dash extends Abilities{
    public int eyeFrame = 0;
    boolean invincibility = true;
    public Dash() {
        this.cdInMillis = 5000;
        this.type = "movement";
        this.lastUse = 0;
    }
    @Override
    public void effect(KeyHandler keyH, Player player, GamePanel gp) {
        if(System.currentTimeMillis() - lastUse >= cdInMillis){
            if(player.direction =="left")  {
                player.worldX -= 0.125* gp.getTileSize();
                eyeFrame++;
            }
            if(player.direction =="right"){
                player.worldX += 0.125* gp.getTileSize();
                eyeFrame++;
            }
            if(player.direction =="up") {
                player.worldY -= 0.125* gp.getTileSize();
                eyeFrame++;
            }
            if(player.direction =="down") {
                player.worldY += 0.125* gp.getTileSize();
                eyeFrame++;
            }
            invincibility = true;
            if(eyeFrame == 16){
                eyeFrame = 0;
                keyH.shiftPressed = false;
                invincibility = false;
                lastUse = System.currentTimeMillis();

            }

        }
    }
    public void draw(BufferedImage image, KeyHandler keyH, Player player) {
                switch(player.direction){
                    case("down"):
                        image = player.down1;
                        break;
                    case("up"):
                        image = player.up1;
                        break;
                    case ("left"):
                        image = player.left1;
                    case ("right"):
                        image = player.right1;
                }
        }
    }
