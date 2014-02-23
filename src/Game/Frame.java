package Game;

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

import Game.Editor.*;
import static Game.World.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Frame extends JFrame {

    public static MenuPanel menuPanel = new MenuPanel();

    private final JPanel panel = new JPanel(); //panel for the game (position)
    private final JPanel edPanel = new JPanel(); //panel for the game (position)
    private final GridLayout layout = new GridLayout(0, 5, 1, 1);

    public static GamePanel gamePanel = new GamePanel();
    public static EditorPanel editorPanel = new EditorPanel();
    
    private final Input input = new Input();

    public Frame(String args) {
        this.pack();
        this.setSize(FRAME_X, FRAME_Y);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIcon();
        
        //this.closeWindows();
        gamePanel.validate();

        FileHandler.createFolder(); //create save-Folder

        if (args.equals("-editor")) {
            this.setTitle("Hunting Animals - Editor");
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.add(gamePanel);

            menuPanel.setLayout(layout);
            this.setJMenuBar(menuPanel.menubar);
            this.add(panel, BorderLayout.CENTER);
            this.add(menuPanel.toolbar, BorderLayout.NORTH);
            this.add(editorPanel, BorderLayout.WEST);
            this.pack();
        } else {
            this.setTitle("Hunting Animals - Game");
            this.initVar();
            this.add(gamePanel);
            GamePanel.game.start();
        }
        this.addComponentListener(new FrameListener());
        this.setListener();

        this.setVisible(true);
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
    
    private void setListener() {
        gamePanel.addKeyListener(input);
        gamePanel.addMouseMotionListener(input);
        gamePanel.addMouseListener(input);
    }

    private class FrameListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent ce) {
            FRAME_X = gamePanel.getWidth();
            FRAME_Y = gamePanel.getHeight();
        }

        @Override
        public void componentMoved(ComponentEvent ce) {
        }

        @Override
        public void componentShown(ComponentEvent ce) {
        }

        @Override
        public void componentHidden(ComponentEvent ce) {
        }

    }

    private void initVar() {
        GAME_X = FRAME_X;
        GAME_Y = FRAME_Y;
    }

    public static void enableClickable() {
        menuPanel.enabled();
        editorPanel.enabled();
    }

    public static void disableClickable() {
        enableClickable();
    }
    
    private void setIcon() {
        try {
            this.setIconImage(ImageIO.read(new File("res\\icon.png")));
            
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
