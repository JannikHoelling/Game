package Game.Entity;

import Game.Map.Terrain;
import Game.*;
import java.awt.Graphics2D;


public class Projectile extends Entity {

    private final float dX, dY;
    private final double creationTime;
     
     private boolean explode = false;
    
    public Projectile(float x, float y, float dX, float dY, boolean explode) {
        super(x, y);

        this.dX = dX;
        this.dY = dY;
        this.explode = explode;

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
    
    
    @Override
    public void onDestroy() {
        if(explode) {
            SpawnTools.spawnExplosion(x, y, 250f);
        }
    } 
}
