/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Entity;

import Game.Game;

/**
 *
 * @author Jannik
 */
public class RigidBody extends Entity {

    
    
    public RigidBody(float x, float y) {
        super(x, y);
        
        Game.collider.add(this);
    }
    
    
}
