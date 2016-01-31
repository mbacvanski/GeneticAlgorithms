import java.awt.*;
import java.util.ArrayList;

public class AI {
    protected static final int CHROMSIZE = 50000;

    int x;
    int y;
    int scale;
    double fitness;
    ArrayList<Direction> chrom = new ArrayList<>(CHROMSIZE);
    private Dimension screenSize;
    private GridOwner gridOwner;

    public AI(int x, int y, int scale, Dimension screenSize, GridOwner gridOwner) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        this.screenSize = screenSize;
        this.gridOwner = gridOwner;

        for (int i = 0; i < CHROMSIZE; i++) {
            chrom.add(getRandomDirection());
        }
    }

    public static Direction getRandomDirection() {
        int randInt = (int) (Math.random() * 4);

        if (randInt == 0) return Direction.UP;
        if (randInt == 1) return Direction.DOWN;
        if (randInt == 2) return Direction.LEFT;
        if (randInt == 3) return Direction.RIGHT;
        throw new RuntimeException("Could not find matching enum.");
    }

    public void run() {
        System.out.println("AI.run");
        for (int i = 0; i < chrom.size(); i++) {
            move(chrom.get(i));
        }
        //Distance from bottom right corner of screen, exit.
        double distance = Math.sqrt(Math.pow((screenSize.getHeight() - y), 2) + Math.pow(screenSize.getWidth() - x, 2)) / scale;
//        double distance = Math.sqrt(Math.pow((224 - y), 2) + Math.pow(224 - x, 2)) / scale;
        if (distance == 0) {
            fitness = 1;
        }
        fitness = 1 / distance;
    }

    public double getFitness() {
        return fitness;
    }

    public boolean movePossible(Direction direction) {

        try {
            switch (direction) {
                case UP: {
                    return gridOwner.getGrid()[(y - scale) / scale][x / scale].isNotWall();
                }
                case DOWN: {
                    return gridOwner.getGrid()[(y + scale) / scale][x / scale].isNotWall();
                }
                case LEFT: {
                    return gridOwner.getGrid()[y / scale][(x - scale) / scale].isNotWall();
                }
                case RIGHT: {
                    return gridOwner.getGrid()[y / scale][(x + scale) / scale].isNotWall();
                }
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) { //If we're at an edge
            //Swallow aioobe: gulp
        }

        return false;

    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:

                if (movePossible(direction)) y -= scale;

                break;
            case DOWN:

                if (movePossible(direction)) y += scale;
                break;
            case LEFT:
                if (movePossible(direction)) x -= scale;
                break;
            case RIGHT:
                if (movePossible(direction)) x += scale;
                break;
        }
    }

    public void draw(Graphics g, boolean fittest) {

        Color transparent = new Color(255, 0, 0, 25);
        g.setColor(transparent);
        g.fillRect(x, y, scale, scale);
        if (fittest) {
            g.setColor(Color.green);
            g.drawRect(x, y, scale, scale);
        }
        //g.setColor(Color.BLACK);
        //g.drawRect(x, y, scale, scale);
    }

    public void drawFittestPath(Graphics g, AI fittest)
    {
        int currentX = 0;
        int currentY = 0;
        g.setColor(Color.green);
        g.fillRect(0,0,scale,scale);
        for(int i = 0; i<fittest.getChrom().size(); i++)
        {
            g.setColor(Color.green);
            if(fittest.getChrom().get(i)==Direction.UP&&movePossible(Direction.UP)){currentY-=scale;}
            else if(fittest.getChrom().get(i)==Direction.DOWN&&movePossible(Direction.DOWN)){currentY+=scale;}
            else if(fittest.getChrom().get(i)==Direction.RIGHT&&movePossible(Direction.RIGHT)){currentX+=scale;}
            else if(fittest.getChrom().get(i)==Direction.LEFT&&movePossible(Direction.LEFT)){currentX-=scale;}
            g.fillRect(x,y,scale,scale);
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ArrayList<Direction> getChrom() {
        return chrom;
    }

    public void setChrom(ArrayList<Direction> chrom) {
        this.chrom = chrom;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}
}

