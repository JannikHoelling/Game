package Game.Entity;

import Game.Enums.Interactives;
import Game.Game;
import Game.Renderer;
import static Game.World.*;
import java.awt.Graphics2D;


public final class Teleporter extends RigidBody {

    private Teleporter teleporter;

    private boolean set = true;
    private int number;
    private Integer intNum;
    
    public long nextTeleport;
    private long coolDown = 2000;

    public Teleporter(float x, float y, Teleporter teleporter) {
        super(x, y);
        this.image = Interactives.TELEPORT.getImage();
        this.teleporter = teleporter;

        this.setSet();

        Game.teleporter.add(this);
    }

    public void setTarget(Teleporter teleporter) {
        this.teleporter = teleporter;
        this.teleporter.setSet();
        this.teleporter.setNumber(this.getNumber());
    }

    
    @Override
    public void render(Graphics2D g) {
        intNum = new Integer(number);
        g.drawImage(image, (int) x + Renderer.offsetX(), (int) y + Renderer.offsetY(), TILE_SIZE, TILE_SIZE, null);
        g.drawString(intNum.toString(), (int) x + Renderer.offsetX(), (int) y + Renderer.offsetY());
    }

    
    @Override
    public void onCollision() {
        if(nextTeleport < System.currentTimeMillis()) {
            Game.player.setX(teleporter.x + HALF_TILE);
            Game.player.setY(teleporter.y + HALF_TILE);
            Game.player.setDX(0);
            Game.player.setDY(0);
            
            teleporter.nextTeleport = System.currentTimeMillis() + coolDown;
            nextTeleport = System.currentTimeMillis() + coolDown;
        }
    }

    public void setSet() {
        this.set = !set;
    }

    public boolean getSet() {
        return set;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public Teleporter get2ndTel() {
        return this.teleporter;
    }

}
