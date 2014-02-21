package Game.Enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public enum Decorations {

    FLOWER1("res\\decorations\\flower1.png"), FLOWER2("res\\decorations\\flower2.png"), FLOWER3("res\\decorations\\flower3.png"),
    FLOWER4("res\\decorations\\flower4.png"), TREE("res\\decorations\\tree.png"),
    GRASS("res\\decorations\\grass.png"), ROCK("res\\decorations\\rock.png");

    private final String location;
    private BufferedImage image;
    private ImageIcon icon;

    private Decorations(String location) {
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
