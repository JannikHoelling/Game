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

        g.drawImage(image, PANEL_X / 2 - HALF_TILE, PANEL_Y / 2 - HALF_TILE, TILE_SIZE, TILE_SIZE, null);

        g.drawRect(PANEL_X / 2 - HALF_TILE, PANEL_Y / 2 - HALF_TILE, TILE_SIZE-1, TILE_SIZE-1);
    }

    @Override
    public void update(float delta) {
        
        if (Input.keys[KeyEvent.VK_A] && !Input.keys[KeyEvent.VK_D]) {
            x -= delta * speed;
            if (!Terrain.getBlock(x - HALF_TILE, y - HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE-1)) {
                x = magic(x) + HALF_TILE;
            }
        }
        if (Input.keys[KeyEvent.VK_D] && !Input.keys[KeyEvent.VK_A]) {
            x += delta * speed;
            if (!Terrain.getBlock(x + HALF_TILE, y - HALF_TILE) || !Terrain.getBlock(x + HALF_TILE, y + HALF_TILE-1)) {
                x = magic(x) - HALF_TILE;
            }
        }
        if (Input.keys[KeyEvent.VK_W] && !Input.keys[KeyEvent.VK_S]) {
            y -= delta * speed;
            if (!Terrain.getBlock(x + HALF_TILE-1, y - HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y - HALF_TILE)) {
                y = magic(y) + HALF_TILE;
            }
        }
        if (Input.keys[KeyEvent.VK_S] && !Input.keys[KeyEvent.VK_W]) {
            y += delta * speed;
            if (!Terrain.getBlock(x + HALF_TILE-1, y + HALF_TILE) || !Terrain.getBlock(x - HALF_TILE, y + HALF_TILE)) {
                y = magic(y) - HALF_TILE;
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

    public int magic(float value) {       
        return ((int) Math.ceil((value - HALF_TILE) / TILE_SIZE)) * TILE_SIZE;
    }
}
