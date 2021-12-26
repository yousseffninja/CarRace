package CarRace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Malek Mohamed
 */
public class HowToPlay extends JFrame {

    private JButton Back;
    private JLabel backGround;
    private JPanel jPanel1;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new HowToPlay();
    }

    public HowToPlay() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setSize(900, 900);
        setLocationRelativeTo(this);

    }

    private void initComponents() {

        jPanel1 = new JPanel();
        Back = new JButton();
        backGround = new JLabel();

        jPanel1.setLayout(null);

        Back.setFont(new Font("Yu Gothic UI Semilight", 3, 14));
        Back.setText("Back");
        Back.addActionListener(this::BackActionPerformed);
        jPanel1.add(Back);
        Back.setBounds(10, 580, 80, 40);

        backGround.setIcon(new ImageIcon(getClass().getResource("back.jpg"))); 
        jPanel1.add(backGround);
        backGround.setBounds(0, 0, 900, 900);

        getContentPane().add(jPanel1);
    }

    private void BackActionPerformed(ActionEvent evt) {
        setVisible(false);
        new MainMenu().setVisible(true);
    }
}
