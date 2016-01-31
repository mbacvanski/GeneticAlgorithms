package Agriculture;
import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Resevoir {
    int volume;
    public Resevoir(int volume)
    {
        this.volume = volume;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,Screen.SCALE,600);
        g.setColor(Color.WHITE);
        g.fillRect(Screen.SCALE,0,240,Screen.SCALE);
        g.setColor(Color.BLACK);
        g.drawString("V = "+volume+" L", Screen.SCALE+15,Screen.SCALE-10);
    }
}
