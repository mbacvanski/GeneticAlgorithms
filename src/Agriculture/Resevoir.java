package Agriculture;

import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Resevoir {
    int volume;
    int originalVolume;
    float volumeTransparency = 1;
    public Resevoir(int volume) {
        this.volume = volume;
        this.originalVolume = volume;

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Screen.SCALE, 600);
        g.setColor(Color.WHITE);
        g.fillRect(Screen.SCALE, 0, 240, Screen.SCALE);
        g.setColor(Color.BLACK);
        g.drawString("V = " + volume + " L", Screen.SCALE + 15, Screen.SCALE - 10);
    }

    public void irrigate(Graphics g, int xStart, int yStart, int width, int height)
    {
        volumeTransparency = volume/originalVolume;
        g.setColor(new Color(0.0f, 0.0f, 1.0f, volumeTransparency));
        g.fillRect(xStart*Screen.SCALE, yStart*Screen.SCALE,width*Screen.SCALE, height*Screen.SCALE);
    }
}
