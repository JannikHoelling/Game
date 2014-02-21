package Game.Entity;

import Game.Game;
import java.awt.Rectangle;
import static Game.World.*;


public class RigidBody extends Entity {
    
    public RigidBody(float x, float y) {
        super(x, y);
        
        Game.rigidBodies.add(this);
    }
    
    public void onCollision() {
        
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, TILE_SIZE, TILE_SIZE);
    }
}
