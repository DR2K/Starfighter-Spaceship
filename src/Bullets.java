import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
    private ArrayList<Ammo> ammo;

    public Bullets() {
        ammo = new ArrayList<>();
    }

    public void add(Ammo al) {
        ammo.add(al);
    }

    //post - draw each Ammo
    public void drawEmAll(Graphics window) {
        for (Ammo x : ammo)
            x.draw(window);
    }

    public void moveEmAll() {
        for (Ammo x : ammo) {
            x.move("");
        }
    }

    public void cleanEmUp() {
        ArrayList<Ammo> ammoCopy = (ArrayList<Ammo>) ammo.clone();
        for (Ammo x : ammoCopy)
            if (x.getY() <= -5)
                ammo.remove(x);
    }

    public List<Ammo> getList() {
        return ammo;
    }

    public String toString() {
        return ammo.toString();
    }
}
