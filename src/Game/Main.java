package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import static Game.World.*;

public class Main {

    /**
     * @version Alpha_1.0
     */
    public static Frame frame;
    static Dimension dim = new Dimension();
    static Toolkit tk = Toolkit.getDefaultToolkit();

    public static void main(String[] args) {
        initVar();
        
        if(args.length == 0 || !args[0].equals("-editor")) {
            frame = new Frame("0");
        } else {
            frame = new Frame(args[0]);
        }
    }

    public static void initVar() {
        //initialize var in Game.World
        dim = tk.getScreenSize();
        FRAME_X = dim.width;
        FRAME_Y = dim.height;

        EDITOR_X = FRAME_X / 7;
        EDITOR_Y = FRAME_Y;

        GAME_X = FRAME_X - EDITOR_X;
        GAME_Y = FRAME_Y;

        HALF_FRAME_X = FRAME_X / 2;
        HALF_FRAME_Y = FRAME_Y / 2;
    }
}
