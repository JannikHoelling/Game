package Game.Enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public enum Players {
    RED("res\\players\\red.png"), GREEN("res\\players\\green.png"), BLUE("res\\players\\blue.png"), YELLOW("res\\players\\yellow.png");

    private final String location;
    private ImageIcon icon;
    private BufferedImage image;

    private Players(String location) {
        this.location = location;

        try {
            image = ImageIO.read(new File(location));
            this.icon = new ImageIcon(this.location);
        } catch (IOException ex) {
            Logger.getLogger(BlockType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }

}
