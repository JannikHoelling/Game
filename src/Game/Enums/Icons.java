package Game.Enums;

import javax.swing.ImageIcon;


public enum Icons {
    NEW("res\\icons\\new.png"), SAVE("res\\icons\\save.png"), SAVEAS("res\\icons\\saveAs.png"), LOAD("res\\icons\\load.png"),
    EXIT("res\\icons\\stop.png"), SETTINGS("res\\icons\\settings.png"), FAQ("res\\icons\\infos.png"), ABOUT("res\\icons\\infos.png"),
    UNDO("res\\icons\\undo.png"), REDO("res\\icons\\redo.png"), START("res\\icons\\run.png"), STOP("res\\icons\\stop.png");

    private final String location;
    private final ImageIcon icon;

    private Icons(String location) {
        
        this.location = location;
        this.icon = new ImageIcon(this.location);
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }

}
