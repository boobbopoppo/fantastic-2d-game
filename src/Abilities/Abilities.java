package Abilities;

import Entity.Player;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class Abilities {
    long cdInMillis;
    long lastUse;
    String type;
    public abstract void effect(KeyHandler keyH, Player player, GamePanel gp);
}
