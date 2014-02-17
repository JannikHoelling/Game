package Game;

import static Game.World.*;

public class Renderer {
    
    public static int offsetX() {
        return - Camera.x + FRAME_X/2;
    }
    
    public static int offsetY() {
        return - Camera.y + FRAME_Y/2;
    }
}
