package Game.File;

import Game.Game;
import Game.Input;
import Game.Map.BlockType;
import static Game.Map.Terrain.terrain;
import static Game.World.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadFile {

    private Scanner sc;

    public void update() {
        if(Input.keys[KeyEvent.VK_L]) {
            openFile();
            readFile();
            closeFile();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void closeFile() {
        sc.close();

    }

    private void readFile() {
        int a =0;
        while (sc.hasNext()) {
            float x = sc.nextFloat();
            float y = sc.nextFloat();
            String type = sc.next();
            
            if(!type.equals("Player")) {
                terrain[(int)x][(int)y].blockType = BlockType.valueOf(type);
            } else {
                Game.player.setX(x);
                Game.player.setY(y);
            }
            
        }
        
    }

    private void openFile() {
        try {
            sc = new Scanner(new File(FILE));
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

}
