package Game.Editor;

import Game.GamePanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorFrame implements ActionListener {
    
    JFrame edFrame;
    Container container;
    JMenuBar menu = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenu options = new JMenu("Options");
    JMenu help = new JMenu("Help");
    
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem save = new JMenuItem("Save", new ImageIcon("res\\icon\\save.png"));
    JMenuItem saveAs = new JMenuItem("Save As..", new ImageIcon("res\\icon\\saveAs.png"));
    JMenuItem load = new JMenuItem("Load", new ImageIcon("res\\icon\\load.png"));
    JMenuItem exit = new JMenuItem("Exit", new ImageIcon("res\\icon\\stop.png"));
    
    JMenuItem settings = new JMenuItem("Settings", new ImageIcon("res\\icon\\settings.png"));

    JMenuItem faq = new JMenuItem("FAQ");
    JMenuItem about = new JMenuItem("About");
    
    public GamePanel panel = new GamePanel();

    public EditorFrame(int sizeX, int sizeY) {
        edFrame = new JFrame("Teeworlds Fake");
        container = edFrame.getContentPane();

        newFile.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        load.addActionListener(this);
        exit.addActionListener(this);
        
        settings.addActionListener(this);

        faq.addActionListener(this);
        about.addActionListener(this);

        menu.add(file);
        menu.add(options);
        menu.add(help);

        file.add(newFile);
        file.add(save);
        file.add(saveAs);
        file.add(load);
        file.add(exit);
        
        options.add(settings);

        help.add(faq);
        help.add(about);

        edFrame.add(menu, BorderLayout.NORTH);

        //edFrame.add(panel, BorderLayout.SOUTH);
        

        edFrame.setSize(sizeX, sizeY);
        edFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        edFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == newFile){
               System.out.println("newFile wurde angeklickt");
          }
          if (e.getSource() == save){
               System.out.println("save wurde angeklickt");
          }
          if (e.getSource() == saveAs){
               System.out.println("saveAs wurde angeklickt");
          }
          if (e.getSource() == load){
               System.out.println("load wurde angeklickt");
          }
          if (e.getSource() == exit){
               System.out.println("exit wurde angeklickt");
          }
          if (e.getSource() == settings){
               System.out.println("settings wurde angeklickt");
          }
          if (e.getSource() == faq){
               System.out.println("faq wurde angeklickt");
          }
          if (e.getSource() == about){
               System.out.println("about wurde angeklickt");
          }
     }

}
