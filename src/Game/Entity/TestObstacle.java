/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Entity;

import Game.Camera;
import Game.Game;
import Game.Renderer;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Jannik
 */
public class TestObstacle extends Entity {
    
    public int sizeX;
    public int sizeY;

    public TestObstacle(float x, float y, int sizeX, int sizeY) {
        super(x, y);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawRect((int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), sizeX, sizeY);
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, sizeX, sizeY);
    }
}
