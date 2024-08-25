import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player extends Sprite {

    private int dx;
    private int dy;
    private List<Bullet> bullets;

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        bullets = new ArrayList<>();
        loadImage("src/player.png");
        getImageDimensions();
        resetState();
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }

        if (x > 800 - width) {
            x = 800 - width;
        }

        if (y > 600 - height) {
            y = 600 - height;
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void fire() {
        bullets.add(new Bullet(x + width, y + height / 2));
    }

    private void resetState() {
        x = 400;
        y = 550;
    }
}
