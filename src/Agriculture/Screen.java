package Agriculture;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Home on 1/30/16.
 */
public class Screen extends JPanel implements MouseMotionListener{
    BufferedImage buffered;


    public Screen()
    {

    }
    public Dimension getPreferredSize()
    {
        return new Dimension(480,480);
    }
    public void paintComponent(Graphics g)
    {
        if(buffered==null)
        {
            buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
        }
        Graphics gBuff = buffered.createGraphics();
        //PAINTING

        //NO MORE PAINTING
        g.drawImage(buffered,0,0,null);
    }

    public void animate(int rate)
    {
        while(true) {
            try {
                Thread.sleep(rate);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
