import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SnakeGame3 extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 1500;
    private static final int SCREEN_HEIGHT = 800;
    private static final int DOT_SIZE = 20;
    private static final int ALL_DOTS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private static final int DELAY = 50;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private int dots;
    private int appleX;
    private int appleY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;

    public SnakeGame3() {
        initGame();
    }

    private void initGame() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);

        addKeyListener(new MyKeyAdapter());

        initSnake();
        placeApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initSnake() {
        dots = 2;

        for (int i = 0; i < dots; i++) {
            x[i] = 60 - i * DOT_SIZE;
            y[i] = 60;
        }
    }

    private void placeApple() {
        int r = (int) (Math.random() * (SCREEN_WIDTH / DOT_SIZE)) * DOT_SIZE;
        appleX = r;

        r = (int) (Math.random() * (SCREEN_HEIGHT / DOT_SIZE)) * DOT_SIZE;
        appleY = r;
    }

    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            placeApple();
        }
    }

    private void checkCollision() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        }

        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over ";
        String msg1="Made By Siddhi Satam";

        Font font = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (SCREEN_WIDTH - metrics.stringWidth(msg)) / 2, SCREEN_HEIGHT / 2);
        Font font1 = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics metrics1 = getFontMetrics(font1);

        g.setColor(Color.PINK);
        g.setFont(font1);
        g.drawString(msg1, (SCREEN_WIDTH - metrics1.stringWidth(msg1)) / 2, (int) (SCREEN_HEIGHT / 1.5));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            drawSnake(g);
            drawApple(g);
        } else {
            gameOver(g);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < dots; i++) {
            g.setColor(Color.green);
            g.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !rightDirection) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_RIGHT && !leftDirection) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_UP && !downDirection) {
                if (x[0] != x[1]) {  // Ensure zigzag movement
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
            }

            if (key == KeyEvent.VK_DOWN && !upDirection) {
                if (x[0] != x[1]) {  // Ensure zigzag movement
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(new SnakeGame3());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
