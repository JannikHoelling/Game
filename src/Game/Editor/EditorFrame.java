package Game.Editor;

import Game.Game;
import static Game.World.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class EditorFrame extends JFrame {
      
    private MenuPanel menuPanel = new MenuPanel();
    private EditorPanel editorPanel = new EditorPanel(); 
    private JPanel gamePanel = new JPanel(); //panel for the game (position)
    private GridLayout layout = new GridLayout(0, 5, 1, 1);
    
    public GamePanel panel = new GamePanel();
    
    
    public EditorFrame() {
        this.setTitle("Teeworlds Fake");
        this.setSize(FRAME_X, FRAME_Y);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        closeWindows();

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBorder(BorderFactory.createEtchedBorder());
        gamePanel.add(panel);

        menuPanel.setLayout(layout);
        setJMenuBar(menuPanel.menubar);
        
        add(gamePanel, BorderLayout.CENTER);
        add(menuPanel.toolbar, BorderLayout.NORTH);
        add(editorPanel, BorderLayout.WEST);

        this.addComponentListener(new FrameListener());
    }

    private void closeWindows() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(); //ask for saving bevor closing
            }
        });
    }

    public static void exit() {
        //ask for saving bevor closing
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
        }
    }
    
    private class FrameListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent ce) {
            FRAME_X = panel.getWidth();
            FRAME_Y = panel.getHeight();
        }

        @Override
        public void componentMoved(ComponentEvent ce) {}

        @Override
        public void componentShown(ComponentEvent ce) {}

        @Override
        public void componentHidden(ComponentEvent ce) {}
        
    }
}
