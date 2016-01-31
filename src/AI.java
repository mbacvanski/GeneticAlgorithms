import java.awt.*;
import java.util.ArrayList;

public class AI {
    protected static final int CHROMSIZE = 50000;
    int x;
    int y;
    int scale;
    double fitness;
    ArrayList<Direction> chrom = new ArrayList<>(CHROMSIZE);
    private double distance;
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
        for (int i = 0; i < chrom.size(); i++) {
            move(chrom.get(i));
        }
        //Distance from bottom right corner of screen, exit.
        distance = Math.sqrt(Math.pow((screenSize.getHeight() - scale - y), 2) + Math.pow(screenSize.getWidth() - scale - x, 2)) / scale;
//        double distance = Math.sqrt(Math.pow((224 - y), 2) + Math.pow(224 - x, 2)) / scale;
        if (distance == 0) {
            fitness = 1;
        }
        fitness = 1 / distance;
        System.out.println("fitness = " + fitness);
    }

    public double getFitness() {
        return fitness;
    }

    public boolean movePossible(int passX, int passY, Direction direction) {

        try {
            switch (direction) {
                case UP: {
                    return gridOwner.getGrid()[(passY - scale) / scale][passX / scale].isNotWall();
                }
                case DOWN: {
                    return gridOwner.getGrid()[(passY + scale) / scale][passX / scale].isNotWall();
                }
                case LEFT: {
                    return gridOwner.getGrid()[passY / scale][(passX - scale) / scale].isNotWall();
                }
                case RIGHT: {
                    return gridOwner.getGrid()[passY / scale][(passX + scale) / scale].isNotWall();
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
                if (movePossible(x, y, direction)) y -= scale;
                break;
            case DOWN:
                if (movePossible(x, y, direction)) y += scale;
                break;
            case LEFT:
                if (movePossible(x, y, direction)) x -= scale;
                break;
            case RIGHT:
                if (movePossible(x, y, direction)) x += scale;
                break;
        }
    }

    public void draw(Graphics g, boolean fittest) {
        int tempMovingX = 0;
        int tempMovingY = 0;
        g.setColor(Color.RED);
        g.fillRect(tempMovingX, tempMovingY, scale, scale);
        for (Direction each : chrom) {
            System.out.println();
            switch (each) {
                case UP: {
                    if (movePossible(tempMovingX, tempMovingY, each)) g.fillRect(tempMovingX, tempMovingY += scale, scale, scale);
                    break;
                }
                case DOWN: {
                    if (movePossible(tempMovingX, tempMovingY, each)) g.fillRect(tempMovingX, tempMovingY -= scale, scale, scale);
                    break;
                }
                case LEFT: {
                    if (movePossible(tempMovingX, tempMovingY, each)) g.fillRect(tempMovingX -= scale, tempMovingY, scale, scale);
                    break;
                }
                case RIGHT: {
                    if (movePossible(tempMovingX, tempMovingY, each)) g.fillRect(tempMovingX += scale, tempMovingY, scale, scale);
                    break;
                }
            }
        }

//        Color transparent = new Color(255, 0, 0, 25);
//        g.setColor(transparent);
//        g.fillRect(x, y, scale, scale);
//        if (fittest) {
//            g.setColor(Color.green);
//            g.drawRect(x, y, scale, scale);
//        }
        //g.setColor(Color.BLACK);
        //g.drawRect(x, y, scale, scale);
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

    public double getDistance() {
        return distance;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}
}

