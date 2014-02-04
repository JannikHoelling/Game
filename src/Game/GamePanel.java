package Game;

import static Game.Game.entities;
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

        g.drawString("Entities: " + Game.entities.size(), 0, 10);
        g.drawString("Camera: " + Camera.x + "|" + Camera.y, 0, 25);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }
}
