/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game.Weapons;

import Game.SpawnTools;

/**
 *
 * @author Jannik
 */
public class Weapon {
    
    public float fireRate = 15;
    
    private long nextShoot = 0;
    
    public void shoot(float x, float y, float dX, float dY) {
        if(nextShoot < System.currentTimeMillis()) {
            SpawnTools.spawnProjectile(x, y, dX, dY, 500f, true);
            nextShoot = System.currentTimeMillis() + (long)(1000/fireRate);
        }
    }
}
