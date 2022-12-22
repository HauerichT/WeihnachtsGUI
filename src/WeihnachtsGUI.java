import javax.swing.*;
import java.awt.*;

public class WeihnachtsGUI extends JFrame {

    Container c;

    JPanel leftPanel;
    JPanel rightPanel;

    Leinwand leinwand;

    public WeihnachtsGUI() {

        c = getContentPane();
        c.setLayout(null);

        leinwand = new Leinwand();

        leftPanel = new JPanel();
        leftPanel.setBounds(0,0,750,700);
        leftPanel.add(leinwand);
        leinwand.setPreferredSize(new Dimension(750,700));
        leinwand.setBackground(new Color(0,0,0));


        rightPanel = new JPanel();
        rightPanel.setBackground(Color.blue);
        rightPanel.setBounds(750,0,250,700);

        c.add(leftPanel);
        c.add(rightPanel);


    }

}
