import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze");

        Screen sc = new Screen(30, frame);
        frame.add(sc);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        while(true) {
//            if(sc.isAnimateBool() == true) {
//                System.out.println("animating");
//                sc.animate();
//            }
//        }
        sc.animate();

    }
}