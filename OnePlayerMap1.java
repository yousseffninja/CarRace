package CarRace;

/**
 *
 * @author Malek Mohamed
 */
import CarRace.Maps;
import CarRace.Levels;
import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.swing.*;

/**
 *
 * @author Malek Mohamed
 */
public class OnePlayerMap1 extends JFrame implements ActionListener {

    public JButton easy;
    public JButton medium;
    public JButton hard;
    public JButton back;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public JPanel jPanel1;

    public GLCanvas glcanvas;
    public Animator animator;
    public JLabel background;

    public boolean flagOnePlayerButton;
    public boolean flagEasyButton = false;
    public boolean flagMediumButton;
    public boolean flagHardButton;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new OnePlayerMap1();
    }

    public OnePlayerMap1() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        setSize(900, 900);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        easy = new JButton();
        medium = new JButton();
        hard = new JButton();
        back = new JButton();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();

        jLabel2 = new JLabel();

        flagEasyButton = false;
        flagMediumButton = false;
        flagHardButton = false;

        getContentPane().setLayout(null);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setBackground(new Color(0, 0, 0));

        jLabel1.setFont(new Font("Yu Gothic UI Semilight", 3, 48));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Levels");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(320, 50, 600, 64);

        easy.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        easy.setText("Easy");
        easy.addActionListener(this::easyActionPerformed);
        jPanel1.add(easy);
        easy.setBounds(320, 190, 254, 48);

        medium.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        medium.setText("Medium");
        medium.addActionListener(this::mediumActionPerformed);
        jPanel1.add(medium);
        medium.setBounds(320, 320, 254, 48);

        hard.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        hard.setText("Hard");
        hard.addActionListener(this::hardActionPerformed);
        jPanel1.add(hard);
        hard.setBounds(320, 460, 254, 48);

        back.setFont(new Font("Yu Gothic UI Semilight", 3, 18));
        back.setText("Back");
        back.addActionListener(this::backAction);
        jPanel1.add(back);
        back.setBounds(30, 623, 80, 30);

//        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("BackGround.jpeg")));
        jLabel2.setText("Put background here");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 900, 900);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 900, 900);

    }

    private void easyActionPerformed(ActionEvent evt) {
        new SEM1();
    }

    private void mediumActionPerformed(ActionEvent evt) {
        new SMM1();


    }

    private void hardActionPerformed(ActionEvent evt) {
        new SHM1();
    }

    private void backAction(ActionEvent evt) {
        // Revert Map buttons' flags to false
        setVisible(false);
        new OnePlayerSelected().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
