package Game.Editor;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Game.Game;
import Game.Input;
import Game.Map.BlockType;
import static Game.Game.*;
import static Game.Map.Terrain.terrain;
import static Game.World.*;

public class FileHandler {

    private Formatter f;
    private Scanner sc;
    private final JFrame frame = new JFrame();
    private JFileChooser fileChooser;
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("save (*.sav)", "sav");
    private boolean saved = false; //true if game was saved
    private boolean exit = false; //true if user wants to exit game (for saving game)

    public void update() {
        //strg + s for saving
        if (Input.keys[KeyEvent.VK_S] && Input.keys[KeyEvent.VK_CONTROL]) {
            save();
            Input.keys[KeyEvent.VK_S] = false;
            Input.keys[KeyEvent.VK_CONTROL] = false;
            resetFrameTime();
        }
    }

    public void save() {
        if (FILE == null || !FILE.isEmpty()) {
            //if no current file
            if (!FILE.isEmpty()) {
                //if no current file
                pathEnding();
                try {
                    writeFile(new Formatter(PATH + FILE));
                    saved = true;
                    if (!isRunning()) {
                        JOptionPane.showMessageDialog(null, "Game saved!", "SAVED", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //set current file
                saveAs();
            }

            if (exit && saved) {
                //if game was saved and player wants to quit
                System.exit(0);
            }
        }
    }

    public void saveAs() {
        fileChooser = new JFileChooser("save\\"); //current direction
        fileChooser = setCurrentDir(); //current direction
        fileChooser.setFileFilter(filter); //ExtensionFilter
        fileChooser.setDialogTitle("Specify a file to save");

        int selection = fileChooser.showSaveDialog(frame);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (fileToSave.exists()) {
                //file exists - overwrite
                int result = JOptionPane.showConfirmDialog(null, "File already exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        //set current file
                        FILE = fileToSave.getAbsolutePath();
                        saved = true;
                        break;
                    case JOptionPane.NO_OPTION:
                        saveAs();
                        return;
                    case JOptionPane.CLOSED_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        return;
                }
            } else {
                //set current file
                FILE = fileToSave.getAbsolutePath();
            }

            //ending only once
            if (!FILE.contains(ENDING)) {
                FILE += ENDING;
            }

            pathEnding();

            try {
                f = new Formatter(FILE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            save();
        }
    }

    public void load() {
        fileChooser = new JFileChooser("save\\"); //current direction
        fileChooser.setFileFilter(filter); //ExtensionFilter
        fileChooser.setDialogTitle("Choose a file to open");

        int selection = fileChooser.showOpenDialog(frame);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            FILE = fileToOpen.getAbsolutePath();

            try {
                readFile(new Scanner(new File(FILE)));
                JOptionPane.showMessageDialog(null, "Game loaded!", "LOADED", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void newFile() {
        saveAs();
    }

    public static void createFolder() {
        //create saveFolder if not exists
        File dir = new File("save");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void writeFile(Formatter f) {
        //write into file
        for (int x = 0; x < WORLD_X; x++) {
            for (int y = 0; y < WORLD_Y; y++) {
                f.format("%d %d %s\n", x, y, terrain[x][y].blockType.name());
            }
        }

        f.format("%f %f %s %f %f\n", Game.player.getX(), Game.player.getY(), "Player", Game.player.getDX(), Game.player.getDY());
        f.close();
    }

    private void readFile(Scanner sc) {
        //read file and set blocktypes
        while (sc.hasNext()) {
            float x = sc.nextFloat();
            float y = sc.nextFloat();
            String type = sc.next();

            if (!type.equals("Player")) {
                terrain[(int) x][(int) y].blockType = BlockType.valueOf(type);
            } else {
                float dX = sc.nextFloat();
                float dY = sc.nextFloat();

                Game.player.setX(x);
                Game.player.setY(y);
                Game.player.setDX(dX);
                Game.player.setDY(dY);
            }

        }
        sc.close();
    }

    private void pathEnding() {
        //ending only once
        if (!FILE.contains(ENDING)) {
            FILE += ENDING;
        }
    }

    private JFileChooser setCurrentDir() {
        //current direction
        return new JFileChooser(PATH);
    }

    public void setExit() {
        //set true if user wants to quit
        exit = true;
    }

}