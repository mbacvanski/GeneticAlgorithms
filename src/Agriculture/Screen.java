package Agriculture;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Home on 1/30/16.
 */
public class Screen extends JPanel{
    BufferedImage buffered;
    public Screen()
    {

    }
    public Dimension getPreferredSize()
    {
        return new Dimension(800,600);
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
        try {
            Thread.sleep(rate);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
