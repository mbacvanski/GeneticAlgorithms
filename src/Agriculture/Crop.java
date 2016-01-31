package Agriculture;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Home on 1/30/16.
 */
public class Crop {
    String type;
    ArrayList<Integer> xs = new ArrayList<Integer>();
    ArrayList<Integer> ys = new ArrayList<Integer>();
    int requiredWater;
    double soilAcidity;

    public Crop(String type, int volWater, double soilAcidity) {
        this.type = type;
        requiredWater = volWater;
        this.soilAcidity = soilAcidity;
    }

    public void plant(int x, int y) {
        xs.add(x * Screen.SCALE);
        ys.add(y * Screen.SCALE);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < xs.size(); i++) {
            g.setColor(Color.green);
            g.fillOval(xs.get(i), ys.get(i), Screen.SCALE, Screen.SCALE);
        }
    }

}
