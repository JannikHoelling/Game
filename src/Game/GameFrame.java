package Game;

import Game.Editor.FileHandler;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

import static Game.World.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameFrame extends JFrame {

    public GamePanel panel = new GamePanel();

    public GameFrame(int x, int y) {
        setTitle("Teeworlds Fake");
        setSize(x, y);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        closeWindows();
       
        this.addComponentListener(new FrameListener());

        add(panel);
    }

    private void closeWindows() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Save game before closing?", "Confirm exit",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                switch (result) {
                    case JOptionPane.YES_OPTION:
                        Game.fileHandler.setExit();
                        Game.fileHandler.save();
                        return;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        return;
                    case JOptionPane.CLOSED_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        return;
                }
  }
});
    }
    
    private class FrameListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent ce) {
            PANEL_X = panel.getWidth();
            PANEL_Y = panel.getHeight();
        }

        @Override
        public void componentMoved(ComponentEvent ce) {}

        @Override
        public void componentShown(ComponentEvent ce) {}

        @Override
        public void componentHidden(ComponentEvent ce) {}
        
    }
}