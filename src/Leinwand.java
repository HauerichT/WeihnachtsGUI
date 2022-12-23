import javax.swing.*;
import java.awt.*;

public class Leinwand extends JPanel {

    Leinwand() {
        this.setBackground(Color.BLACK);
    }

    public void tannenbaumZeichnen(Graphics g) {
        for (int i = 0; i < 3; i++) {
            int[] x = {45+i*5,80,115-i*5};
            int[] y = {135-i*30,80-i*30,135-i*30};
            g.setColor(new Color(0,200,0));
            g.fillPolygon(x,y,3);
        }
    }

    public void waldZeichnen(Graphics g) {
        for (int k = 0; k <= 10; k++) {

            int randomX = (int) (Math.random()*getWidth());
            int randomY = (int) (Math.random()*getHeight());

            int randomR = (int) (Math.random() * 255);
            int randomG = (int) (Math.random() * 255);
            int randomB = (int) (Math.random() * 255);

            for (int i = 0; i < 3; i++) {
                int[] x = {randomX+i*5,randomX+35,randomX+70-i*5};
                int[] y = {randomY-i*30,randomY-55-i*30,randomY-i*30};
                g.setColor(new Color(randomR,randomG,randomB));
                g.fillPolygon(x,y,3);

            }
        }

    }
}
