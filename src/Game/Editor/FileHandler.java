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

import static Game.Game.correctTime;
import static Game.Map.Terrain.terrain;
import static Game.World.*;

public class FileHandler {

    Formatter f;
    Scanner sc;

    JFrame frame = new JFrame();
    JFileChooser fileChooser;

    FileNameExtensionFilter filter = new FileNameExtensionFilter("save (*.sav)", "sav");
    
    private boolean saved = false;
    private boolean exit = false;

    public void update() {
        if (Input.keys[KeyEvent.VK_S] && Input.keys[KeyEvent.VK_CONTROL]) {
            save();
            Input.keys[KeyEvent.VK_S] = false;
            Input.keys[KeyEvent.VK_CONTROL] = false;
            correctTime();
        } else if (Input.keys[KeyEvent.VK_A] && Input.keys[KeyEvent.VK_CONTROL]) {
            saveAs();
            Input.keys[KeyEvent.VK_A] = false;
            Input.keys[KeyEvent.VK_CONTROL] = false;
            correctTime();
        } else if (Input.keys[KeyEvent.VK_D] && Input.keys[KeyEvent.VK_CONTROL]) {
            load();
            Input.keys[KeyEvent.VK_D] = false;
            Input.keys[KeyEvent.VK_CONTROL] = false;
            correctTime();
        }
    }

    public void save() {
        if (FILE == null || !FILE.isEmpty()) {
            try {
                writeFile(new Formatter(FILE));
                saved = true;
                JOptionPane.showMessageDialog(null, "Game saved!", "SAVED", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            saveAs();
        }
        
        if(exit && saved) {
                System.exit(0);
            }
    }

    public void saveAs() {
        fileChooser = new JFileChooser("save\\");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to save");

        int selection = fileChooser.showSaveDialog(frame);

        if (selection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (fileToSave.exists()) {
                int result = JOptionPane.showConfirmDialog(null, "File already exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (result) {
                    case JOptionPane.YES_OPTION:
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
                FILE = fileToSave.getAbsolutePath();
            }

            if (!FILE.contains(ENDING)) {
                FILE += ENDING;
            }
            try {
                f = new Formatter(FILE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            save();
        }
    }

    public void load() {
        fileChooser = new JFileChooser("save\\");
        fileChooser.setFileFilter(filter);
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
        File dir = new File("save");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void writeFile(Formatter f) {
        for (int x = 0; x < WORLD_X; x++) {
            for (int y = 0; y < WORLD_Y; y++) {
                f.format("%d %d %s\n", x, y, terrain[x][y].blockType.name());
            }
        }

        f.format("%f %f %s %f %f\n", Game.player.getX(), Game.player.getY(), "Player", Game.player.getDX(), Game.player.getDY());
        f.close();
    }

    private void readFile(Scanner sc) {
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
    }
    
    public void setExit() {
        exit = true;
    }

}
