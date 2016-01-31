import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Screen extends JPanel implements MouseListener, GridOwner {
    int scale;
    ArrayList<Tile> latestTiles = new ArrayList<>();
    Tile[][] grid;
    AI[] pop;
    ArrayList<AI> oldPops = new ArrayList<>();
    ArrayList<Double> oldFitnesses = new ArrayList<>();
    File file = new File("goodMaze.txt");
    int mutationChance = 1;
    private boolean isDone = false;
    private int fittest = 0;
    private int fittest2 = 0;

    public Screen(int scale) {
        addMouseListener(this);
        this.scale = scale;
        this.grid = new Tile[16][16];
        readGrid();

        pop = new AI[50];
        for (int i = 0; i < pop.length; i++) {
            pop[i] = new AI(0, 0, scale, getPreferredSize(), this);
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

//        if (!isDone) {
//            for (AI a : pop) {
//                a.draw(g, (a == pop[fittest] || a == pop[fittest2]));
//
//                oldPops.add(a);
//            }
//            for (AI a : oldPops) {
//                a.draw(g, false);
//            }
//        } else {
//
//        }

        pop[fittest].draw(g, true);
        pop[fittest2].draw(g, true);
    }

    private void createNewPopulation() {
        fittest = 0;
        fittest2 = 0;

        for (int i = 0; i < pop.length; i++) {
            pop[i].run();
            if (pop[i].fitness > pop[fittest].fitness) {
                fittest = i;
            } else if (pop[i].fitness > pop[fittest2].fitness) {
                fittest2 = i;
            }
        }

        oldFitnesses.add(pop[fittest].fitness);

        if (oldFitnesses.size() > 20) {
            for (int i = oldFitnesses.size() - 1; i > oldFitnesses.size() - 20; i--) {
                if (Math.abs(oldFitnesses.get(i - 1) - oldFitnesses.get(i)) < 0.02) {
                    if (mutationChance < 10) {
                        mutationChance++;
                    }
                }
            }
        }

        AI[] newpop = new AI[pop.length];

        newpop[0] = pop[fittest];
        newpop[0].setX(scale);
        newpop[0].setY(scale);

        newpop[1] = pop[fittest2];
        newpop[1].setX(scale);
        newpop[1].setY(scale);

        for (int i = 2; i < newpop.length/* / 2*/; i++) {
            newpop[i] = new AI(0, 0, scale, getPreferredSize(), this);

            int a = (int) (Math.random() * AI.CHROMSIZE);
//            int a = (int) (Math.random() * 31);

            int b = (int) (Math.random() * 10 + 1);

            ArrayList<AI.Direction> chromPart1 = new ArrayList<>();
            chromPart1.addAll(newpop[0].getChrom().subList(0, a));

            ArrayList<AI.Direction> chromPart2 = new ArrayList<>();
            chromPart2.addAll(newpop[1].getChrom().subList(a, AI.CHROMSIZE));

            ArrayList<AI.Direction> totalChrom = new ArrayList<>();
            totalChrom.addAll(chromPart1);
            totalChrom.addAll(chromPart2);

            newpop[i].setChrom(totalChrom);

            if (b <= mutationChance) {
                int newpopSize = AI.CHROMSIZE;
                int randIndex = (int) (Math.random() * (newpopSize - 1));

                newpop[i].getChrom().set(randIndex, AI.getRandomDirection());
            }
        }
        for (int j = (newpop.length / 2) - 1; j < newpop.length; j++) {
            newpop[j] = new AI(0, 0, scale, getPreferredSize(), this);
        }

        pop = newpop;
    }

    public void animate() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            for (AI each : pop) {
                if (each.getDistance() == 0) {
                    isDone = true;
                    System.out.println("Done!!!");
//                    done = true;
                    break;
                }
            }

            createNewPopulation();
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
        int xSquare = (int) (xCor / 10.0) * 10;
        int ySquare = (int) (yCor / 10.0) * 10;

        int xIndex = xSquare / 10;
        int yIndex = ySquare / 10;

        System.out.println("xIndex = " + xIndex);
        System.out.println("yIndex = " + yIndex);

        for (AI each : pop) {
            System.out.println("each.getX() = " + each.getX());
            System.out.println("each.getY() = " + each.getY());
            if (each.getX() == xSquare && each.getY() == ySquare) {
                System.out.println("Fitness is " + each.getFitness());
            }
        }

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