/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Entity;

import Game.Enums.Interactives;
import Game.Game;
import Game.Renderer;
import static Game.World.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jannik
 */
public final class Teleporter extends RigidBody {

    private final BufferedImage image;
    private Teleporter teleporter;

    private boolean set = true;
    private int number;
    private Integer intNum;

    public Teleporter(float x, float y, Teleporter teleporter) {
        super(x, y);
        image = Interactives.TELEPORT.getImage();
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
        Game.player.setX(teleporter.x + 64);
        Game.player.setY(teleporter.y);
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
