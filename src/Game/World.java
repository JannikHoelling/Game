package Game;


public class World {
    
    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    
    public static int PANEL_X = 1280;
    public static int PANEL_Y = 720;
    
    public static final int TILE_SIZE = 32;
    public static final int HALF_TILE = TILE_SIZE/2;
    public static final int WORLD_X = 64;
    public static final int WORLD_Y = 64;
    
    public static String FILE = "";
    public static final String ENDING = ".sav";
    
    
    
    public static void setPanel(int x, int y) {
        World.PANEL_X = x;
        World.PANEL_Y = y;
    }
}
