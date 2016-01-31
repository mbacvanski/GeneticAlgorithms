import javax.swing.JFrame;
/**
 * Created by Home on 1/30/16.
 */
public class Runner {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Agricultural Optimizer");
        Agriculture.Screen s = new Agriculture.Screen();
        frame.add(s);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        s.animate(2);
    }
}
