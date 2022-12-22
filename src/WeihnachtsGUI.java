import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class WeihnachtsGUI extends JFrame {

    Container c;

    JPanel leftPanel;
    JPanel rightPanel;

    JPanel autorPanel;

    JPanel parameterPanel;


    Leinwand leinwand;

    JLabel label;
    JTextField text;
    JRadioButton radio1;
    JRadioButton radio2;
    ButtonGroup buttonGroup;
    JCheckBox checkBox;
    JButton button;

    TitledBorder titledBorder;

    public WeihnachtsGUI() {

        c = getContentPane();
        c.setLayout(null);

        leftPanel = new JPanel();
        leftPanel.setBounds(0,0,750,700);

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(238, 238, 238));
        rightPanel.setBounds(750,0,250,700);

        leinwand = new Leinwand();
        leinwand.setPreferredSize(new Dimension(750,700));
        leinwand.setBackground(new Color(0,0,0));

        autorPanel = new JPanel();
        autorPanel.setPreferredSize(new Dimension(250,30));

        label = new JLabel();
        label.setPreferredSize(new Dimension(40,30));
        label.setText("Autor:");

        text = new JTextField();
        text.setPreferredSize(new Dimension(200,30));
        text.setText("Text");
        text.setBackground(new Color(255,255,255));

        titledBorder = new TitledBorder("Parameter");

        parameterPanel = new JPanel();
        parameterPanel.setPreferredSize(new Dimension(250,100));
        parameterPanel.setLayout(new GridLayout(3, 1));
        parameterPanel.setBorder(titledBorder);

        radio1 = new JRadioButton("Tannenbaum");
        radio2 = new JRadioButton("Wald");
        checkBox = new JCheckBox("Santa");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(checkBox);

        parameterPanel.add(radio1);
        parameterPanel.add(radio2);
        parameterPanel.add(checkBox);

        autorPanel.add(label);
        autorPanel.add(text);

        leftPanel.add(leinwand);

        rightPanel.add(autorPanel);
        rightPanel.add(parameterPanel);

        c.add(leftPanel);
        c.add(rightPanel);


    }

}
