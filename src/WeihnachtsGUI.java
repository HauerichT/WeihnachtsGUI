import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeihnachtsGUI extends JFrame implements ActionListener {

    private JPanel rechteSeite, rechteSeiteOben, rechteSeiteParameter, rechteSeiteStart;
    private JRadioButton tannenbaum, wald;
    private JCheckBox santa;
    private JButton startButton;
    private ButtonGroup buttonGroup;
    private Leinwand leinwand;
    private JLabel autor, status;
    private JTextField text;

    public WeihnachtsGUI() {

        rechteSeite = new JPanel();
        rechteSeiteOben = new JPanel();
        rechteSeiteParameter = new JPanel();
        rechteSeiteStart = new JPanel();

        // leinwand erzeugen
        leinwand = new Leinwand();

        autor = new JLabel("Autor: ");
        autor.setPreferredSize(new Dimension(43,20));
        text = new JTextField("Text");
        text.setPreferredSize(new Dimension(193,20));

        // Radio-Buttons werden in eine ButtonGroup eingebunden
        buttonGroup = new ButtonGroup();
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

        // Start Button erstellen und in JPanel setzen
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        rechteSeiteStart = new JPanel();
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

    @Override
    public void actionPerformed(ActionEvent e) {

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

        if (tannenbaum.isSelected()) leinwand.tannenbaumZeichnen(leinwand.getGraphics());
        if (wald.isSelected()) leinwand.waldZeichnen(leinwand.getGraphics());
    }
}
