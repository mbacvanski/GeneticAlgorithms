import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Screen extends JPanel implements MouseListener, GridOwner, ActionListener {
    ArrayList<Tile> latestTiles = new ArrayList<>();
    Tile[][] grid;
    AI[] pop;
    ArrayList<AI> oldPops = new ArrayList<>();
    ArrayList<Double> oldFitnesses = new ArrayList<>();
    File file = new File("goodMaze.txt");
    int mutationChance = 1;
    boolean mainMenu = false;
    int numTimes = 0;
    JButton createNew;
    JButton preLoaded;
    private int scale;
    private int fittest = 0;
    private int fittest2 = 0;

    JButton maze1;
    JButton maze2;
    JButton maze3;
    JButton maze4;

    public Screen(int scale) {
        setLayout(null);
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
        if(mainMenu == true) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j].draw(g);
                }
            }

            for (AI a : pop) {
                a.draw(g, (a == pop[fittest] || a == pop[fittest2]));

                oldPops.add(a);
            }
            for (AI a : oldPops) {
                a.draw(g, false);
            }
        }

        maze1 = new JButton("Maze 1");  //Instantiate a button
        maze1.setBounds(50,50,200,30); //Set the position and size
        maze1.addActionListener(this);  //add to the action listener

        maze2 = new JButton("Maze 2");  //Instantiate a button
        maze2.setBounds(250,50,200,30); //Set the position and size
        maze2.addActionListener(this);  //add to the action listener

        maze3 = new JButton("Maze 3");  //Instantiate a button
        maze3.setBounds(50,250,200,30); //Set the position and size
        maze3.addActionListener(this);  //add to the action listener

        maze4 = new JButton("Maze 4");  //Instantiate a button
        maze4.setBounds(250,250,200,30); //Set the position and size
        maze4.addActionListener(this);  //add to the action listener

        if(mainMenu == false && numTimes == 0) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, 480, 480);

            createNew = new JButton("Create New Maze");  //Instantiate a button
            createNew.setBounds(150,50,200,30); //Set the position and size
            add(createNew); //add to JPanel
            createNew.addActionListener(this);  //add to the action listener

            preLoaded = new JButton("Pre-loaded Maze");  //Instantiate a button
            preLoaded.setBounds(150,150,200,30); //Set the position and size
            add(preLoaded); //add to JPanel
            preLoaded.addActionListener(this);  //add to the action listener

            numTimes++;

//            mainMenu = true;
        }

//        ArrayList<AI> newpop = new ArrayList<>(pop.length);


    }

    private void createNewPopulation() {
        boolean busy = false;
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


//        newpop.set(0, pop[fittest]);
//        newpop.get(0).setX(scale);
//        newpop.get(0).setY(scale);
//
//        newpop.set(1, pop[fittest2]);
//        newpop.get(0).setX(scale);
//        newpop.get(0).setY(scale);

        for (int i = 2; i < newpop.length/* / 2*/; i++) {
            newpop[i] = new AI(0, 0, scale, getPreferredSize(), this);

            int a = (int) (Math.random() * AI.CHROMSIZE);
//            int a = (int) (Math.random() * 31);

            int b = (int) (Math.random() * 10 + 1);

//            AI.Direction[] part1 = Arrays.copyOfRange(newpop[0].chrom, 0, a);
//            AI.Direction[] part2 = Arrays.copyOfRange(newpop[1].chrom, a, 32);

            ArrayList<AI.Direction> chromPart1 = new ArrayList<>();
            chromPart1.addAll(newpop[0].getChrom().subList(0, a));

            ArrayList<AI.Direction> chromPart2 = new ArrayList<>();
            chromPart2.addAll(newpop[1].getChrom().subList(a, AI.CHROMSIZE));

//            newpop[i].chrom = new AI.Direction[32];

//            System.arraycopy(part1, 0, newpop[i].chrom, 0, part1.length);
//            System.arraycopy(part2, 0, newpop[i].chrom, part1.length, part2.length);

            ArrayList<AI.Direction> totalChrom = new ArrayList<>();
            totalChrom.addAll(chromPart1);
            totalChrom.addAll(chromPart2);

            newpop[i].setChrom(totalChrom);

            if (b <= mutationChance) {
                int newpopSize = AI.CHROMSIZE;
                int randIndex = (int) (Math.random() * (newpopSize - 1));

                newpop[i].getChrom().set(randIndex, AI.getRandomDirection());
//                newpop[i].chrom[(int) (Math.random() * (newpop[i].chrom.length - 1))] = AI.getRandomDirection();
            }
        }
        for (int j = (newpop.length / 2) - 1; j < newpop.length; j++) {
            newpop[j] = new AI(0, 0, scale, getPreferredSize(), this);
        }

        pop = newpop;
        busy = false;
    }

    public void animate() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            createNewPopulation();
            repaint();
        }

//        while (!busy) {
//            createNewPopulation();
//            repaint();
//        }
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

//        grid[yIndex][xIndex] = !grid[yIndex][xIndex]; //backwards because rows, columns.
//        grid[yIndex][xIndex].getOccupyingAI(pop);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createNew) {

        }
        else if(e.getSource() == preLoaded) {
            removeAll();
            add(maze1);
            add(maze2);
            add(maze3);
            add(maze4);

        }
    }
}