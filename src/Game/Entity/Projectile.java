package Game.Entity;

import Game.Map.Terrain;
import Game.*;
import java.awt.Graphics2D;


public class Projectile extends Entity {

    public float dX, dY;

    public double creationTime;
    
    public Projectile(float x, float y, float dX, float dY) {
        super(x, y);

        this.dX = dX;
        this.dY = dY;

        creationTime = Game.time;
    }

    @Override
    public void render(Graphics2D g) {
        g.fillRect((int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), 4, 4);
    }

    @Override
    public void update(double deltaD) {
        
        float delta = (float) deltaD;
        
        if(Game.time - creationTime > 10 || !Terrain.getBlock(x + dX * delta, y + dY * delta)) {
            delete = true;
        }
        else {
            x += dX * delta;
            y += dY * delta;
        }
    }
}
