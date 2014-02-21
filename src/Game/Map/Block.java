package Game.Map;

import Game.Enums.*;
import java.awt.Image;


public class Block {   
    public BlockType blockType = BlockType.AIR;
    
    public Image getImage() {
        return blockType.getImage();
    }
    
    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }
}
