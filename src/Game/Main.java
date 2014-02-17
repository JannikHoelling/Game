package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import static Game.World.*;


public class Main {

    /**
     * @version Alpha_1.0
     */
    
    public static Game game;
    static Dimension dim = new Dimension();
    static Toolkit tk = Toolkit.getDefaultToolkit();
    
    public static void main(String[] args) {   
        initVar();
        game = new Game();
    }
    
    public static void initVar() {
        //initialize var in Game.World
        dim = tk.getScreenSize();
        FRAME_X = dim.width;
        FRAME_Y = dim.height; 
        
        EDITOR_X = FRAME_X/7;
        EDITOR_Y = FRAME_Y;
        
        GAME_X = FRAME_X-EDITOR_X;
        GAME_Y = FRAME_Y;
    }
}
