import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import static java.lang.Character.*;

import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    private Bullets shots;
    private AlienHorde horde;
    private AmmoGen generator;
    private BugMover bugMover;

    private boolean[] keys;
    private BufferedImage back;

    public class AmmoGen extends Thread {
        @Override
        public void run() {
            boolean keepRunning = true;
            while (keepRunning) {
                try {
                    shots.add(new Ammo(ship.getX(), ship.getY()));
                    Thread.currentThread().sleep(400);

                } catch (Exception e) {
                    keepRunning = false;
                }
            }
        }

    }

    ;

    public class BugMover extends Thread {
        @Override
        public void run() {
            boolean keepRunning = true;
            while (keepRunning) {
                horde.moveEmAll();
                try {
                    Thread.currentThread().sleep(400);
                } catch (Exception e) {
                    keepRunning = false;
                }
            }
        }

    }

    ;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        ship = new Ship(300, 500, 1);
        shots = new Bullets();
        horde = new AlienHorde(12);
        this.addKeyListener(this);
        new Thread(this).start();
        setVisible(true);
        bugMover = new BugMover();
        bugMover.start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics buffer = back.createGraphics();

        buffer.setColor(Color.BLUE);
        buffer.drawString("StarFighter ", 25, 50);
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, 800, 600);

        ship.draw(buffer);
        shots.moveEmAll();
        shots.drawEmAll(buffer);
        //    horde.moveEmAll();
        shots.cleanEmUp();
        horde.removeDeadOnes(shots.getList());
        horde.drawEmAll(buffer);

        if (keys[0] && ship.getX() >= -10) {
            ship.move("LEFT");
        }
        if (keys[1] && ship.getX() + ship.getHeight() <= 810) {
            ship.move("RIGHT");
        }
        if (keys[2] && ship.getY() >= -2) {
            ship.move("UP");
        }
        if (keys[3] && ship.getY() + ship.getWidth() <= 578) {
            ship.move("DOWN");
        }
        //     if (keys[4]) {
        //         shots.add(new Ammo(ship.getX(), ship.getY()));
        //     }

        //add code to move Alien.
        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship

        twoDGraph.drawImage(back, null, 0, 0);
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // keys[4] = true;
            if (generator == null) {
                generator = new AmmoGen();
                generator.start();
            }
            //  shots.add(new Ammo(ship.getX(), ship.getY()));
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //     keys[4] = false;
            generator.interrupt();
            generator = null;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

