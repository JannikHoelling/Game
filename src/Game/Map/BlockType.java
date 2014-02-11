/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jannik
 */
public enum BlockType {

    AIR("res/air.png"), DIRT("res/dirt.png");

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
