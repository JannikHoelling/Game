package Game;

import Game.Editor.Tab;
import Game.Entity.Player;
import Game.Enums.*;
import Game.Map.Terrain;
import static Game.World.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseMotionListener, MouseListener {

    public static boolean[] keys = new boolean[KeyEvent.KEY_LAST];

    public static boolean mouseClicked;
    public static int mouseX, mouseY;

    private static String name = "";
    private static String type = "";
    
    Dimension dim = new Dimension();

    public static void setIconName(String name) {
        String[] splitting;

        splitting = name.split("-");
        Input.name = splitting[0];
        Input.type = splitting[1];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (type != null && !type.equals("") && name != null && !name.equals("")) { 
            dim = Terrain.positionInArray(getMouseX(), getMouseY());
            switch (type) {
                case "BLOCKS":
                    //Terrain.positionInArray(getMouseX(), getMouseY()).setBlockType(BlockType.valueOf(name));
                    Terrain.terrain[dim.width][dim.height].setBlockType(BlockType.valueOf(name));
                    break;
                case "INTERACTIVES":
                    //Terrain.test(getMouseX(), getMouseY()).setImage(Interactives.valueOf(name).getImage());
                    break;
                case "DECORATIONS":
                    //Terrain.test(getMouseX(), getMouseY()).setImage(Decorations.valueOf(name).getImage());
                    break;
                case "PLAYERS":
                    Game.player.setPosition(dim);
                    Player.setImage(Players.valueOf(name).getImage());
                    //Terrain.test(getMouseX(), getMouseY()).setImage(Players.valueOf(name).getImage());
                    break;
            }
        }
        Frame.panel.repaint();
    }
    
    public static void setNull() {
        Input.name = "";
        Input.type = "";
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public static int getMouseX() {
        return mouseX - World.HALF_FRAME_X + Camera.x;
    }

    public static int getMouseY() {
        return mouseY - World.HALF_FRAME_Y + Camera.y;
    }
}
