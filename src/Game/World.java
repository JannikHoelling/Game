package Game;


public class World {
    
    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    
    public static int FRAME_X = 0; //x-size windows
    public static int FRAME_Y = 0; //y-size windows
    public static int EDITOR_X = 0; //x-size editorpanel
    public static int EDITOR_Y = 0; //x-size editorpanel
    public static int GAME_X = 0; //x-size gamepanel
    public static int GAME_Y = 0; //x-size gamepanel
    
    public static final int TILE_SIZE = 32;
    public static final int HALF_TILE = TILE_SIZE/2;
    public static final int WORLD_X = 64; //number of blocks
    public static final int WORLD_Y = 64; //number of blocks
    
    public static String FILE = "save"; //current savefile
    public static String PATH = "save\\";
    public static final String ENDING = ".sav"; //extensions of savefile
    
    public static final String[] SELECTIONS = {"Blocks", "Interactives", "Decoration", "Player"}; 

}
