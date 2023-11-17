package Main;

import javax.swing.*;
import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
              // creates window
              JFrame window = new JFrame();
              window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              window.setResizable(false);
              window.setTitle("A Beginning, Alas");
              // adds gamePanel functionality to the window, by putting the panel in it
              GamePanel gamePanel = new GamePanel();
              window.add(gamePanel);
        window.pack();

              window.setLocationRelativeTo(null);
              window.setVisible(true);
              // this starts the "game loop", which is the set of actions that the game
              // executes every frame
              gamePanel.startGameThread();
        }
    }