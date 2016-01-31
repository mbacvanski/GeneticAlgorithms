package Agriculture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Home on 1/30/16.
 */
public class Screen extends JPanel implements MouseMotionListener {

    private Car car;
    private Map oldmapthingywrittenonyellowparchmentscrollswhicharefoldedhaphazardlybypirates;
    private MapReader mapReader = new MapReader();

    public Screen() {

        mapReader.readMap(new File("src/Agriculture/map.txt"));
        oldmapthingywrittenonyellowparchmentscrollswhicharefoldedhaphazardlybypirates = mapReader.getMap();

        oldmapthingywrittenonyellowparchmentscrollswhicharefoldedhaphazardlybypirates.setTile(new Tile('1', 12, 14), 12, 14);

        car = new Car(oldmapthingywrittenonyellowparchmentscrollswhicharefoldedhaphazardlybypirates);

        car.getMovin();

    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void paintComponent(Graphics g) {

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.drawRect(x * 40, y * 40, 40, 40);
            }
        }

        ArrayList<Integer> xWall = mapReader.getxWall();
        ArrayList<Integer> yWall = mapReader.getyWall();

        for (int i = 0; i < xWall.size(); i++) {
            int xCor = xWall.get(i);
            int yCor = yWall.get(i);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(xCor * AI.SCALE, yCor * AI.SCALE, AI.SCALE, AI.SCALE);

        }

        car.draw(g);
    }

    public void animate() {
        try {
            Thread.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
