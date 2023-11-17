package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean shiftPressed, upPressed, downPressed, leftPressed, rightPressed;
    public boolean isKeyPressed;
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code ==KeyEvent.VK_SHIFT){
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_W) {
             upPressed = true;
            isKeyPressed = true;
        }
        if(code == KeyEvent.VK_A) {
             leftPressed = true;
            isKeyPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
            isKeyPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            isKeyPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
            isKeyPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
            isKeyPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
            isKeyPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
            isKeyPressed = false;
        }

    }
}
