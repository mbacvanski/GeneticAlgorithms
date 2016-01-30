import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Screen extends JPanel {
    int scale;

    Tile[][] grid;
    AI[] pop;
    ArrayList<AI> oldPops = new ArrayList<AI>();
    File file = new File("maze.txt");

    public Screen(int scale) {
        this.scale = scale;
        this.grid = new Tile[16][16];
        getGrid();

        pop = new AI[50];
        for (int i = 0; i < pop.length; i++) {
            pop[i] = new AI(scale, scale, scale, grid, getPreferredSize());
        } //creating individuals within a population of size 50.
    }

    public void getGrid() {
        try {
            Scanner in = new Scanner(file);
            int lineCounter = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                char[] charLine = line.toCharArray();
                for (int x = 0; x < charLine.length; x++) {
                    char charHere = charLine[x];
                    grid[lineCounter][x] = new Tile(x * scale, lineCounter * scale, scale, (charHere == '1'));
                }
                lineCounter++;
            } //READS INPUTS FOR THE MAZE SCHEMATIC
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Dimension getPreferredSize() {

        return new Dimension(grid.length * scale, grid[0].length * scale);
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].draw(g);
            }
        }

        int fittest = 0;
        int fittest2 = 0;

        for (int i = 0; i < pop.length; i++) {
            pop[i].run();
            if (pop[i].fitness > pop[fittest].fitness) {
                fittest = i;
            } else if (pop[i].fitness > pop[fittest2].fitness) {
                fittest2 = i;
            }
        }
        //pop[fittest].draw(g);
        //pop[fittest2].draw(g);
        for(AI a : pop)
        {
            a.draw(g);
            oldPops.add(a);
        }
        for(AI a : oldPops)
        {
            a.draw(g);
        }

        AI[] newpop = new AI[pop.length];

        System.out.println("New population!");

        newpop[0] = pop[fittest];
        newpop[0].x = scale;
        newpop[0].y = scale;
        newpop[1] = pop[fittest2];
        newpop[1].x = scale;
        newpop[1].y = scale;

        for (int i = 2; i < newpop.length / 2; i++) {

            int a = (int) (Math.random() * 31);
            int b = (int) (Math.random() * 10 + 1);

            AI.Direction[] part1 = Arrays.copyOfRange(newpop[0].chrom, 0, a);
            AI.Direction[] part2 = Arrays.copyOfRange(newpop[1].chrom, a, 32);

            newpop[i] = new AI(scale, scale, scale, grid, getPreferredSize());
            newpop[i].chrom = new AI.Direction[32];

            System.arraycopy(part1, 0, newpop[i].chrom, 0, part1.length);
            System.arraycopy(part2, 0, newpop[i].chrom, part1.length, part2.length);

            if (b == 1) {
                newpop[i].chrom[(int) (Math.random() * (newpop[i].chrom.length - 1))] = AI.getRandomDirection();
            }
        }
        for (int j = (newpop.length / 2) - 1; j < newpop.length; j++) {
            newpop[j] = new AI(scale, scale, scale, grid, getPreferredSize());
        }

        pop = newpop;
    }

    public void animate() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            repaint();
        }

    }
}
