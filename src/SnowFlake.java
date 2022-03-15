import javax.swing.*;
import java.awt.*;

public class SnowFlake {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(60, 90, 500, 500);
            }
        };
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
