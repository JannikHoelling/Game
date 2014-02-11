/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import Game.Entity.Projectile;
import java.util.Random;

/**
 *
 * @author Jannik
 */
public class SpawnTools {
    
    /**
     *
     * @param xFrom x Spawnposition of the projectile
     * @param yFrom y Spawnposition of the projectile
     * @param xTo x Position of the target
     * @param yTo y Position of the target
     * @param force speed with that the projectile is spawned in pixel/sec
     */
    public static void spawnProjectile(float xFrom, float yFrom, float xTo, float yTo, float force) {
        // Direction
        float dX = xTo - xFrom;
        float dY = yTo - yFrom;

        // Normalize Vector
        double length = Math.sqrt(dX * dX + dY * dY);

        dX /= length;
        dY /= length;

        Game.entities.add(new Projectile(xFrom, yFrom, dX * force, dY * force));
    }
    
    /**
     *
     * @param xFrom x Spawnposition of the explosion
     * @param yFrom x Spawnposition of the explosion
     * @param force speed with that the projectiles are spawned in pixel/sec
     */
    public static void spawnExplosion(float xFrom, float yFrom, float force) {
        
        Random rand = new Random();
        
        int shots = 180;
        // Convert the degrees to Radians
        float degree = 360f / shots;
        float radian = (float) ((Math.PI/180.0) * degree);
        
        // Get the points on a circle
        for (int i = 0; i < shots; i++) {
            
            float randPosition = (rand.nextFloat() - 0.5f) * 0.25f;
            float randSpeed = (rand.nextFloat() - 0.5f) * force/2;
            
            float x = (float) Math.cos(i * radian + randPosition) + xFrom;
            float y = (float) Math.sin(i * radian + randPosition) + yFrom;
            
            spawnProjectile(xFrom, yFrom, x, y, force + randSpeed);
        }
    }
}
