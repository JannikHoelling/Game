package Game.Map;

import Game.Enums.BlockType;
import java.awt.image.BufferedImage;


public class Block {
    public BufferedImage image = BlockType.AIR.getImage();
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
