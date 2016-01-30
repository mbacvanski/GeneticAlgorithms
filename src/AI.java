import java.awt.*;

public class AI {
    int x;
    int y;
    int scale;
    double fitness;

    Tile[][] grid;

    char[] moves;
    char[] chrom;

    public AI(int x, int y, int scale, Tile[][] grid){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.moves = new char[]{'U', 'D', 'R', 'L'};
        this.chrom = new char[32];
        this.grid = grid;
        for(int i = 0; i < chrom.length; i++){
            chrom[i] = moves[(int)(Math.random()*(moves.length-1))];
        }
//        chrom = new char[]{'D', 'D', 'D', 'R', 'R', 'R', 'R', 'D', 'D', 'D', 'D', 'R', 'R', 'R', 'R', 'U', 'U', 'R', 'R', 'R', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'R', 'R', 'R'};
    }

    public void run(){
        for(int i = 0; i < chrom.length; i++){
            move(chrom[i]);
            System.out.println("Moved: " + chrom[i]);
        }
        double distance = Math.sqrt(Math.pow((224 - y), 2) + Math.pow(224 - x, 2)) / scale;
        if (distance == 0) {
            fitness = 1;
        }
        fitness = 1 / distance;
        System.out.println(fitness);
    }

    public boolean movePossible(char move){
        if (grid[x / scale][(y) / scale].isWall()) {
            return true;
        } else if (move == 'U' && grid[x / scale][(y - scale) / scale].isWall()) {
            return false;
        } else if (move == 'U' && !grid[x / scale][(y - scale) / scale].isWall()) {
            return true;
        } else if (move == 'D' && grid[x / scale][(y + scale) / scale].isWall()) {
            return false;
        } else if (move == 'D' && !grid[x / scale][(y + scale) / scale].isWall()) {
            return true;
        } else if (move == 'R' && grid[(x + scale) / scale][(y) / scale].isWall()) {
            return false;
        } else if (move == 'R' && !grid[(x + scale) / scale][(y) / scale].isWall()) {
            return true;
        } else if (move == 'L' && grid[(x - scale) / scale][(y) / scale].isWall()) {
            return false;
        } else if (move == 'L' && !grid[(x - scale) / scale][(y) / scale].isWall()) {
            return true;
        }
        return true;
    }

    public void move(char move){
        if(move == 'U' && movePossible(move)){
            y -= scale;
        }else if(move == 'D' && movePossible(move)){
            y += scale;
        }else if(move == 'L' && movePossible(move)){
            x -= scale;
        }else if(move == 'R' && movePossible(move)){
            x += scale;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, scale, scale);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, scale, scale);
    }
}

