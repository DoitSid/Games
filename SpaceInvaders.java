import javax.swing.JFrame;

public class SpaceInvaders extends JFrame {

    public SpaceInvaders() {
        add(new Sid());
        setTitle("Space Invaders");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new SpaceInvaders().setVisible(true);
    }
}
