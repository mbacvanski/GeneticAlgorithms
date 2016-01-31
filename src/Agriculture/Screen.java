package Agriculture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Home on 1/30/16.
 */
public class Screen extends JPanel implements MouseMotionListener {

    private Car car;

    public Screen() {
        car = new Car();
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    public void paintComponent(Graphics g) {
        System.out.println("Screen.paintComponent");
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
