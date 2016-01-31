import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Home on 1/30/16.
 */
public class Screen extends JPanel{
    BufferedImage buffered;
    public static final int SCALE = 24;
    Crop test = new Crop("Barley",200,0.3);
    Resevoir resTest = new Resevoir(6600);
    public Screen()
    {
        test.plant(2,3);
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
        resTest.draw(gBuff);
        test.draw(gBuff);
        //NO MORE PAINTING
        g.drawImage(buffered,0,0,null);
    }

    public void animate(int rate)
    {
        try {
            Thread.sleep(rate);
            repaint();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
