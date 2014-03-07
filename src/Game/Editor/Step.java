package Game.Editor;

import Game.Entity.Entity;
import Game.Enums.BlockType;
import Game.Frame;
import Game.Map.Terrain;

public class Step {

    private final int x, y;
    private final BlockType type1, type2;
    private final Entity en1;

    public Step(int x, int y, BlockType type1, BlockType replaceType, Entity replaceEntity) {
        this.x = x;
        this.y = y;
        this.type1 = type1;
        this.type2 = replaceType;
        this.en1 = replaceEntity;
        
        Frame.input.setPlaced();
    }

    public void set() {
        if(type2 != null) {
            if (type2.equals(Terrain.terrain[x][y].blockType)) {
                Terrain.terrain[x][y].blockType = type1;
            } else {
                Terrain.terrain[x][y].blockType = type2;
            }
        } else {
            if(Frame.input.isEntityOnPosition(x, y)) {
                Terrain.terrain[x][y].blockType = type1;
                System.out.println("hi");
            }
        }
        Frame.gamePanel.repaint();
    }
}
