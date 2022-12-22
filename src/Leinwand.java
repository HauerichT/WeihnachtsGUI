import javax.swing.*;
import java.awt.*;

public class Leinwand extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0,244,0));
        g.fillPolygon(new int[] {50, 100, 150}, new int[] {80, 20, 80}, 3);
    }
}
