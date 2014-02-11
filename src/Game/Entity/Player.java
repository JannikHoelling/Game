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


public class Player extends Entity {
    
    public float speed = 250f;
    
    BufferedImage image;
    
    public Player(float x, float y) {
        super(x, y);
       
        try {
            image = ImageIO.read(new File("res/figur.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        //g.fillOval(PANEL_X/2 - 5, PANEL_Y/2 - 5, 32, 32);
        //g.fillRect(PANEL_X/2 - 5, PANEL_Y/2 - 5, TILE_SIZE, TILE_SIZE);
        g.drawImage(image, PANEL_X/2, PANEL_Y/2, TILE_SIZE, TILE_SIZE, null);
    }

    @Override
    public void update(double delta) {
        
        if (Input.keys[KeyEvent.VK_A] && Terrain.getBlock((float) (x - delta * speed), y) && Terrain.getBlock((float) (x - delta * speed), (float) (y+TILE_SIZE-TILE_SIZE/5))) {
            x -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_D] && Terrain.getBlock((float) (x+TILE_SIZE-TILE_SIZE/5 + delta * speed), y) && Terrain.getBlock((float) (x+TILE_SIZE-TILE_SIZE/5 + delta * speed), (float) (y+TILE_SIZE-TILE_SIZE/5))) {
            x += delta * speed;
        }
        if (Input.keys[KeyEvent.VK_W] && Terrain.getBlock(x, (float) (y - delta * speed)) && Terrain.getBlock((float) (x+TILE_SIZE-TILE_SIZE/5), (float) (y - delta * speed))) {
            y -= delta * speed;
        }
        if (Input.keys[KeyEvent.VK_S] && Terrain.getBlock(x, (float) (y+TILE_SIZE-TILE_SIZE/5 + delta * speed)) && Terrain.getBlock((float) (x+TILE_SIZE-TILE_SIZE/5), (float) (y+TILE_SIZE-TILE_SIZE/5 + delta * speed))) {
            y += delta * speed;
        }
        
        /*if (Terrain.getBlock(x, (float) (y + delta * speed/2))) {
            y += delta * speed/2;
        }*/
        
//        if (Input.keys[KeyEvent.VK_A]) {
//            x -= delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_D]) {
//            x += delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_W]) {
//            y -= delta * speed;
//        }
//        if (Input.keys[KeyEvent.VK_S]) {
//            y += delta * speed;
//        }
        
        if (Input.mouseClicked) {
            
            SpawnTools.spawnProjectile(x+TILE_SIZE/2-5, y+TILE_SIZE/2-5, Input.getMouseX(), Input.getMouseY(), 500f);
        }
        if (Input.keys[KeyEvent.VK_ALT]) {
            
            SpawnTools.spawnExplosion(Input.getMouseX(), Input.getMouseY(), 250f);
        }
        
        Camera.x = (int) x;
        Camera.y = (int) y;
    }
}
