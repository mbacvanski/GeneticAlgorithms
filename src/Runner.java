import javax.swing.*;

public class Runner {
    public static void main(String[] args){
        JFrame frame = new JFrame("Maze");

        Screen sc = new Screen(16);
        frame.add(sc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

//        sc.getGrid();
        sc.animate();
    }
}