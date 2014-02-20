package Game.Entity;

import Game.Game;


public class RigidBody extends Entity {

    public RigidBody(float x, float y) {
        super(x, y);
        
        Game.collider.add(this);
    }    
}
