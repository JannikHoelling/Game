package Game;

import Game.Editor.EditorFrame;
import Game.Editor.FileHandler;
import Game.Map.Terrain;
import Game.Entity.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Game.World.*;
import javax.swing.JFrame;


public class Game implements Runnable {
    
    public static ArrayList<Entity> entities = new ArrayList<>(); 
    public static ArrayList<RigidBody> collider = new ArrayList<>();
    
    public static Terrain terrain;
    public static Player player;
    private Input input = new Input();
    
    public static EditorFrame frame;
    public static FileHandler fileHandler;
    
    public static long lastLoopTime;
    public static float timePerFrame = 0.0f; 
    public static double time;
    
    private static boolean isRunning = false;
    
    public Game() {
        frame = new EditorFrame();
        terrain = new Terrain();
        fileHandler = new FileHandler();
        player = new Player(0, 0);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximize frame
        //entities.add(player);
        FileHandler.createFolder(); //create save-Folder

        frame.setVisible(true);
    }
    
    public void update(double delta) {
        // Loop through all entities, update them or remove them if they are marked to be deleted
        for (int i = 0; i < entities.size(); i++) {
            
            Entity e = entities.get(i);
            
            if(!e.isAlive())
                e.update((float) delta);
            else
                entities.remove(i);
        }
        time += delta;
    }
    
    @Override
    public void run(){
        fileHandler.save();
        // keep looping round til the game ends
        while (isRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            
            frame.panel.requestFocusInWindow(); //focus on game panel
            fileHandler.update(); //check keyinput in fileHandler
            
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = (updateLength / ((double)OPTIMAL_TIME))/TARGET_FPS;
            
            update(delta);             

            frame.panel.paintImmediately(0,0,GAME_X ,GAME_Y);
            
            timePerFrame = ((float)(System.nanoTime() - lastLoopTime) / 1000000);
            
            try {
                int sleepTime = (int)(lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000;
                if(sleepTime < 0) sleepTime = 0;
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start() {
        lastLoopTime = System.nanoTime();
        frame.panel.addKeyListener(input);
        frame.panel.addMouseMotionListener(input);
        frame.panel.addMouseListener(input);

        isRunning = true;
        new Thread(this).start();
    }
    
    public void stop() {
        isRunning = false;
    }
    
    public static boolean isRunning() {
        return isRunning;
    }
    
    public static void resetFrameTime() {
        lastLoopTime = System.nanoTime();
    }
}
