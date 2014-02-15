package Game.File;

import Game.Entity.*;
import Game.Entity.Player;
import Game.*;
import Game.Map.Block;
import Game.Map.BlockType;
import static Game.Map.Terrain.terrain;
import static Game.World.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {

    private Formatter f;

    public void update() {
        if(Input.keys[KeyEvent.VK_K]) {
            openFile();
            writeFile();
            closeFile();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }

    private void closeFile() {
        f.close();

    }

    private void writeFile() {
        for (int x = 0; x < WORLD_X; x++) {
            for (int y = 0; y < WORLD_Y; y++) {
                f.format("%d %d %s\n", x, y, terrain[x][y].blockType.name());
            }
        }
        
        f.format("%f %f %s\n", Game.player.getX(), Game.player.getY(), "Player");
    }

    private void openFile() {
        try {
            f = new Formatter(FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

}
