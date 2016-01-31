package Agriculture;

import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Car {
    private int x;
    private int y;
    private PathFinder guidance = new PathFinder();

    public Car() {
        System.out.println("Car.Car");
        move();
        this.x = guidance.fittest.getX();
        this.y = guidance.fittest.getY();
    }

    public void draw(Graphics g) {
        g.fillOval(x, y, 32, 32);
    }

    private void move() {
        System.out.println("Car.move");
        guidance.startGuidance();
    }

}
