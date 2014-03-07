package Game.Enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public enum Icons {
    NEW("res\\icons\\new.png"), SAVE("res\\icons\\save.png"), SAVEAS("res\\icons\\saveAs.png"), LOAD("res\\icons\\load.png"),
    EXIT("res\\icons\\stop.png"), SETTINGS("res\\icons\\settings.png"), FAQ("res\\icons\\faq.png"), ABOUT("res\\icons\\infos.png"),
    UNDO("res\\icons\\undo.png"), REDO("res\\icons\\redo.png"), START("res\\icons\\run.png"), STOP("res\\icons\\stop.png"),
    RESET("res\\icons\\reset.png");

    private final String location;
    private ImageIcon icon;
    private BufferedImage image;

    private Icons(String location) {
        this.location = location;
        
        try {
            this.image = ImageIO.read(new File(location));
            this.icon = new ImageIcon(this.location);
        } catch (IOException ex) {
            Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }

}
