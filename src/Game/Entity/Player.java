package Game.Entity;

import Game.*;
import Game.Map.Terrain;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import static Game.World.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Game.Enums.Players;
import Game.Map.Block;
import java.awt.Dimension;

public class Player extends RigidBody {

    private final float speed = 10 ;
    private final float jumpForce = 750;
    private float dX;
    private float dY;
    private static BufferedImage image;

    public Player(float x, float y) {
        super(x, y);
        image = Players.BLUE.getImage();
    }

    @Override
    public void render(Graphics2D g) {       
        g.drawImage(image, HALF_FRAME_X - HALF_TILE, HALF_FRAME_Y - HALF_TILE, TILE_SIZE, TILE_SIZE, null);
        g.drawRect(HALF_FRAME_X - HALF_TILE, HALF_FRAME_Y - HALF_TILE, TILE_SIZE-1, TILE_SIZE-1);
        
    }

    @Override
    public void update(float delta) {
        if(Input.keys[KeyEvent.VK_A]){
            dX -= speed;
        }

        if(Input.keys[KeyEvent.VK_D]){
            dX += speed;
        }
        
        // Jumping
        if(Input.keys[KeyEvent.VK_SPACE]) {
            if (!Terrain.getBlock(x + HALF_TILE-1, y + HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE)) {
                dY = - jumpForce;
            }
        }
        
        // Adding fall speed
        dY += delta * 981f;
        
        // Slowing down when touching the ground and not pressing A, D
        if(!Input.keys[KeyEvent.VK_A] && !Input.keys[KeyEvent.VK_D] && (!Terrain.getBlock(x + HALF_TILE-1, y + HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE))) {
            dX *= 0.8;
        }
        
        //Horizontal movement
        x += delta * dX;
        
        if(dX < 0) { // Check left side
            if (!Terrain.getBlock(x - HALF_TILE, y - HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE-1)) {
                x = Terrain.positionBlock(x) + HALF_TILE;
                dX = 0;
            }
        }
        if(dX > 0){ // Check right side
            if (!Terrain.getBlock(x + HALF_TILE, y - HALF_TILE) || !Terrain.getBlock(x + HALF_TILE, y + HALF_TILE-1)) {
                x = Terrain.positionBlock(x) - HALF_TILE;
                dX = 0;
            }
        }
        
        // add vertical movement
        y += delta * dY;
        
        if(dY < 0) { // Check floor
            if (!Terrain.getBlock(x + HALF_TILE-1, y - HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y - HALF_TILE)) {
                y = Terrain.positionBlock(y) + HALF_TILE;
                dY = 0;
            }
        }
        if(dY > 0) { // Check ceiling
            if (!Terrain.getBlock(x + HALF_TILE-1, y + HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE)) {
                y = Terrain.positionBlock(y) - HALF_TILE;
                dY = 0;
            }
        }

        
        if (Input.mouseClicked) {
            SpawnTools.spawnProjectile(x, y, Input.getMouseX(), Input.getMouseY(), 500f);
        }
        if (Input.keys[KeyEvent.VK_ALT]) {

            SpawnTools.spawnExplosion(Input.getMouseX(), Input.getMouseY(), 250f);
        }

        Camera.x = (int) x;
        Camera.y = (int) y;
    }    
    
    
    public float getDX() {
        return dX;
    }
    
    public float getDY() {
        return dY;
    }
    
    public void setDX(float dX) {
        this.dX = dX;
    }
    
    public void setDY(float dY) {
        this.dY = dY;
    }
    
    public static void setImage(BufferedImage image) {
        Player.image = image;
    }
 
}
