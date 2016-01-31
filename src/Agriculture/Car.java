package Agriculture;

import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Car {
    private int x;
    private int y;

    private Map map;

    private PathFinder guidance;

    public Car(Map map) {
        System.out.println("Car.Car");
        this.map = map;
        guidance = new PathFinder(map);
    }

    public void draw(Graphics g) {
        System.out.println("Car.draw");
        this.x = guidance.getFittest().getX();
        this.y = guidance.getFittest().getY();

        g.fillOval(x * AI.SCALE, y * AI.SCALE, AI.SCALE, AI.SCALE);
    }

    public void getMovin() {
        System.out.println("Car.getMovin");
        guidance.startGuidance();
        this.x = guidance.getFittest().getX();
        this.y = guidance.getFittest().getY();
    }

}
