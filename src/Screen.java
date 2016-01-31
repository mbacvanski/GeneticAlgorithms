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
    File file/* = new File("goodMaze.txt")*/;
    int mutationChance = 1;
    int numTimes = 0;
    JButton createNew;
    JButton preLoaded;
    JButton maze1;
    JButton maze2;
    JButton maze3;
    JButton maze4;
    JButton maze5;
    JButton go;
    JFrame frame;
    private int scale;
    private int fittest = 0;
    private int fittest2 = 0;
    private MazeDesigner mazeDesigner;

    private State currentState;

    public Screen(int scale, JFrame frame) {
        currentState = State.MAINMENU;
        setLayout(null);
        addMouseListener(this);
        this.scale = scale;
        this.grid = new Tile[16][16];
//        readGrid();

        this.frame = frame;

        maze1 = new JButton("Maze 1");  //Instantiate a button
        maze1.setBounds(50, 50, 200, 30); //Set the position and size
        maze1.addActionListener(this);  //add to the action listener

        maze2 = new JButton("Maze 2");  //Instantiate a button
        maze2.setBounds(250, 50, 200, 30); //Set the position and size
        maze2.addActionListener(this);  //add to the action listener

        maze3 = new JButton("Maze 3");  //Instantiate a button
        maze3.setBounds(50, 250, 200, 30); //Set the position and size
        maze3.addActionListener(this);  //add to the action listener

        maze4 = new JButton("Maze 4");  //Instantiate a button
        maze4.setBounds(250, 250, 200, 30); //Set the position and size
        maze4.addActionListener(this);  //add to the action listener

        maze5 = new JButton("Maze 5");  //Instantiate a button
        maze5.setBounds(150, 350, 200, 30); //Set the position and size
        maze5.addActionListener(this);  //add to the action listener

        createNew = new JButton("Create New Maze");  //Instantiate a button
        createNew.setBounds(150, 50, 200, 30); //Set the position and size
        add(createNew); //add to JPanel
        createNew.addActionListener(this);  //add to the action listener

        preLoaded = new JButton("Pre-loaded Maze");  //Instantiate a button
        preLoaded.setBounds(150, 150, 200, 30); //Set the position and size
        add(preLoaded); //add to JPanel
        preLoaded.addActionListener(this);  //add to the action listener

        mazeDesigner = new MazeDesigner(scale);

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
            animate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Dimension getPreferredSize() {

        return new Dimension(grid.length * scale, grid[0].length * scale);
    }

    public void paintComponent(Graphics g) {
        switch (currentState) {
            case MAINMENU: {

                g.setColor(Color.lightGray);
                g.fillRect(0, 0, 480, 480);

//                preLoaded = new JButton("Pre-loaded Maze");  //Instantiate a button
//                preLoaded.setBounds(150, 150, 200, 30); //Set the position and size
//                add(preLoaded); //add to JPanel
//                preLoaded.addActionListener(this);  //add to the action listener

                numTimes++;
                break;
            }
            case CREATEMAZE: {
                mazeDesigner.draw(g);
                break;
            }
            case SOLVEMAZE: {
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
                break;
            }
        }

//        if (mainMenu == true) {
//            for (int i = 0; i < grid.length; i++) {
//                for (int j = 0; j < grid[i].length; j++) {
//                    grid[i][j].draw(g);
//                }
//            }
//
//            for (AI a : pop) {
//                a.draw(g, (a == pop[fittest] || a == pop[fittest2]));
//
//                oldPops.add(a);
//            }
//            for (AI a : oldPops) {
//                a.draw(g, false);
//            }
//        } else if ()
//        if (mainMenu == false && numTimes == 0) {
//            maze1 = new JButton("Maze 1");  //Instantiate a button
//            maze1.setBounds(50, 50, 200, 30); //Set the position and size
//            maze1.addActionListener(this);  //add to the action listener
//
//            maze2 = new JButton("Maze 2");  //Instantiate a button
//            maze2.setBounds(250, 50, 200, 30); //Set the position and size
//            maze2.addActionListener(this);  //add to the action listener
//
//            maze3 = new JButton("Maze 3");  //Instantiate a button
//            maze3.setBounds(50, 250, 200, 30); //Set the position and size
//            maze3.addActionListener(this);  //add to the action listener
//
//            maze4 = new JButton("Maze 4");  //Instantiate a button
//            maze4.setBounds(250, 250, 200, 30); //Set the position and size
//            maze4.addActionListener(this);  //add to the action listener
//
//            maze5 = new JButton("Maze 5");  //Instantiate a button
//            maze5.setBounds(150, 350, 200, 30); //Set the position and size
//            maze5.addActionListener(this);  //add to the action listener
//
//            g.setColor(Color.lightGray);
//            g.fillRect(0, 0, 480, 480);
//
//            createNew = new JButton("Create New Maze");  //Instantiate a button
//            createNew.setBounds(150, 50, 200, 30); //Set the position and size
//            add(createNew); //add to JPanel
//            createNew.addActionListener(this);  //add to the action listener
//
//            preLoaded = new JButton("Pre-loaded Maze");  //Instantiate a button
//            preLoaded.setBounds(150, 150, 200, 30); //Set the position and size
//            add(preLoaded); //add to JPanel
//            preLoaded.addActionListener(this);  //add to the action listener
//
//            numTimes++;

//            mainMenu = true;
//        }

//        ArrayList<AI> newpop = new ArrayList<>(pop.length);


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
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                createNewPopulation();
                repaint();
            }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createNew) {
            System.out.println("Create new clicked!");
            frame.removeAll();
            frame.add(mazeDesigner);
            repaint();
            currentState = State.CREATEMAZE;
        } else if (e.getSource() == preLoaded) {
            System.out.println("Adding maze preloaded selection!");
            removeAll();
            add(maze1);
            add(maze2);
            add(maze3);
            add(maze4);
            add(maze5);
        } else if (e.getSource() == maze1) {
            System.out.println("Doing maze1!");
            removeAll();
            System.out.println("p1");
            file = new File("maze.txt");
            System.out.println("p2");

            currentState = State.SOLVEMAZE;
            System.out.println("p3");

            readGrid();
            System.out.println("p4");

        } else if (e.getSource() == maze2) {
            System.out.println("Doing maze2!");
            removeAll();
            file = new File("maze2.txt");
            currentState = State.SOLVEMAZE;
            readGrid();
        } else if (e.getSource() == maze3) {
            System.out.println("Doing maze3!");
            removeAll();
            file = new File("maze3.txt");
            currentState = State.SOLVEMAZE;
            readGrid();
        } else if (e.getSource() == maze4) {
            System.out.println("Doing maze4!");
            removeAll();
            file = new File("maze4.txt");
            currentState = State.SOLVEMAZE;
            readGrid();
        } else if (e.getSource() == maze5) {
            System.out.println("Doing maze5!");
            removeAll();
            file = new File("goodMaze.txt");
            currentState = State.SOLVEMAZE;
            readGrid();
        }
        repaint();
    }

    public enum State {MAINMENU, CREATEMAZE, SOLVEMAZE}
}