package Game.Map;

import Game.Enums.BlockType;
import Game.Renderer;
import java.awt.Graphics2D;
import java.util.Random;
import static Game.World.*;
import java.awt.Dimension;
import java.awt.Rectangle;


public class Terrain {

    public static Block[][] terrain;
    public static Rectangle bounds;

    public Terrain() {
        terrain = new Block[WORLD_X][WORLD_Y];
        
        randomMap();
        bounds = new Rectangle(0, 0, WORLD_X * TILE_SIZE, WORLD_Y * TILE_SIZE);
    }
   
    public void render(Graphics2D g) {
        
        for (int x = 0; x < terrain.length; x++) {
            for (int y = 0; y < terrain[0].length; y++) {
                if (terrain[x][y].getImage() != BlockType.AIR.getImage()) {
                    g.drawImage(terrain[x][y].blockType.getImage(), (int) x * TILE_SIZE + Renderer.offsetX() - WORLD_X * TILE_SIZE / 2, (int) y * TILE_SIZE + Renderer.offsetY() - WORLD_Y * TILE_SIZE / 2, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }
    
    private void randomMap() {
        //random map
        Random rand = new Random();

        for (int x = 0; x < WORLD_X; x++) {
            for (int y = 0; y < WORLD_Y; y++) {
                if (rand.nextFloat() < 0.05f || x == 0 || y == 0 || x == WORLD_X-1 || y == WORLD_Y-1) {
                    terrain[x][y] = new Block();
                    terrain[x][y].setBlockType(BlockType.DIRT);
                } else {
                    terrain[x][y] = new Block();
                }
            }
        }
    }

    public static boolean getBlock(float x, float y) {

        x += TILE_SIZE * WORLD_X / 2;
        y += TILE_SIZE * WORLD_Y / 2;

        x /= TILE_SIZE;
        y /= TILE_SIZE;

        int xPosition = (int) x;
        int yPosition = (int) y;

        try {
            if (bounds.contains(x, y)) {
                return terrain[xPosition][yPosition].getImage() == BlockType.AIR.getImage();
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    
    public static int positionBlock(float value) {       
        return ((int) Math.ceil((value - HALF_TILE) / TILE_SIZE)) * TILE_SIZE;
    }
    
//    public static Block positionInArray(float x, float y) {
//        x += TILE_SIZE * WORLD_X / 2;
//        y += TILE_SIZE * WORLD_Y / 2;
//
//        x /= TILE_SIZE;
//        y /= TILE_SIZE;
//
//        int xPosition = (int) x;
//        int yPosition = (int) y;
//        return terrain[xPosition][yPosition];
//    }
    
    public static Dimension positionInArray(float x, float y) {
        x += TILE_SIZE * WORLD_X / 2;
        y += TILE_SIZE * WORLD_Y / 2;

        x /= TILE_SIZE;
        y /= TILE_SIZE;

        int xPosition = (int) x;
        int yPosition = (int) y;
        return new Dimension(xPosition, yPosition);
    }
}
