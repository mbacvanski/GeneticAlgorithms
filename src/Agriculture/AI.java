package Agriculture;

import java.util.ArrayList;

public class AI {
    protected static final int CHROMSIZE = 50000;
    int x;
    int y;
    Tile startTile;
    double fitness;
    Tile destination;
    ArrayList<Direction> chrom = new ArrayList<>(CHROMSIZE);
    private GridOwner gridOwner;

    public AI(Tile startTile, Tile destination, GridOwner gridOwner) {
        this.startTile = startTile;
        this.destination = destination;
        this.gridOwner = gridOwner;
        this.x = startTile.getX();
        this.y = startTile.getY();

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
        double distance = Math.sqrt(Math.pow((destination.getY() - y), 2) + Math.pow(destination.getX() - x, 2));
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
                    return gridOwner.getMap().getTiles().get(y).get(x).isNotWall();
                }
                case DOWN: {
                    return gridOwner.getMap().getTiles().get(y).get(x).isNotWall();
                }
                case LEFT: {
                    return gridOwner.getMap().getTiles().get(y).get(x).isNotWall();
                }
                case RIGHT: {
                    return gridOwner.getMap().getTiles().get(y).get(x).isNotWall();
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
                if (movePossible(direction)) y -= 1;
                break;
            case DOWN:
                if (movePossible(direction)) y += 1;
                break;
            case LEFT:
                if (movePossible(direction)) x -= 1;
                break;
            case RIGHT:
                if (movePossible(direction)) x += 1;
                break;
        }
    }

//    public void draw(Graphics g, boolean fittest) {
//
//        Color transparent = new Color(255, 0, 0, 25);
//        g.setColor(transparent);
//        g.fillRect(x, y, scale, scale);
//        if (fittest) {
//            g.setColor(Color.green);
//            g.drawRect(x, y, scale, scale);
//        }
//        //g.setColor(Color.BLACK);
//        //g.drawRect(x, y, scale, scale);
//    }

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

