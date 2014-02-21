package Game.Editor;

import Game.Enums.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tab  extends JPanel{
    
    String name = "";
    GridLayout grid = new GridLayout(0, 4);
    GridBagConstraints bag = new GridBagConstraints();
    
    public Tab(String str) {
        this.name = str;
        this.setLayout(grid);

        switch(name) {
            case "Blocks":
                blocks();
                break;
            case "Interactives":
                interactives();
                break;
            case "Decorations":
                decorations();
                break;
            case "Players":
                players();
                break;
            default:
                blocks();       
        }
    } 
    
    private void blocks() {
        BlockType[] blocks = BlockType.values();
        for(int i=0; i<BlockType.values().length; i++) {
            add(new JLabel(blocks[i].getIcon()));
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void decorations() {
        Decorations[] decorations = Decorations.values();
        for(int i=0; i<Decorations.values().length; i++) {
            add(new JLabel(decorations[i].getIcon()), BorderLayout.NORTH);
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void interactives() {
        Interactives[] interactives = Interactives.values();
        for(int i=0; i<Interactives.values().length; i++) {
            add(new JLabel(interactives[i].getIcon()), BorderLayout.NORTH);
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void players() {
        Players[] players = Players.values();
        for(int i=0; i<Players.values().length; i++) {
            add(new JLabel(players[i].getIcon()), BorderLayout.NORTH);
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
}
