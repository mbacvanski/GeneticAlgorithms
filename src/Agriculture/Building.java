package Agriculture;

import java.awt.*;

/**
 * Created by Home on 1/30/16.
 */
public class Building {
    public enum Type{house,center,hall};
    int x, y;
    public Type buildingType;
    int priority;
    public Building(Type type)
    {
        this.buildingType = type;
        if(buildingType==Type.house)
        {
            priority = 1;
        }
        else if(buildingType==Type.hall)
        {
            priority = 4;
        }
        else if(buildingType==Type.center)
        {
            priority = 10;
        }
        else
        {
            priority = 0;
        }
    }
    public void build(int x, int y, Graphics g)
    {
        switch(buildingType) {
            case house:
                g.setColor(Color.orange);
                g.fillRect(x,y,25,25);
                break;
            case center:
                g.setColor(Color.white);
                g.fillRect(x,y,50,50);
                break;
            case hall:
                g.setColor(Color.lightGray);
                g.fillRect(x,y,50,25);
                break;
            default:
                break;
        }
    }

}
