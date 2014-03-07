package Game.Entity;

import Game.Enums.Interactives;
import Game.Renderer;
import static Game.World.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Box extends RigidBody {

    public Box(float x, float y) {
        super(x, y);
        
        this.image = Interactives.BOX.getImage();
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), TILE_SIZE, TILE_SIZE, null);
    }
    
    @Override
    public void onCollision() {
        destroy();
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x + HALF_TILE + 6, (int)y + HALF_TILE + 12, 20, 20);
    }
}
