package Game.Entity;

import Game.Camera;
import java.awt.Graphics2D;

/**
 *
 * @author Jannik
 */
public class Entity {

    public float x;
    public float y;

    public boolean delete = false;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(double delta) {

    }

    public void render(Graphics2D g) {
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
    }

    public void markAsDelete() {
        delete = true;
    }
}
