package Game;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

import static Game.World.*;
import java.awt.Graphics2D;

/**
 *
 * @author Jannik
 */
public class GameFrame extends JFrame {

    public GamePanel panel = new GamePanel();

    public GameFrame(int x, int y) {
        setTitle("Teeworlds Fake");
        setSize(x, y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addComponentListener(new FrameListener());

        add(panel);
    }
    
    private class FrameListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent ce) {
            PANEL_X = panel.getWidth();
            PANEL_Y = panel.getHeight();
        }

        @Override
        public void componentMoved(ComponentEvent ce) {}

        @Override
        public void componentShown(ComponentEvent ce) {}

        @Override
        public void componentHidden(ComponentEvent ce) {}
        
    }
}