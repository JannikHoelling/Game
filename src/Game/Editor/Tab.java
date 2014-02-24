package Game.Editor;

import Game.Enums.*;
import Game.Input;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tab  extends JPanel{
    
    String name = "";
    GridLayout grid = new GridLayout(0, 4);
    GridBagConstraints bag = new GridBagConstraints();
    
    public static ArrayList<JLabel> list = new ArrayList<>();
    
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
        Listener();
        
    } 
    
    private void blocks() {
        BlockType[] blocks = BlockType.values();
        for(int i=0; i<BlockType.values().length; i++) {
            list.add(new JLabel(blocks[i].getIcon()));
            list.get(list.size()-1).setName(blocks[i].name() + "-BLOCKS");
            add(list.get(list.size()-1));
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void decorations() {
        Decorations[] decorations = Decorations.values();
        for(int i=0; i<Decorations.values().length; i++) {
            list.add(new JLabel(decorations[i].getIcon()));
            list.get(list.size()-1).setName(decorations[i].name() + "-DECORATIONS");
            add(list.get(list.size()-1));
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void interactives() {
        Interactives[] interactives = Interactives.values();
        for(int i=0; i<Interactives.values().length; i++) {
            list.add(new JLabel(interactives[i].getIcon()));
            list.get(list.size()-1).setName(interactives[i].name() + "-INTERACTIVES");
            add(list.get(list.size()-1));
            
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void players() {
        Players[] players = Players.values();
        for(int i=0; i<Players.values().length; i++) {
            list.add(new JLabel(players[i].getIcon()));
            list.get(list.size()-1).setName(players[i].name() + "-PLAYERS");
            add(list.get(list.size()-1));
        }
        for(int i=0; i<48-BlockType.values().length; i++) {
            add(new JLabel(new ImageIcon("res\\null.png")));
        }
    }
    
    private void Listener() {
        for(int i=0; i<list.size(); i++) {
            list.get(i).addMouseListener(new Mouse());
        }
    }
    
    
    private class Mouse extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            for(int i=0; i<list.size(); i++) {
                if(e.getSource().equals(list.get(i))) {
                    Input.setIconName(list.get(i).getName());
                    return;
                }   
           }          
        }
    }
}
