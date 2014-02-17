package Game.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public enum BlockType {

    AIR("res\\block\\air.png"), DIRT("res\\block\\dirt.png"), DIRT_FULL("res\\block\\dirt_full.png"), 
    GRASS("res\\block\\grass.png");

    private final String location;
    private BufferedImage image;

    private BlockType(String location) {
        this.location = location;
        
        try {
            image = ImageIO.read(new File(location));
        } catch (IOException ex) {
            Logger.getLogger(BlockType.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public BufferedImage getImage() {
        return image;
    }

}
