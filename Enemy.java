import javax.swing.ImageIcon;

public class Enemy extends Sprite {

    private int direction = 1;

    public Enemy(int x, int y) {
        initEnemy(x, y);
    }

    private void initEnemy(int x, int y) {
        this.x = x;
        this.y = y;
        loadImage("src/enemy.png");
        getImageDimensions();
    }

    public void move() {
        x += direction;

        if (x <= 0 || x >= 800 - width) {
            direction *= -1;
            y += 20;
        }
    }
}
