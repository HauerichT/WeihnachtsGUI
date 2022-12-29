import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Leinwand extends JPanel {

    // Instanzvariablen
    private BufferedImage santaImg;

    // Konstruktor
    Leinwand() {
        // setzt Leinwand-Hintergrund auf Schwarz
        this.setBackground(Color.BLACK);
    }

    // Tannenbaum bzw. Wald zeichnen
    public void tannenbaumZeichnen(Graphics g, String selectedButton) {

        // zeichnet einen Tannenbaum
        if (selectedButton.equals("tannenbaum")) {
            // zufällige Position erzeugen
            int randomX;
            int randomY;
            do {
                randomX = (int) (Math.random()*getWidth());
                randomY = (int) (Math.random()*getHeight());
            } while (randomY < getHeight()/1.5 || randomX < 0 || randomX > getWidth()-70);

            // Tannenbaum erzeugen und auf Leinwand zeichnen
            for (int i = 0; i < 3; i++) {
                int[] x = {randomX+i*5,randomX+35,randomX+70-i*5};
                int[] y = {randomY-i*30,randomY-55-i*30,randomY-i*30};
                g.setColor(new Color(41, 97, 23));
                g.fillPolygon(x,y,3);
            }
        }
        // zeichnet 10 Tannenbäume in unterschiedlicher Farbe
        else if (selectedButton.equals("wald")) {
            // 10 Tannenbäume erzeugen
            for (int k = 0; k <= 10; k++) {
                // zufällige Position erzeugen
                int randomX;
                int randomY;
                do {
                    randomX = (int) (Math.random()*getWidth());
                    randomY = (int) (Math.random()*getHeight());
                } while (randomY < getHeight()/1.5 || randomX < 0 || randomX > getWidth()-70);

                // zufällige RGB-Werte erzeugen
                int randomR = (int) (Math.random() * 255);
                int randomG = (int) (Math.random() * 255);
                int randomB = (int) (Math.random() * 255);

                // Tannenbaum erzeugen und auf Leinwand zeichnen
                for (int i = 0; i < 3; i++) {
                    int[] x = {randomX+i*5,randomX+35,randomX+70-i*5};
                    int[] y = {randomY-i*30,randomY-55-i*30,randomY-i*30};
                    g.setColor(new Color(randomR,randomG,randomB));
                    g.fillPolygon(x,y,3);
                }
            }
        }

    }

    // Santa ausblenden
    public void santaAusblenden() {
        this.santaImg = null;
    }

    // Santa einblenden
    public void santaEinblenden() {
        try {
            santaImg = ImageIO.read(new File("src/santa.png"));
        } catch (IOException ex) {
            System.out.println("Santa konnte nicht geladen werden :(");
        }
    }

    // Santa zeichnen und animieren
    public void santaZeichnen (Graphics g){
        // blendet Santa zu Beginn ein
        santaEinblenden();

        // Timer zur Erstellung der Animation
        Timer timer = new Timer(8, new ActionListener() {
            // Counter zur Speicherung der aktuellen Position des Bildes
            int postion = -120;

            // wird im angegeben delay immer wieder ausgeführt
            @Override
            public void actionPerformed(ActionEvent e) {
                // erzeugt Bild von Santa
                g.setColor(Color.BLACK);
                g.fillRect(postion, 60, 100,100);
                g.drawImage(santaImg, postion, 60, 100,100, null);
                // erhöht Position vom Bild um 1
                postion++;

                // wenn das Bild das Ende der Leinwand erreicht, wird das Bild wieder nach links gesetzt
                if (postion == getWidth()) {
                    g.fillRect(postion, 60, 100,100);
                    postion = -120;
                }
            }
        });
        // startet den Timer
        timer.start();
    }

}
