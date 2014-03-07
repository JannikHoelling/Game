package Game.Entity;

import Game.Enums.Interactives;
import Game.Renderer;
import static Game.World.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Deco extends Entity {

    public Deco(float x, float y, BufferedImage image) {
        super(x, y);
        
        this.image = image;
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), TILE_SIZE, TILE_SIZE, null);
    } 
}
