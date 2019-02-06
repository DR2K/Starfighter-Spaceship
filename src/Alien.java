import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {
    private int speed;
    private Image image;

    public Alien() {
        this(0, 0, 30, 30, 0);
    }

    public Alien(int x, int y) {
        this(x, y, 50, 50, 1);
    }

    public Alien(int x, int y, int s) {
        this(x, y, 50, 50, s);
    }

    public Alien(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
        try {
            URL url = getClass().getResource("alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        int x = (int)(Math.random()*5)+1;
        if (x==1 && getX() >= 10)
            setX(getX() - speed);
        if (x==2 && getX() + getHeight() <= 790)
            setX(getX() + speed);
     //   if (x==3 && getY() >= 2)
     //       setY(getY() - speed);
        if (x==4 && getY() + getWidth() <= 500)
            setY(getY() + speed);
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return "";
    }
}
