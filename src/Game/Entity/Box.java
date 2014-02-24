package Game.Entity;

import Game.Enums.Interactives;
import Game.Renderer;
import static Game.World.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Box extends RigidBody {
    
    private final BufferedImage imageBox;

    public Box(float x, float y) {
        super(x, y);
        
        this.imageBox = Interactives.BOX.getImage();
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), TILE_SIZE, TILE_SIZE, null);
    }
    
}
