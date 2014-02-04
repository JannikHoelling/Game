package Game;

import javax.swing.*;

/**
 *
 * @author Jannik
 */
public class GameFrame extends JFrame {

    public GamePanel panel = new GamePanel();

    public GameFrame(int x, int y) {
        setTitle("Game");
        setSize(x, y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel);
    }
}
