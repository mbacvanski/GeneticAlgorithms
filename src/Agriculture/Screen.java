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
    public static final int SCALE = 24;
    ArrayList<Crop> crops = new ArrayList<Crop>();
    Resevoir resTest = new Resevoir(1200000000);

    public Screen()
    {
        //
        crops.add(new Crop("Barley",200,0.3,4,3));
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
        gBuff.setColor(new Color(200,120,25));
        gBuff.fillRect(0,0,getWidth(),getHeight());
        for(int y = 0; y<20; y++)
        {
            for(int x = 0; x<20; x++)
            {
                gBuff.setColor(Color.black);
                gBuff.drawRect(x*24,y*24,24,24);
            }
        }
        for(Crop c : crops)
        {
            //System.out.println(c);
            c.plant(4,3);
            c.draw(gBuff);
        }
        resTest.draw(gBuff);
        //test.draw(gBuff);
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
