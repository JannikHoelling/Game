package Game.Enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public enum Interactives {
    BOX("res\\interactives\\box.png"), TREE("res\\interactives\\tree.png"), BUCK("res\\interactives\\buck.png"), ROCK("res\\interactives\\rock.png");
    private final String location;
    private ImageIcon icon;
    private BufferedImage image;

    private Interactives(String location) {
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
