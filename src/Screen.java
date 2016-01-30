import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Screen extends JPanel {
    int scale;

    Tile[][] grid;
    String[][] gridstray = new String[16][16];
    AI[] pop;
    File file = new File("maze.txt");

    public Screen(int scale) {
        this.scale = scale;
        this.grid = new Tile[16][16];
        getGrid();

        pop = new AI[50];
        for (int i = 0; i < pop.length; i++) {
            pop[i] = new AI(scale, scale, scale, grid);
        }
    }

    public void getGrid() {
        try {

            Scanner in = new Scanner(file);
            int lineCounter = 0;
            while (in.hasNextLine()) {
                System.out.println("Has new line!");
                String line = in.nextLine();
                char[] charLine = line.toCharArray();
                System.out.println("charLine = " + charLine);
                for (int x = 0; x < charLine.length; x++) {
                    char charHere = charLine[x];
                    System.out.println("charHere = " + charHere);
                    grid[lineCounter][x] = new Tile(x * scale, lineCounter * scale, scale, (charHere == '1'));
                }
                lineCounter++;
            }

            System.out.println("-------------------------------------");

            for (Tile[] line : grid) {
                for (Tile tile : line) {
                    if (tile != null) {
                        if (tile.isWall()) {
                            System.out.print(tile.isWall() + "  ");
                        } else {
                            System.out.print(tile.isWall() + " ");
                        }
                    }
                }
                System.out.println();
            }

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
        pop[fittest].draw(g);
        pop[fittest2].draw(g);

        AI[] newpop = new AI[pop.length];

        newpop[0] = pop[fittest];
        newpop[0].x = scale;
        newpop[0].y = scale;
        newpop[1] = pop[fittest2];
        newpop[1].x = scale;
        newpop[1].y = scale;

        for (int i = 2; i < newpop.length / 2; i++) {
            char[] moves = new char[]{'U', 'D', 'R', 'L'};

            int a = (int) (Math.random() * 31);
            int b = (int) (Math.random() * 10 + 1);

            char[] part1 = Arrays.copyOfRange(newpop[0].chrom, 0, a);
            char[] part2 = Arrays.copyOfRange(newpop[1].chrom, a, 32);

            newpop[i] = new AI(scale, scale, scale, grid);
            newpop[i].chrom = new char[32];

            System.arraycopy(part1, 0, newpop[i].chrom, 0, part1.length);
            System.arraycopy(part2, 0, newpop[i].chrom, part1.length, part2.length);

            if (b == 1) {
                newpop[i].chrom[(int) (Math.random() * (newpop[i].chrom.length - 1))] = moves[(int) (Math.random() * (moves.length - 1))];
            }
        }
        for (int i = (newpop.length / 2) - 1; i < newpop.length; i++) {
            newpop[i] = new AI(scale, scale, scale, grid);
        }

        pop = newpop;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }
}
