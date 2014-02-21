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
public class Teleporter extends RigidBody {

    Teleporter teleporter;
    
    public Teleporter(float x, float y, Teleporter teleporter) {
        super(x, y);
        
        this.teleporter = teleporter;
    }
    
    
    public void setTarget(Teleporter teleporter) {
        this.teleporter = teleporter;
    }
    
    @Override
    public void onCollision() {
        Game.player.setX(teleporter.x + 64);
        Game.player.setY(teleporter.y);
    }
    
}
