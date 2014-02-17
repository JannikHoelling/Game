package Game.Entity;

import java.awt.Graphics2D;
import Game.Game;

public class Entity {

    protected float x;
    protected float y;

    private boolean alive = false;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        
        Game.entities.add(this);
    }

    public void update(float delta) {
        
    }

    public void render(Graphics2D g) {
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
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
        alive = true;
    }
}
