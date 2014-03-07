package Game.Editor;

import Game.Enums.Icons;
import Game.Frame;
import Game.Game;
import static Game.Game.fileHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class MenuPanel extends JPanel implements ActionListener {

    public JMenuBar menubar = new JMenuBar();
    public JToolBar toolbar = new JToolBar();

    //menu file
    private final JMenu file = new JMenu("File");
    private final JMenu options = new JMenu("Options");
    private final JMenu help = new JMenu("Help");

    //items file
    private final JMenuItem newFile = new JMenuItem("New", Icons.NEW.getIcon());
    private final JMenuItem save = new JMenuItem("Save", Icons.SAVE.getIcon());
    private final JMenuItem saveAs = new JMenuItem("Save As..", Icons.SAVEAS.getIcon());
    private final JMenuItem load = new JMenuItem("Load", Icons.LOAD.getIcon());
    private final JMenuItem exit = new JMenuItem("Exit", Icons.EXIT.getIcon());

    //items settings
    private final JMenuItem settings = new JMenuItem("Settings", Icons.SETTINGS.getIcon());

    //items help
    private final JMenuItem faq = new JMenuItem("FAQ", Icons.FAQ.getIcon());
    private final JMenuItem about = new JMenuItem("About", Icons.ABOUT.getIcon());

    //items toolbar
    private final JButton cmdSave = new JButton(Icons.SAVE.getIcon());
    private final JButton cmdUndo = new JButton(Icons.UNDO.getIcon());
    private final JButton cmdRedo = new JButton(Icons.REDO.getIcon());
    private final JButton cmdRun = new JButton(Icons.START.getIcon());
    private final JButton cmdReset = new JButton(Icons.RESET.getIcon());

    private final JFrame frame = new JFrame();
    private static int step = 0;

    public MenuPanel() {
        menu();
        tools();
        this.setSize(this.getWidth(), this.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            fileHandler.newFile();
        } else if (e.getSource() == save) {
            fileHandler.save();
        } else if (e.getSource() == saveAs) {
            fileHandler.saveAs();
        } else if (e.getSource() == load) {
            fileHandler.load();
        } else if (e.getSource() == exit) {
            Frame.exit();
        } else if (e.getSource() == settings) {
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(Icons.SETTINGS.getImage());
            frame.setTitle("Settings");
            frame.setVisible(true);
        } else if (e.getSource() == faq) {
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(Icons.FAQ.getImage());
            frame.setTitle("FAQ");
            frame.setVisible(true);
        } else if (e.getSource() == about) {
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(Icons.ABOUT.getImage());
            frame.setTitle("About");
            frame.setVisible(true);
        } else if (e.getSource() == cmdSave) {
            fileHandler.save();
        } else if (e.getSource() == cmdUndo) {
            cmdRedo.setEnabled(true);
            if (!Game.step.isEmpty() && (Game.step.size() - 1 >= (-step))) {
                step--;
                Game.step.get(Game.step.size() + step).set();

                if (Game.step.size() == (-step)) {
                    cmdUndo.setEnabled(false);
                }
            }
        } else if (e.getSource() == cmdRedo) {
            cmdUndo.setEnabled(true);
            if ((-step) >= 1) {
                step++;
                Game.step.get(Game.step.size() - 1 + step).set();

                if ((-step) == 0) {
                    cmdRedo.setEnabled(false);
                }
            }
        } else if (e.getSource() == cmdRun) {
            if (Game.getReady()) {
                if (cmdRun.getIcon() == Icons.START.getIcon()) {
                    GamePanel.game.start();
                    cmdRun.setIcon(Icons.STOP.getIcon());

                } else {
                    GamePanel.game.stop();
                    cmdRun.setIcon(Icons.START.getIcon());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nicht alles ist gesetzt!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == cmdReset) {
            //Frame.gamePanel.removeAll();
            //GamePanel.newGame();
            //Frame.gamePanel.repaint();
            GamePanel.newGame();
            Frame.gamePanel.repaint();
        }
    }

    private void menu() {
        newFile.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        load.addActionListener(this);
        exit.addActionListener(this);

        settings.addActionListener(this);

        faq.addActionListener(this);
        about.addActionListener(this);

        menubar.add(file);
        menubar.add(options);
        menubar.add(help);

        file.add(newFile);
        file.add(load);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);

        options.add(settings);

        help.add(faq);
        help.add(about);

        menubar.setAlignmentX(0);
        menubar.setEnabled(false);
    }

    private void tools() {
        cmdSave.addActionListener(this);
        cmdUndo.addActionListener(this);
        cmdRedo.addActionListener(this);
        cmdRun.addActionListener(this);
        cmdReset.addActionListener(this);

        toolbar.add(cmdSave);
        toolbar.add(cmdUndo);
        toolbar.add(cmdRedo);
        toolbar.add(cmdRun);
        toolbar.add(cmdReset);

        cmdUndo.setEnabled(false);
        cmdRedo.setEnabled(false);

        toolbar.setBorder(new EtchedBorder());
        toolbar.setAlignmentX(0);
    }

    public void enabled() {
        for (int i = 0; i < menubar.getComponentCount(); i++) {
            menubar.getComponent(i).setEnabled(!menubar.getComponent(i).isEnabled());
        }
        for (int i = 0; i < toolbar.getComponentCount(); i++) {
            toolbar.getComponent(i).setEnabled(!toolbar.getComponent(i).isEnabled());
        }
        cmdRun.setEnabled(true);
    }

    public void enableUndo() {
        this.cmdUndo.setEnabled(true);
    }

    public static int getStep() {
        return step;
    }
}
