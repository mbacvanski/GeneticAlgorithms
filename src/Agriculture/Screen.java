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
    Building townHall;
    Building home;
    Building shoppingCenter;

    public Screen()
    {
        home = new Building(Building.Type.house);
        townHall = new Building(Building.Type.hall);
        shoppingCenter = new Building(Building.Type.center);
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(800,800);
    }
    public void paintComponent(Graphics g)
    {
        if(buffered==null)
        {
            buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
        }
        Graphics gBuff = buffered.createGraphics();
        //PAINTING
        gBuff.setColor(Color.darkGray);
        gBuff.fillRect(0,0,800,800);
        home.build(200, 200,gBuff);
        shoppingCenter.build(600,200,gBuff);
        townHall.build(200,600,gBuff);
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
