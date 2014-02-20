package Game.Editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static Game.World.*;


public class EditorPanel extends JPanel {

    private final JPanel selectionPanel = new JPanel(); //selectionsBox
    private final JPanel holderPanel = new JPanel(); //all panels with items
    private final JPanel sidePanel = new JPanel(); //selection + holder
    private final JPanel blocksPanel = new JPanel(); //panel for blockitems
    private final JPanel interactivesPanel = new JPanel(); //panel for interactiveitems
    private final JPanel decorationPanel = new JPanel(); //panel for decorationitems
    private final JPanel playerPanel = new JPanel(); //panel for playeritems
    private final JComboBox<String> objectSelection = new JComboBox(SELECTIONS); //selectionsBox
    private final GridLayout layout = new GridLayout(0, 5, 1, 1); //layout for panels with items

    public EditorPanel() {
        editor();  
    }

    private void editor() {
        selectionPanel.setLayout(new BorderLayout());
        selectionPanel.setBorder(BorderFactory.createEtchedBorder());
        selectionPanel.add(objectSelection, BorderLayout.NORTH); //dropDown Menu
        
        //setLayout and visibility
        blocksPanel.setLayout(layout);
        blocksPanel.setVisible(true);
        interactivesPanel.setLayout(layout);
        interactivesPanel.setVisible(false);
        decorationPanel.setLayout(layout);
        decorationPanel.setVisible(false);
        playerPanel.setLayout(layout);
        playerPanel.setVisible(false);
        
        //add panels to holder
        holderPanel.add(blocksPanel);
        holderPanel.add(interactivesPanel);
        holderPanel.add(decorationPanel);
        holderPanel.add(playerPanel);
        selectionPanel.add(new JScrollPane(holderPanel), BorderLayout.CENTER);
        
        //set sidePanel
        sidePanel.setLayout(new GridLayout(2, 1, 1, 1));
        sidePanel.add(selectionPanel);
        sidePanel.setPreferredSize(new Dimension(EDITOR_X, EDITOR_Y));

        //setLayout and visibility
        blocksPanel.setLayout(layout);
        blocksPanel.setVisible(true);
        interactivesPanel.setLayout(layout);
        interactivesPanel.setVisible(false);
        decorationPanel.setLayout(layout);
        decorationPanel.setVisible(false);
        playerPanel.setLayout(layout);
        playerPanel.setVisible(false);
        
        //add panels to holder
        holderPanel.add(blocksPanel);
        holderPanel.add(interactivesPanel);
        holderPanel.add(decorationPanel);
        holderPanel.add(playerPanel);
        selectionPanel.add(new JScrollPane(holderPanel), BorderLayout.CENTER);
        
        //set sidePanel
        sidePanel.setLayout(new GridLayout(2, 1, 1, 1));
        sidePanel.add(selectionPanel);
        holderPanel.setPreferredSize(new Dimension(EDITOR_X, EDITOR_Y));
        
        add(sidePanel, BorderLayout.WEST);
    }

}