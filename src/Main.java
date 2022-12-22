import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        WeihnachtsGUI weihnachtsGUI = new WeihnachtsGUI();
        weihnachtsGUI.setTitle("WeihnachtsGUI");
        weihnachtsGUI.setSize(1000,750);
        weihnachtsGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weihnachtsGUI.setVisible(true);
        weihnachtsGUI.setLocationRelativeTo(null);
        weihnachtsGUI.setResizable(false);

    }
}