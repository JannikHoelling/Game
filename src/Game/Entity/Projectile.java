package Game.Entity;

import Game.Map.Terrain;
import Game.*;
import java.awt.Graphics2D;


public class Projectile extends Entity {

    private float dX, dY;
    private double creationTime;
    
    public Projectile(float x, float y, float dX, float dY) {
        super(x, y);

        this.dX = dX;
        this.dY = dY;

        creationTime = Game.time;
    }

    @Override
    public void render(Graphics2D g) {
        g.fillOval((int) x + Renderer.offsetX() - 2, (int) y + Renderer.offsetY() - 2, 4, 4);
    }

    @Override
    public void update(float delta) {     
        if(Game.time - creationTime > 10 || !Terrain.getBlock(x + dX * delta, y + dY * delta)) {
            destroy();
        }
        else {
            x += dX * delta;
            y += dY * delta;
        }
    }
}
