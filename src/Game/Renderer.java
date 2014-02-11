/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

/**
 *
 * @author Jannik
 */
public class Renderer {
    
    public static int offsetX() {
        return - Camera.x + World.PANEL_X/2;
    }
    
    public static int offsetY() {
        return - Camera.y + World.PANEL_Y/2;
    }
}
