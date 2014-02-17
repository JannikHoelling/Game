package Game.Editor;

import javax.swing.ImageIcon;


public enum Icons {
    NEW("res\\icon\\new.png"), SAVE("res\\icon\\save.png"), SAVEAS("res\\icon\\saveAs.png"), LOAD("res\\icon\\load.png"),
    EXIT("res\\icon\\stop.png"), SETTINGS("res\\icon\\settings.png"), FAQ("res\\icon\\infos.png"), ABOUT("res\\icon\\infos.png"),
    UNDO("res\\icon\\undo.png"), REDO("res\\icon\\redo.png"), START("res\\icon\\run.png"), STOP("res\\icon\\stop.png");

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
