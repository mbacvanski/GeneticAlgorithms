package Agriculture;

import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Road {

    private int startx = 0;
    private int starty = 0;
    private int endx = 0;
    private int endy = 0;

    public Road(int startx, int starty, int endx, int endy) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(171, 186, 175));
        g.drawLine(startx, starty, endx, endy);
    }

    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getEndx() {
        return endx;
    }

    public void setEndx(int endx) {
        this.endx = endx;
    }

    public int getEndy() {
        return endy;
    }

    public void setEndy(int endy) {
        this.endy = endy;
    }
}
