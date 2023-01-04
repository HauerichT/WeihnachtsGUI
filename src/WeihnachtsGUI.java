import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class WeihnachtsGUI extends JFrame implements ActionListener {

    // Instanzvariablen
    private boolean keinSantaErzeugt = true;
    private boolean keineTanneErzeugt = true;
    private final JRadioButton radioTannenbaum, radioWald;
    private final JCheckBox checkBoxSanta;
    private final JButton startButton;
    private final Leinwand leinwand;
    private final JLabel status, autor;
    private final JTextField text;
    private final ButtonGroup buttonGroup;
    private final JPanel rechteSeite, rechteSeiteOben, rechteSeiteParameter, rechteSeiteStart;


    public WeihnachtsGUI() {

        // JPanels für die einzelnen Elemente der rechten Seite erzeugen
        rechteSeite = new JPanel();
        rechteSeiteOben = new JPanel();
        rechteSeiteParameter = new JPanel();
        rechteSeiteStart = new JPanel();

        // Leinwand erzeugen
        leinwand = new Leinwand();

        // Autor und zugehöriges Textfeld erzeugen
        autor = new JLabel("Autor: ");
        autor.setPreferredSize(new Dimension(43,20));
        text = new JTextField("Text");
        text.setPreferredSize(new Dimension(193,20));

        // Radio-Buttons für Tannenbaum und Wald werden in eine ButtonGroup eingebunden
        buttonGroup = new ButtonGroup();
        radioTannenbaum = new JRadioButton("Tannenbaum");
        radioTannenbaum.setPreferredSize(new Dimension(240,20));
        radioWald = new JRadioButton("Wald");
        radioWald.setPreferredSize(new Dimension(240,20));
        buttonGroup.add(radioTannenbaum);
        buttonGroup.add(radioWald);

        // Erstellung der Santa-Checkbox
        checkBoxSanta = new JCheckBox("Santa");
        checkBoxSanta.setPreferredSize(new Dimension(240,20));

        // ActionListener auf die Parameter anwenden
        radioTannenbaum.addActionListener(this);
        radioWald.addActionListener(this);
        checkBoxSanta.addActionListener(this);

        // Zuweisung von Border und Größe zum Parameterblock
        rechteSeiteParameter.setPreferredSize(new Dimension(240, 100));
        rechteSeiteParameter.setBorder(new TitledBorder("Parameter"));

        // Elemente werden dem Parameterblock hinzugefügt
        rechteSeiteParameter.add(radioTannenbaum);
        rechteSeiteParameter.add(radioWald);
        rechteSeiteParameter.add(checkBoxSanta);

        // Autor-Eingabe und Parameter werden in einen Block zusammengefügt
        rechteSeiteOben.setPreferredSize(new Dimension(250,150));
        rechteSeiteOben.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        rechteSeiteOben.add(autor);
        rechteSeiteOben.add(text);
        rechteSeiteOben.add(rechteSeiteParameter);

        // Start-Button erstellen
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        rechteSeiteStart.setLayout(new BorderLayout());
        rechteSeiteStart.add(startButton);

        // Statusanzeige erstellen
        status = new JLabel();
        status.setText("-");
        status.setLayout(new BorderLayout());

        // Elemente der rechten Seite zuweisen
        rechteSeite.setLayout(new BorderLayout());
        rechteSeite.add(rechteSeiteOben, BorderLayout.NORTH);
        rechteSeite.add(rechteSeiteStart, BorderLayout.PAGE_END);

        // rechte und linke Seite dem Frame zuweisen
        this.setLayout(new BorderLayout(5, 0));
        this.add(leinwand, BorderLayout.CENTER);
        this.add(rechteSeite, BorderLayout.EAST);
        this.add(status, BorderLayout.PAGE_END);

    }

    // Event-Handler
    @Override
    public void actionPerformed(ActionEvent e) {

        // Statusanzeige setzen, je nachdem welche Buttons gewählt sind
        if (radioTannenbaum.isSelected()) {
            if (checkBoxSanta.isSelected()) {
                status.setText("Tannenbaum plus Santa");
            }
            else {
                status.setText("Tannenbaum");
            }
        }

        if (radioWald.isSelected()) {
            if (checkBoxSanta.isSelected()) {
                status.setText("Wald plus Santa");
            }
            else {
                status.setText("Wald");
            }
        }

        if (!radioWald.isSelected() && !radioTannenbaum.isSelected()) {
            status.setText("Santa");
        }

        if (!radioWald.isSelected() && !radioTannenbaum.isSelected() && !checkBoxSanta.isSelected()) {
            status.setText("-");
        }

        // Santa zeichnen
        if (e.getSource() == this.checkBoxSanta && !keineTanneErzeugt && keinSantaErzeugt) {
            leinwand.startAnimation();
            leinwand.santaZeichnen();
            keinSantaErzeugt = false;
        }

        // Santa ein- und ausblenden
        if (e.getSource() == this.checkBoxSanta && checkBoxSanta.isSelected()) {
            leinwand.santaEinblenden();
        }
        else if (e.getSource() == this.checkBoxSanta && !checkBoxSanta.isSelected()) {
            leinwand.santaAusblenden();
        }

        // Tannenbaum, Wald oder Santa zeichnen, wenn der Start-Button gedrückt wird
        if (e.getSource() == this.startButton) {
            if (radioTannenbaum.isSelected()) {
                leinwand.tannenbaumZeichnen(leinwand.getGraphics(), radioTannenbaum.getText());
                keineTanneErzeugt = false;
            }
            if (radioWald.isSelected()) {
                leinwand.tannenbaumZeichnen(leinwand.getGraphics(), radioWald.getText());
                keineTanneErzeugt = false;
            }
            if (checkBoxSanta.isSelected() && keinSantaErzeugt) {
                leinwand.startAnimation();
                leinwand.santaZeichnen();
                keinSantaErzeugt = false;
            }
        }

    }

}
