package Entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage upIdle, downIdle, rightIdle, leftIdle, up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage rolling_f_1, rolling_f_2, rolling_f_3, rolling_f_4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
