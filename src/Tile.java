import java.awt.*;

public class Tile {
    private int x;
    private int y;

    private boolean isWall;

    private int scale;

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

    public void getOccupyingAI(AI[] ai) {
        for (AI each : ai) {
            if (each.getFitness() == 0) {

            } else {
                System.out.println("Fitness: " + each.getFitness());
            }
        }
    }
    public boolean isWall() {
        return isWall;
    }
}
