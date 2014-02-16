package Game.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public enum BlockType {

    AIR("res\\air.png"), DIRT("res\\dirt.png"), DIRT_FULL("res\\dirt_full.png"), GRASS("res\\grass.png");

    public final String location;

    public BufferedImage image;

    private BlockType(String location) {
        
        this.location = location;
        
        try {
            image = ImageIO.read(new File(location));
        } catch (IOException ex) {
            Logger.getLogger(BlockType.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
