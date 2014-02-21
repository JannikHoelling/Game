package Game.Editor;

import static Game.Frame.editorPanel;
import static Game.World.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EditorPanel extends JPanel {

    JPanel pBlocks = new JPanel();
    JPanel pInteractives = new JPanel();
    JPanel pDecorations = new JPanel();
    JPanel pPlayers = new JPanel();
    JTabbedPane tabpane = new JTabbedPane();
    GridBagLayout gridbag = new GridBagLayout();

    public EditorPanel() {
        editor();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder());

        this.add(tabpane);
    }

    private void editor() {

        tabpane.addTab("Blocks", new Tab("Blocks"));
        tabpane.addTab("Interactives", new Tab("Interactives"));
        tabpane.addTab("Decorations", new Tab("Decorations"));
        tabpane.addTab("Players", new Tab("Players"));

        tabpane.setPreferredSize(new Dimension(EDITOR_X, EDITOR_Y));
    }
    
    public void enabled() {
        tabpane.setEnabled(!tabpane.isEnabled());
        
        for(int i=0; i<Tab.list.size(); i++) {
            Tab.list.get(i).setEnabled(tabpane.isEnabled());
        }
    }

}
