import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Leinwand extends JPanel implements KeyListener, ActionListener {

    // Instanzvariablen
    private BufferedImage santaImg;
    private int santaX;
    private int santaY;

    // Konstruktor
    Leinwand() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);

        // Santa-Position
        this.santaX = -120;
        this.santaY = 60;
    }

    // Methode erzeugt einen Tannenbaum
    public void tannenbaumErzeugen(Graphics g) {
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
            g.setColor(new Color(randomR, randomG, randomB));
            g.fillPolygon(x,y,3);
        }
    }


    // Tannenbaum bzw. Wald zeichnen
    public void tannenbaumZeichnen(Graphics g, String selectedButton) {
        // zeichnet einen Tannenbaum
        if (selectedButton.equals("Tannenbaum")) {
            tannenbaumErzeugen(g);
        }
        // zeichnet 10 Tannenbäume
        else if (selectedButton.equals("Wald")) {
            for (int i = 0; i <= 10; i++) {
                tannenbaumErzeugen(g);
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

    // Santa animieren
    public void startAnimation() {
        // blendet das Bild zunächst ein
        santaEinblenden();
        // erzeugt und startet einen Timer
        Timer timer = new Timer(10, this);
        timer.start();
    }

    // Santa zeichnen
    public void santaZeichnen() {
        // setzt den Fokus, um die Steuerung zu gewähren
        requestFocus();

        // zeichnet den aktuellen Santa
        this.getGraphics().fillRect(santaX+8, santaY-8, 50, 100);
        this.getGraphics().fillRect(santaX, santaY, 50, 100);
        this.getGraphics().drawImage(santaImg, santaX, santaY, 80, 80, null);
        santaX++;

        // prüft, ob der Santa am Ende des Rahmens angekommen ist
        if (santaX >= getWidth()) {
            this.getGraphics().fillRect(santaX+8, santaY-8, 50, 100);
            this.getGraphics().fillRect(santaX, santaY, 50, 100);
            santaX = -120;
        }

    }

    // prüft, ob ein Key gedrückt wird
    @Override
    public void keyReleased(KeyEvent e) {
        // prüft, ob obere Grenze erreicht wurde
        if (santaY <= 0) {
            if (e.getKeyChar() == 'w' || e.getKeyChar() == 's') {
                santaY = 0;
            }
        }

        // prüft, ob untere Grenze erreicht wurde
        if (santaY+100 <= getHeight()/2) {
            // bewegt Santa nach oben bzw. unten
            if (e.getKeyChar() == 'w') {
                santaY -= 8;
            }
            if (e.getKeyChar() == 's') {
                santaY += 8;
            }
        }

        if (e.getKeyChar() == 'w') {
            santaY -= 8;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // Action-Listener zur Animation des Santas
    @Override
    public void actionPerformed(ActionEvent e) {
        this.santaZeichnen();
    }


}
