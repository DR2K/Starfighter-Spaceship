import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
    private ArrayList<Alien> aliens;

    public AlienHorde(int size) {
        aliens = new ArrayList<>();
        aliens.add(new Alien(0, 0, 20));
        for (int x = 1; x < size && x < 8; x++) {
            aliens.add(new Alien(aliens.get(x - 1).getX() + 100, aliens.get(x - 1).getY(), 20));
        }
        for (int x = 1; x < size && x < 8; x++) {
            aliens.add(new Alien(aliens.get(x - 1).getX() + 100, 60, 20));
        }
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (Alien x : aliens)
            x.draw(window);
    }

    public void moveEmAll() {
        for (Alien x : aliens)
            x.move("");
    }

    public void removeDeadOnes(List<Ammo> shots) {
        ArrayList<Alien> arrayClone = (ArrayList<Alien>) aliens.clone();
        for (Alien x : aliens)
            for (Ammo y : shots)
                if (y.getY() <= x.getY() + x.getHeight() && y.getY() >= x.getY() && y.getX() < x.getX() + x.getWidth() - 30 && y.getX() > x.getX() - 30) {
                    arrayClone.remove(x);
                }
        aliens = arrayClone;
    }

    public String toString() {
        return "" + aliens;
    }
}
