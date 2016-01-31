import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by marc on 160131.
 */
public class MazeDesigner extends JPanel implements MouseListener {

    private boolean[][] maze = new boolean[16][16];
    private int scale;

    public MazeDesigner(int scale) {
        this.scale = scale;
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                g.drawRect(col * scale, row * scale, scale, scale);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int xCor = e.getX();
        int yCor = e.getY();

        //Truncates, to the nearest 10.
        int xSquare = (int) (xCor / 10.0) * 10;
        int ySquare = (int) (yCor / 10.0) * 10;

        int xIndex = xSquare / 10;
        int yIndex = ySquare / 10;

        System.out.println("xIndex = " + xIndex);
        System.out.println("yIndex = " + yIndex);

        maze[yIndex][xIndex] = !maze[yIndex][xIndex]; //backwards because rows, columns.
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
}
