package Game.File;

import Game.Input;
import static Game.World.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateFile {

    File file;
    Formatter f;

    public void update() {
        if (Input.keys[KeyEvent.VK_J]) {
            create();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void create() {
        file = new File(FILE);
        
        try {
            if (!file.exists()) {
                f = new Formatter(FILE);
                System.out.println("file created");
            } else {
                System.out.println("file exists!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

}
