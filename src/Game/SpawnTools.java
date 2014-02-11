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
    
    public static void spawnExplosion(float xFrom, float yFrom, float force) {
        
        Random rand = new Random();
        
        int shots = 128;
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
