import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Sid extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private List<Enemy> enemies;
    private boolean inGame;

    public Sid() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        inGame = true;

        player = new Player();
        enemies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(50 + i * 60, 50));
        }

        timer = new Timer(15, this);
        timer.start();

        addKeyListener(new TAdapter());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        List<Bullet> bullets = player.getBullets();

        for (Bullet bullet : bullets) {
            if (bullet.isVisible()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.isVisible()) {
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 14));
        g.drawString("Enemies: " + enemies.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {
        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message, (getWidth() - fm.stringWidth(message)) / 2, getHeight() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();

        updatePlayer();
        updateBullets();
        updateEnemies();

        checkCollisions();

        repaint();
    }

    private void inGame() {
        if (!inGame) {
            timer.stop();
        }
    }

    private void updatePlayer() {
        if (player.isVisible()) {
            player.move();
        }
    }

    private void updateBullets() {
        List<Bullet> bullets = player.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);

            if (bullet.isVisible()) {
                bullet.move();
            } else {
                bullets.remove(i);
            }
        }
    }

    private void updateEnemies() {
        if (enemies.isEmpty()) {
            inGame = false;
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if (enemy.isVisible()) {
                enemy.move();
            } else {
                enemies.remove(i);
            }
        }
    }

    private void checkCollisions() {
        List<Bullet> bullets = player.getBullets();

        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.setVisible(false);
                    enemy.setVisible(false);
                }
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.getBounds().intersects(player.getBounds())) {
                player.setVisible(false);
                enemy.setVisible(false);
                inGame = false;
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
