import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Screen extends JPanel implements MouseListener, GridOwner {
    int scale;

    Tile[][] grid;
    AI[] pop;
    ArrayList<AI> oldPops = new ArrayList<AI>();
    ArrayList<Double> oldFitnesses = new ArrayList<Double>();
    File file = new File("maze3.txt");
    int mutationChance = 1;
    boolean clicked = false;
    int xIndex = 0;
    int yIndex = 0;

    public Screen(int scale) {
        addMouseListener(this);
        this.scale = scale;
        this.grid = new Tile[16][16];
        readGrid();

        pop = new AI[50];
        for (int i = 0; i < pop.length; i++) {
            pop[i] = new AI(scale, scale, scale, getPreferredSize(), this);
        } //creating individuals within a population of size 50.
    }

    public void readGrid() {
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
        if (clicked == true) {
            grid[yIndex][xIndex].drawClicked(g);
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
        oldFitnesses.add(pop[fittest].fitness);
        if(oldFitnesses.size()>20)
        {
            for(int i = oldFitnesses.size()-1; i > oldFitnesses.size()-20; i--)
            {
                if(Math.abs(oldFitnesses.get(i-1)-oldFitnesses.get(i))<0.02)
                {
                    if(mutationChance<10)
                    {
                        mutationChance++;
                    }
                }
            }
        }

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

            newpop[i] = new AI(scale, scale, scale, getPreferredSize(), this);
            newpop[i].chrom = new AI.Direction[32];

            System.arraycopy(part1, 0, newpop[i].chrom, 0, part1.length);
            System.arraycopy(part2, 0, newpop[i].chrom, part1.length, part2.length);

            if (b <= mutationChance) {
                newpop[i].chrom[(int) (Math.random() * (newpop[i].chrom.length - 1))] = AI.getRandomDirection();
            }
        }
        for (int j = (newpop.length / 2) - 1; j < newpop.length; j++) {
            newpop[j] = new AI(scale, scale, scale, getPreferredSize(), this);
        }

        pop = newpop;
    }

    public void animate() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            repaint();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xCor = e.getX();
        int yCor = e.getY();

        //Truncates, to the nearest 10.
        int xSquare = (int) (xCor / scale * 1.0) * scale;
        int ySquare = (int) (yCor / scale * 1.0) * scale;

        xIndex = xSquare / scale;
        yIndex = ySquare / scale;

        System.out.println("xIndex = " + xIndex);
        System.out.println("yIndex = " + yIndex);

//        grid[yIndex][xIndex] = !grid[yIndex][xIndex]; //backwards because rows, columns.
        grid[yIndex][xIndex].getOccupyingAI(pop);
        clicked = true;
        repaint();
        System.out.println("Screen.mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public Tile[][] getGrid() {
        return grid;
    }
}