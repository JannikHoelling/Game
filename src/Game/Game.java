package Game;

import Game.Editor.FileHandler;
import Game.Map.Terrain;
import Game.Entity.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Game.World.*;

public class Game implements Runnable {

    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<RigidBody> rigidBodies = new ArrayList<>();
    public static ArrayList<Teleporter> teleporter = new ArrayList<>();

    public static Terrain terrain;
    public static Player player;

    public static FileHandler fileHandler;

    public static long lastLoopTime;
    public static float timePerFrame = 0.0f;
    public static double time;

    private static boolean ready = true;

    private static boolean isRunning = false;
    static Teleporter t1;
    static Teleporter t2;

    public Game() {
        terrain = new Terrain();
        fileHandler = new FileHandler();
        player = new Player(0, 0);
        //entities.add(player);

        //t1 = new Teleporter(255, 255, null);
        //t2 = new Teleporter(500, 255, t1);
        //t1.setTarget(t2);
    }

    public void update(double delta) {
        // Loop through all entities, update them or remove them if they are marked to be deleted
        for (int i = 0; i < entities.size(); i++) {

            Entity e = entities.get(i);

            if (!e.isAlive()) {
                e.update((float) delta);
            } else {
                e.onDestroy();
                entities.remove(i);
            }
        }

        for (int i = 0; i < rigidBodies.size(); i++) {
            RigidBody e = rigidBodies.get(i);

            if (e.getBounds().intersects(player.getBounds()) && e != player) {
                e.onCollision();
            }

        }

        time += delta;
    }

    @Override
    public void run() {
        fileHandler.save();
        // keep looping round til the game ends
        while (isRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop

            Frame.gamePanel.requestFocusInWindow(); //focus on game panel
            //fileHandler.update(); //check keyinput in fileHandler

            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = (updateLength / ((double) OPTIMAL_TIME)) / TARGET_FPS;

            update(delta);

            Frame.gamePanel.repaint();

            timePerFrame = ((float) (System.nanoTime() - lastLoopTime) / 1000000);

            try {
                int sleepTime = (int) (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (sleepTime < 0) {
                    sleepTime = 0;
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void start() {
        Input.setNull();
        Frame.disableClickable();
        lastLoopTime = System.nanoTime();

        isRunning = true;
        new Thread(this).start();

    }

    public void stop() {
        Frame.enableClickable();
        isRunning = false;
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static void resetFrameTime() {
        lastLoopTime = System.nanoTime();
    }

    public static boolean getReady() {
        return ready;
    }

    public static void setReady(boolean ready) {
        Game.ready = ready;
    }
}
