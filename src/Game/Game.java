package Game;

import Game.Editor.EditorFrame;
import Game.Editor.FileHandler;
import Game.Map.Terrain;
import Game.Entity.Player;
import Game.Entity.Entity;
import Game.File.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Game.World.*;


public class Game {
    
    public static GameFrame frame;
    public static EditorFrame edFrame;
    
    public static double time;
        
    Input input = new Input();
    public static Terrain terrain;
    
    public static ArrayList<Entity> entities = new ArrayList<>();
    
    public static float timePerFrame = 0.0f;
    
    public static FileHandler fileHandler;
    
    public static Player player;
    public static long lastLoopTime;

    public Game() {
        frame = new GameFrame(PANEL_X, PANEL_Y);
        edFrame = new EditorFrame(PANEL_X, PANEL_Y);
        fileHandler = new FileHandler();
        
        frame.panel.addKeyListener(input);
        frame.panel.addMouseMotionListener(input);
        frame.panel.addMouseListener(input);
    }
    
    public void update(double delta) {
        // Loop through all entities, update them or remove them if they are marked to be deleted
        for (int i = 0; i < entities.size(); i++) {
            if(!entities.get(i).delete) {
                entities.get(i).update((float) delta);
            }
            else {
                entities.remove(i);
            }
        }
        time += delta;
    }
    
    public void start(){
        terrain = new Terrain();
        
        player = new Player(0, 0);
        entities.add(player);
        
        frame.setVisible(true);
        
        fileHandler.createFolder();
        
        lastLoopTime = System.nanoTime();
        
        // keep looping round til the game ends
        while (true) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = (updateLength / ((double)OPTIMAL_TIME))/TARGET_FPS;
            
            update(delta);            
            fileHandler.update();

            
            //frame.repaint();
            frame.panel.paintImmediately(0,0,PANEL_X ,PANEL_Y);
            
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
    
    public static void correctTime() {
        lastLoopTime = System.nanoTime();
    }
}
