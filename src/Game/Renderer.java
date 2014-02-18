package Game;

import static Game.World.*;

public class Renderer {
    
    public static int offsetX() {
        return - Camera.x + HALF_FRAME_X;
    }
    
    public static int offsetY() {
        return - Camera.y + HALF_FRAME_Y;
    }
}
