import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class WeihnachtsGUI extends JFrame implements ActionListener {

    // Instanzvariablen
    private boolean keinSantaErzeugt = true;
    private final JRadioButton tannenbaum, wald;
    private final JCheckBox santa;
    private final JButton startButton;
    private final Leinwand leinwand;
    private final JLabel status;


    public WeihnachtsGUI() {

        // JPanels für die einzelnen Elemente der rechten Seite erzeugen
        JPanel rechteSeite = new JPanel();
        JPanel rechteSeiteOben = new JPanel();
        JPanel rechteSeiteParameter = new JPanel();
        JPanel rechteSeiteStart = new JPanel();

        // Leinwand erzeugen
        leinwand = new Leinwand();

        // Autor und zugehöriges Textfeld erzeugen
        JLabel autor = new JLabel("Autor: ");
        autor.setPreferredSize(new Dimension(43,20));
        JTextField text = new JTextField("Text");
        text.setPreferredSize(new Dimension(193,20));

        // Radio-Buttons für Tannenbaum und Wald werden in eine ButtonGroup eingebunden
        ButtonGroup buttonGroup = new ButtonGroup();
        tannenbaum = new JRadioButton("Tannenbaum");
        tannenbaum.setPreferredSize(new Dimension(240,20));
        wald = new JRadioButton("Wald");
        wald.setPreferredSize(new Dimension(240,20));
        buttonGroup.add(tannenbaum);
        buttonGroup.add(wald);

        // Erstellung der Santa-Checkbox
        santa = new JCheckBox("Santa");
        santa.setPreferredSize(new Dimension(240,20));

        // ActionListener auf die Parameter anwenden
        tannenbaum.addActionListener(this);
        wald.addActionListener(this);
        santa.addActionListener(this);

        // Zuweisung von Border und Größe zum Parameterblock
        rechteSeiteParameter.setPreferredSize(new Dimension(240, 100));
        rechteSeiteParameter.setBorder(new TitledBorder("Parameter"));

        // Elemente werden dem Parameterblock hinzugefügt
        rechteSeiteParameter.add(tannenbaum);
        rechteSeiteParameter.add(wald);
        rechteSeiteParameter.add(santa);

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
        if (tannenbaum.isSelected()) {
            if (santa.isSelected()) {
                status.setText("Tannenbaum plus Santa");
            }
            else {
                status.setText("Tannenbaum");
            }
        }

        if (wald.isSelected()) {
            if (santa.isSelected()) {
                status.setText("Wald plus Santa");
            }
            else {
                status.setText("Wald");
            }
        }

        if (!wald.isSelected() && !tannenbaum.isSelected()) {
            status.setText("Santa");
        }

        if (!wald.isSelected() && !tannenbaum.isSelected() && !santa.isSelected()) {
            status.setText("-");
        }


        // Santa zeichnen, wenn noch kein Santa erzeugt wurde
        if (e.getSource() == this.santa && keinSantaErzeugt) {
            leinwand.santaZeichnen(leinwand.getGraphics());
            keinSantaErzeugt = false;
        }

        // Santa ein- und ausblenden
        if (e.getSource() == this.santa && santa.isSelected()) {
            leinwand.santaEinblenden();
        }
        else if (e.getSource() == this.santa && !santa.isSelected()) {
            leinwand.santaAusblenden();
        }

        // Tannenbaum oder Wald zeichnen, wenn der Start-Button gedrückt wird
        if (e.getSource() == this.startButton) {
            if (tannenbaum.isSelected()) leinwand.tannenbaumZeichnen(leinwand.getGraphics(), tannenbaum.getText());
            if (wald.isSelected()) leinwand.tannenbaumZeichnen(leinwand.getGraphics(), wald.getText());
        }

    }
}
