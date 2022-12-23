import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Leinwand extends JPanel {

    private BufferedImage santaImg;


    Leinwand() {
        this.setBackground(Color.BLACK);
    }

    public void tannenbaumZeichnen(Graphics g) {

        int randomX;
        int randomY;
        do {
            randomX = (int) (Math.random()*getWidth());
            randomY = (int) (Math.random()*getHeight());
        } while (randomY < getHeight()/1.5 || randomX < 0 || randomX > getWidth()-70);

        for (int i = 0; i < 3; i++) {
            int[] x = {randomX+i*5,randomX+35,randomX+70-i*5};
            int[] y = {randomY-i*30,randomY-55-i*30,randomY-i*30};
            g.setColor(new Color(41, 97, 23));
            g.fillPolygon(x,y,3);
        }
    }

    public void waldZeichnen(Graphics g) {
        for (int k = 0; k <= 10; k++) {

            int randomX;
            int randomY;
            do {
                randomX = (int) (Math.random()*getWidth()-20);
                randomY = (int) (Math.random()*getHeight());
            } while (randomY < getHeight()/1.5 || randomX < 0 || randomX > getWidth()-70);


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

    public void santaZeichnen (Graphics g, boolean santaIsSelected){

        if (santaIsSelected) {
            try {
                santaImg = ImageIO.read(new File("src/santa.png"));
            } catch (IOException ex) {
                System.out.println("Santa konnte nicht geladen werden :(");
            }

            Timer timer = new Timer(1, new ActionListener() {
                int counter = 0;
                @Override
                public void actionPerformed(ActionEvent e) {

                    g.setColor(Color.BLACK);
                    g.fillRect(counter, 60, 100,100);
                    g.drawImage(santaImg, counter, 60, 100,100, null);
                    counter++;

                    if (counter == getWidth()) {
                        g.fillRect(counter, 60, 100,100);
                        counter = 0;
                    }
                }
            });
            timer.start();
        }

    }
}
