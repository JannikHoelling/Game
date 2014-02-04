package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Lokaler Benutzer
 */
public class Input implements KeyListener, MouseMotionListener, MouseListener {

    public static boolean[] keys = new boolean[KeyEvent.KEY_LAST];
    
    public static boolean mouseClicked;
    public static int mouseX, mouseY;
     
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }

    // TODO: Not moving mouse while moving player targets old position
    
    @Override
    public void mouseDragged(MouseEvent me) {
        mouseX = me.getX() - Game.getWidth()/2 + Camera.x;
        mouseY = me.getY() - Game.getHeight()/2 + Camera.y;
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX() - Game.getWidth()/2 + Camera.x;
        mouseY = me.getY() - Game.getHeight()/2 + Camera.y;
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {
        mouseClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
