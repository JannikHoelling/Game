package Game;

import Game.Editor.GamePanel;
import Game.Editor.Step;
import Game.Entity.*;
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
    Dimension dimMouse = new Dimension();
    Dimension dimEntity = new Dimension();

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
        this.paintEditor();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    public static void setNull() {
        Input.name = "";
        Input.type = "";
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseClicked = true;
        this.paintEditor();
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

    public void paintEditor() {
        if (type != null && !type.equals("") && name != null && !name.equals("")) {
            dimMouse = new Dimension(getMouseX(), getMouseY());
            dim = Terrain.positionInArray(dimMouse.width, dimMouse.height);

            if (dim.width < WORLD_X - 1 && dim.width > 0 && dim.height < WORLD_Y - 1 && dim.height > 0) {
                if (Terrain.terrain[dim.width][dim.height].blockType == BlockType.AIR && !isEntityOnPosition(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height))) {
                    switch (type) {
                        case "BLOCKS":
                            if (!Terrain.terrain[dim.width][dim.height].blockType.equals(BlockType.valueOf(name))) {
                                Game.step.add(new Step(dim.width, dim.height, Terrain.terrain[dim.width][dim.height].blockType, BlockType.valueOf(name), null));
                                Terrain.terrain[dim.width][dim.height].setBlockType(BlockType.valueOf(name));
                            }
                            break;
                        case "INTERACTIVES":
                            switch (name) {
                                case "TELEPORT":
                                    if (Game.teleporter.isEmpty() || Game.teleporter.get(Game.teleporter.size() - 1).getSet()) {
                                        new Teleporter(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height), null);
                                        Game.step.add(new Step(dim.width, dim.height, Terrain.terrain[dim.width][dim.height].blockType, null, Game.teleporter.get(Game.teleporter.size() - 1)));
                                        Game.setReady(false);
                                        Game.teleporter.get(Game.teleporter.size() - 1).setNumber(Game.teleporter.size() / 2);
                                        mouseClicked = false;
                                    }

                                    if (mouseClicked && !Game.teleporter.get(Game.teleporter.size() - 1).getSet()) {
                                        new Teleporter(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height), Game.teleporter.get(Game.teleporter.size() - 1));
                                        Game.teleporter.get(Game.teleporter.size() - 2).setTarget(Game.teleporter.get(Game.teleporter.size() - 1));
                                        Game.setReady(true);
                                        mouseClicked = false;
                                    }
                                    break;
                                case "BOX":
                                    if ((Terrain.terrain[dim.width][dim.height].blockType == BlockType.AIR && !isEntityOnPosition(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height)))) {
                                        new Box(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height));
                                        mouseClicked = false;
                                    }
                                    break;
                            }
                            break;
                        case "DECORATIONS":
                            new Deco(Terrain.positionBlock(dimMouse.width), Terrain.positionBlock(dimMouse.height), Decorations.valueOf(name).getImage());
                            break;
                        case "PLAYERS":
                            Game.player.setX(Terrain.positionBlock(dimMouse.width));
                            Game.player.setY(Terrain.positionBlock(dimMouse.height));
                            Game.player.setImage(Players.valueOf(name).getImage());
                            break;
                    }
                    dimEntity = Terrain.positionInArray(Game.entities.get(Game.entities.size() - 1).getX(), Game.entities.get(Game.entities.size() - 1).getY());
                    if (Terrain.terrain[dimEntity.width][dimEntity.height].blockType != BlockType.AIR) {
                        if (Game.entities.size() == 1) {
                            if (dimEntity.width > dim.width) {
                                Game.entities.get(Game.entities.size() - 1).setX(Terrain.positionBlock(Game.entities.get(Game.entities.size() - 1).getX() - TILE_SIZE));
                            } else {
                                Game.entities.get(Game.entities.size() - 1).setY(Terrain.positionBlock(Game.entities.get(Game.entities.size() - 1).getY() - TILE_SIZE));
                            }
                        } else {
                            if (Game.teleporter.size() > 0 && Game.entities.get(Game.entities.size() - 1).equals(Game.teleporter.get(Game.teleporter.size() - 1))) {
                                Game.teleporter.get(Game.teleporter.size() - 1).destroy();
                                Game.teleporter.remove(Game.teleporter.size() - 1);
                            }
                            Game.entities.get(Game.entities.size() - 1).destroy();
                            Game.entities.remove(Game.entities.size() - 1);
                        }
                    }
                }
            }
            GamePanel.game.update(0);
            Frame.gamePanel.repaint();
        }

    }

    public boolean isEntityOnPosition(float x, float y) {
        for (int i = 0; i < Game.entities.size(); i++) {
            if (x == Terrain.positionBlock(Game.entities.get(i).getX()) && y == Terrain.positionBlock(Game.entities.get(i).getY())) {
                return true;
            }
        }
        return false;
    }

}
