import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x, y;
    protected int width, height;
    protected boolean visible;
    protected Image image;

    public Sprite() {
        visible = true;
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(getClass().getResource("/" + imageName));
        image = ii.getImage();
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
