import java.awt.Color;
import java.awt.Graphics;

public class Tile {
    int x;
    int y;

    boolean isWall;

    int scale;

    public Tile(int x, int y, int scale, boolean isWall){
        this.x = x;
        this.y = y;

        this.isWall = isWall;
        this.scale = scale;
    }

    public void draw(Graphics g){
        if(isWall){
            g.setColor(Color.DARK_GRAY);
        }else{
            g.setColor(Color.GRAY);
        }
        g.fillRect(x, y, scale, scale);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, scale, scale);
    }
}
