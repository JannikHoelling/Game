package Game.Editor;

import Game.Game;
import static Game.Game.fileHandler;
import Game.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class MenuPanel extends JPanel implements ActionListener {

    public JMenuBar menubar = new JMenuBar();
    public JToolBar toolbar = new JToolBar();
    
    //menu file
    private JMenu file = new JMenu("File");
    private JMenu options = new JMenu("Options");
    private JMenu help = new JMenu("Help");
    
    //items file
    private JMenuItem newFile = new JMenuItem("New", Icons.NEW.getIcon());
    private JMenuItem save = new JMenuItem("Save", Icons.SAVE.getIcon());
    private JMenuItem saveAs = new JMenuItem("Save As..", Icons.SAVEAS.getIcon());
    private JMenuItem load = new JMenuItem("Load", Icons.LOAD.getIcon());
    private JMenuItem exit = new JMenuItem("Exit", Icons.EXIT.getIcon());
    
    //items settings
    private JMenuItem settings = new JMenuItem("Settings", Icons.SETTINGS.getIcon());
    
    //items help
    private JMenuItem faq = new JMenuItem("FAQ", Icons.FAQ.getIcon());
    private JMenuItem about = new JMenuItem("About", Icons.ABOUT.getIcon());
    
    //items toolbar
    private JButton cmdSave = new JButton(Icons.SAVE.getIcon());
    private JButton cmdUndo = new JButton(Icons.UNDO.getIcon());
    private JButton cmdRedo = new JButton(Icons.REDO.getIcon());
    private JButton cmdRun = new JButton(Icons.START.getIcon());

    private GamePanel panel = new GamePanel();

    public MenuPanel() {
        menu();
        tools();     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            fileHandler.newFile();
        }
        if (e.getSource() == save) {
            fileHandler.save();
        }
        if (e.getSource() == saveAs) {
            fileHandler.saveAs();
        }
        if (e.getSource() == load) {
            fileHandler.load();
        }
        if (e.getSource() == exit) {
            EditorFrame.exit();
        }
        if (e.getSource() == settings) {
            JOptionPane.showMessageDialog(null, "SETTINGS", "SETTINGS", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == faq) {
            JOptionPane.showMessageDialog(null, "FAQ", "FAQ", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "ABOUT", "ABOUT", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == cmdSave) {
            fileHandler.save();
        }
        if (e.getSource() == cmdUndo) {
            JOptionPane.showMessageDialog(null, "UNDO", "UNDO", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == cmdRedo) {
            JOptionPane.showMessageDialog(null, "REDO", "REDO", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == cmdRun) {
            if (cmdRun.getIcon() == Icons.START.getIcon()) {
                Main.game.start();
                cmdRun.setIcon(Icons.STOP.getIcon());
                
            } else {
                Main.game.stop();
                cmdRun.setIcon(Icons.START.getIcon());
            }
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
        file.add(save);
        file.add(saveAs);
        file.add(load);
        file.addSeparator();
        file.add(exit);

        options.add(settings);

        help.add(faq);
        help.add(about);
        
        menubar.setAlignmentX(0);
    }

    private void tools() {
        cmdSave.addActionListener(this);
        cmdUndo.addActionListener(this);
        cmdRedo.addActionListener(this);
        cmdRun.addActionListener(this);

        toolbar.add(cmdSave);
        toolbar.add(cmdUndo);
        toolbar.add(cmdRedo);
        toolbar.add(cmdRun);

        toolbar.setBorder(new EtchedBorder());
        toolbar.setAlignmentX(0);
    }


}
