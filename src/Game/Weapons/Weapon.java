package Game.Weapons;

import Game.SpawnTools;


public class Weapon {
    
    public float fireRate = 30;
    
    private long nextShoot = 0;
    
    public void shoot(float x, float y, float dX, float dY) {
        if(nextShoot < System.currentTimeMillis()) {
            SpawnTools.spawnProjectile(x, y, dX, dY, 500f, true);
            nextShoot = System.currentTimeMillis() + (long)(1000/fireRate);
        }
    }
}
