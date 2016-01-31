import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by marc on 160131.
 */
public class MazeDesigner extends JPanel implements MouseListener, KeyListener {

    private boolean[][] maze = new boolean[16][16];
    private int scale;

    public MazeDesigner(int scale) {
        this.scale = scale;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        requestFocusInWindow(); //Shhh.
        System.out.println();
        System.out.println("MazeDesigner.draw");
        for (int row = 0; row < maze.length; row++) {
            System.out.println("row = " + row);
            for (int col = 0; col < maze[row].length; col++) {
                System.out.println("col = " + col);
                if (maze[row][col]) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fillRect(col * scale, row * scale, scale, scale);
                g.setColor(Color.BLACK);
                g.drawRect(col * scale, row * scale, scale, scale);
            }
        }
        System.out.println("Finished mazeDesigner drawing");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(480, 480);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("MazeDesigner.mouseClicked");

        int xCor = e.getX();
        int yCor = e.getY();

        //Truncates, to the nearest 10.
        int xSquare = (int) (xCor / scale / 1.0) * scale;
        int ySquare = (int) (yCor / scale / 1.0) * scale;

        int xIndex = xSquare / scale;
        int yIndex = ySquare / scale;

        System.out.println("xIndex = " + xIndex);
        System.out.println("yIndex = " + yIndex);

        maze[yIndex][xIndex] = !maze[yIndex][xIndex]; //backwards because rows, columns.
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) { //ENTER
            ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
            for (int row = 0; row < maze.length; row++) {
                ArrayList<Tile> rowList = new ArrayList<>();
                for (int col = 0; col < maze[row].length; col++) {
                    rowList.add(new Tile(col, row, scale, !maze[row][col]));
                }
                tiles.add(rowList);
            }

            for (int row = 0; row < tiles.size(); row++) {
                for (int col = 0; col < tiles.get(row).size(); col++) {
                    System.out.print(tiles.get(row).get(col).isNotWall() + " ");
                }
                System.out.println();
            }

            writeTileArraysToFile(tiles);
        }
    }


    private void writeTileArraysToFile(ArrayList<ArrayList<Tile>> tileArrays) {
        try {
            FileWriter fl = new FileWriter("gendata.txt", false);
            BufferedWriter br = new BufferedWriter(fl);

//            PrintWriter writer = new PrintWriter("gendata.txt");
            for (ArrayList<Tile> tileList : tileArrays) {
                for (Tile tile : tileList) {
                    char whatToWrite = tile.isNotWall() ? '0' : '1';
                    System.out.println("whatToWrite = " + whatToWrite);
                    br.write(whatToWrite);
//                    writer.print((char) ((tile.isNotWall()) ? 0 : 1));
                }
//                writer.println();
                br.write("\n");
            }

            br.close();
//            writer.flush();
//            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
