package Game.Editor;

import Game.*;
import static Game.Game.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;     
import static Game.World.*;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    public static Game game = new Game();
    
    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow(); //focus KeyListener to gamepanel     
    }

    @Override
    public void paint(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(0, 170, 255));
        g.fillRect(0, 0,GAME_X ,GAME_Y);
        g.setColor(Color.BLACK);

        g.drawOval(Input.getMouseX() + Renderer.offsetX() - 10, Input.getMouseY() + Renderer.offsetY() - 10, 20, 20);

        Game.terrain.render(g);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
        
        g.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        g.drawString("Main: " + Game.timePerFrame + "ms", 0, 18);
        g.drawString("Entities: " + Game.entities.size(), 0, 18 * 2);
        g.drawString("Camera: " + Camera.x + "|" + Camera.y, 0, 18 * 3);
        g.drawString("Mouse: " + Input.getMouseX() + "|" + Input.getMouseX(), 0, 18 * 4);

    }
}
