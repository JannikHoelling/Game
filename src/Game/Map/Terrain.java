/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Map;

import Game.Renderer;
import java.awt.Graphics2D;
import java.util.Random;

import static Game.World.*;
import java.awt.Rectangle;

/**
 *
 * @author Jannik
 */
public class Terrain {

    public static Block[][] terrain;
    public static Rectangle bounds;

    public Terrain() {
        terrain = new Block[WORLD_X][WORLD_Y];

        Random rand = new Random();

        for (int x = 0; x < WORLD_X; x++) {
            for (int y = 0; y < WORLD_Y; y++) {
                if (rand.nextFloat() < 0.2f) {
                    terrain[x][y] = new Block();
                    terrain[x][y].blockType = BlockType.DIRT;
                } else {
                    terrain[x][y] = new Block();
                }
            }
        }

        bounds = new Rectangle(0, 0, WORLD_X * TILE_SIZE, WORLD_Y * TILE_SIZE);
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < terrain.length; x++) {
            for (int y = 0; y < terrain[0].length; y++) {
                if (terrain[x][y].blockType != BlockType.AIR) {
                    g.drawImage(terrain[x][y].blockType.image, (int) x * TILE_SIZE + Renderer.offsetX() - WORLD_X * TILE_SIZE / 2, (int) y * TILE_SIZE + Renderer.offsetY() - WORLD_Y * TILE_SIZE / 2, TILE_SIZE, TILE_SIZE, null);
                    //g.fillRect((int) x * TILE_SIZE + Renderer.offsetX() - WORLD_X * TILE_SIZE / 2, (int) y * TILE_SIZE + Renderer.offsetY() - WORLD_Y * TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
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
                return terrain[xPosition][yPosition].blockType == BlockType.AIR;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
