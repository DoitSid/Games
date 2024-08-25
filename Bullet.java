import javax.swing.ImageIcon;

public class Bullet extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int BULLET_SPEED = 4;

    public Bullet(int x, int y) {
        initBullet(x, y);
    }

    private void initBullet(int x, int y) {
        loadImage("src/bullet.png");
        getImageDimensions();
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += BULLET_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}
