package Agriculture;

import javax.swing.*;

/**
 * Created by Home on 1/30/16.
 */
public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("City Traffic");
        Screen s = new Screen();
        frame.add(s);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        while (true) {
            s.animate();
        }
    }
}
