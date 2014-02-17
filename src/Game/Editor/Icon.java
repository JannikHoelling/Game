package Game.Editor;

import javax.swing.ImageIcon;


public enum Icon {

    NEW("res\\icon\\new.png"), SAVE("res\\icon\\save.png"), SAVEAS("res\\icon\\saveAs.png"), LOAD("res\\icon\\load.png");

    public final String location;

    public ImageIcon icon;

    private Icon(String location) {
        
        this.location = location;
        icon = new ImageIcon(location);

    }

}
