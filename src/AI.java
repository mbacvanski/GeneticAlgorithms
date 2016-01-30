import java.awt.*;

public class AI {
    int x;
    int y;
    int scale;
    double fitness;
    Tile[][] grid;
    Direction[] chrom;
    private Dimension screenSize;

    public AI(int x, int y, int scale, Tile[][] grid, Dimension screenSize) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.chrom = new Direction[32];
        this.grid = grid;

        this.screenSize = screenSize;

        for (int i = 0; i < chrom.length; i++) {
            chrom[i] = getRandomDirection();
        }
//        chrom = new char[]{'D', 'D', 'D', 'R', 'R', 'R', 'R', 'D', 'D', 'D', 'D', 'R', 'R', 'R', 'R', 'U', 'U', 'R', 'R', 'R', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'R', 'R', 'R'};
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
        for (int i = 0; i < chrom.length; i++) {
            move(chrom[i]);
            System.out.println("Moved: " + chrom[i]);
        }
        //Distance from bottom right corner of screen, exit.
        double distance = Math.sqrt(Math.pow((screenSize.getHeight() - y), 2) + Math.pow(screenSize.getWidth() - x, 2)) / scale;
//        double distance = Math.sqrt(Math.pow((224 - y), 2) + Math.pow(224 - x, 2)) / scale;
        if (distance == 0) {
            fitness = 1;
        }
        fitness = 1 / distance;
        System.out.println(fitness);
    }

    public boolean movePossible(Direction direction) {

        try {
            switch (direction) {
                case UP: {
                    return !grid[x / scale][(y - scale) / scale].isWall();
                }
                case DOWN: {
                    return !grid[x / scale][(y + scale) / scale].isWall();
                }
                case LEFT: {
                    return !grid[(x - scale) / scale][y / scale].isWall();
                }
                case RIGHT: {
                    return !grid[(x + scale) / scale][y / scale].isWall();
                }
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
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

    public void draw(Graphics g) {
        Color transparent = new Color(255, 0, 0, 25);
        g.setColor(transparent);
        g.fillRect(x, y, scale, scale);
        //g.setColor(Color.BLACK);
        //g.drawRect(x, y, scale, scale);
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}


}

