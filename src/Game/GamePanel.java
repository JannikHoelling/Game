package Game;

import static Game.Game.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Jannik
 */
public class GamePanel extends JPanel {

    public GamePanel() {
        setFocusable(true);
    }

    @Override
    public void paint(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;
        
        g.setColor(new Color(0, 170, 255));
        g.fillRect(0, 0, World.PANEL_X, World.PANEL_Y);
        g.setColor(Color.BLACK);

        g.drawString("Entities: " + Game.entities.size(), 0, 10);
        g.drawString("Camera: " + Camera.x + "|" + Camera.y, 0, 25);
        
        g.drawOval(Input.getMouseX() + Renderer.offsetX() - 10, Input.getMouseY() + Renderer.offsetY() - 10, 20, 20);

        Game.terrain.render(g);
        
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }
}
