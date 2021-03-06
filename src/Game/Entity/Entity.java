package Game.Entity;

import java.awt.Graphics2D;
import Game.Game;
import Game.Renderer;
import static Game.World.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class Entity {

    protected float x;
    protected float y;
    
    private boolean alive = true;
    protected BufferedImage image;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        
        Game.entities.add(this);
    }

    public void update(float delta) {
        
    }

    public void render(Graphics2D g) {
        g.fillOval((int) x + Renderer.offsetX() - HALF_TILE, (int) y + Renderer.offsetY() - HALF_TILE, 4, 4);
        g.drawRect((int) x + Renderer.offsetX() - TILE_SIZE, (int) y + Renderer.offsetY() - TILE_SIZE, TILE_SIZE-1, TILE_SIZE-1);
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public boolean isAlive() {
        return alive;
    }

    public void destroy() {
        alive = false;
    }
    
    public void onDestroy() {
        
    }
        
    public void setPosition(Dimension dim) {
        setX(dim.width);
        setY(dim.height);
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
