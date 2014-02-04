package Game;

import Game.Entity.Player;
import Game.Entity.Entity;
import Game.Entity.TestObstacle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jannik
 */
public class Game {    
    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    
    public static GameFrame frame;
    
    public static double time;
        
    Input input = new Input();
    
    public static ArrayList<Entity> entities = new ArrayList<>(); 
    
    public static TestObstacle obstacle;
    
    public Game() {
        frame = new GameFrame(1280, 720);
        
        frame.panel.addKeyListener(input);
        frame.panel.addMouseMotionListener(input);
        frame.panel.addMouseListener(input);
    }
    
    public void update(double delta) {
        // Loop through all entities, update them or remove them if they are marked to be deleted
        for (int i = 0; i < entities.size(); i++) {
            if(!entities.get(i).delete) {
                entities.get(i).update(delta);
            }
            else {
                entities.remove(i);
            }
        }
        time += delta;
    }
    
    public void start(){
        
        entities.add(new Player(0, 0));
        obstacle = new TestObstacle(256, 0, 10 , 256);
        entities.add(obstacle);
        
        frame.setVisible(true);
        
        long lastLoopTime = System.nanoTime();
        
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
            
            frame.repaint();
            
            try {
                int sleepTime = (int)(lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000;
                if(sleepTime < 0) sleepTime = 0;
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static int getWidth() {
        return frame.panel.getWidth();
    }
    
    public static int getHeight() {
        return frame.panel.getHeight();
    }
}
