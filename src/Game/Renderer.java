package Game;

import static Game.World.*;


public class Renderer {
    
    public static int offsetX() {
        return - Camera.x + PANEL_X/2;
    }
    
    public static int offsetY() {
        return - Camera.y + PANEL_Y/2;
    }
}
