/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Entity;

import Game.*;
import Game.Map.Terrain;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lokaler Benutzer
 */
public class Player extends Entity {
    
    public float speed = 250f;
    
    public Player(float x, float y) {
        super(x, y);
    }
    
    @Override
    public void render(Graphics2D g) {
        g.fillOval( World.PANEL_X/2 - 5, World.PANEL_Y/2 - 5, 10, 10);
    }

    @Override
    public void update(double delta) {
        
        if (Input.keys[KeyEvent.VK_A] && Terrain.getBlock((float) (x - delta * speed), y)) {
            x -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_D] && Terrain.getBlock((float) (x + delta * speed), y)) {
            x += delta * speed;
        }
        if (Input.keys[KeyEvent.VK_W] && Terrain.getBlock(x, (float) (y - delta * speed))) {
            y -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_S] && Terrain.getBlock(x, (float) (y + delta * speed))) {
            y += delta * speed;
        }
        
        /*if (Terrain.getBlock(x, (float) (y + delta * speed/2))) {
            y += delta * speed/2;
        }*/
        
//        if (Input.keys[KeyEvent.VK_A]) {
//            x -= delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_D]) {
//            x += delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_W]) {
//            y -= delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_S]) {
//            y += delta * speed;
//        }
        
        if (Input.mouseClicked) {
            
            SpawnTools.spawnProjectile(x, y, Input.getMouseX(), Input.getMouseY(), 500f);
        }
        if (Input.keys[KeyEvent.VK_ALT]) {
            
            SpawnTools.spawnExplosion(Input.getMouseX(), Input.getMouseY(), 250f);
        }
        
        Camera.x = (int) x;
        Camera.y = (int) y;
    }
}
