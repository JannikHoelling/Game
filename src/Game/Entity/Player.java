/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Entity;

import Game.*;
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
        g.fillOval( Game.getWidth()/2 - 5, Game.getHeight()/2 - 5, 10, 10);
    }

    @Override
    public void update(double delta) {

        if (Input.keys[KeyEvent.VK_A] && !Game.obstacle.getBounds().contains(x - delta * speed, y)) {
            x -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_D] && !Game.obstacle.getBounds().contains(x + delta * speed, y)) {
            x += delta * speed;
        }
        if (Input.keys[KeyEvent.VK_W] && !Game.obstacle.getBounds().contains(x, y - delta * speed)) {
            y -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_S] && !Game.obstacle.getBounds().contains(x, y + delta * speed)) {
            y += delta * speed;
        }
        
        if (Input.mouseClicked) {
            
            SpawnTools.spawnProjectile(x, y, Input.mouseX, Input.mouseY, 500f);
        }
        if (Input.keys[KeyEvent.VK_NUMPAD0]) {
            
            SpawnTools.spawnExplosion(Input.mouseX, Input.mouseY, 250f);
        }
        
        Camera.x = (int) x;
        Camera.y = (int) y;
        
        /*if(y < 500) {
            y += 2.5f;
        }*/
    }
}
