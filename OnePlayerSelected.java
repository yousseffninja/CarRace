package CarRace;

/**
 *
 * @author Malek Mohamed
 */
import CarRace.MainMenu;
import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.swing.*;

public class OnePlayerSelected extends JFrame {

    public JButton mapOne;
    public JButton mapTwo;
    public JButton mapThree;
    public JButton back;
    public JLabel gameName;
    public JLabel background;
    public JPanel jPanel1;


    public boolean flagMapOneButton = false;
    public boolean flagMapTwoButton;
    public boolean flagMapThreeButton;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new OnePlayerSelected();
    }

    public OnePlayerSelected() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setSize(900, 900);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        gameName = new JLabel();
        mapOne = new JButton();
        mapTwo = new JButton();
        mapThree = new JButton();
        back = new JButton();
        background = new JLabel();

        flagMapOneButton = false;
        flagMapTwoButton = false;
        flagMapThreeButton = false;

        jPanel1.setLayout(null);
        gameName.setFont(new Font("Yu Gothic UI Semilight", 3, 36));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setText("Maps");
        jPanel1.add(gameName);
        gameName.setBounds(0, 58, 600, 71);

        mapOne.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        mapOne.setText("Map One");
        mapOne.addActionListener(this::mapOneAction);
        jPanel1.add(mapOne);
        mapOne.setBounds(320, 220, 240, 50);

        mapTwo.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        mapTwo.setText("Map Two");
        mapTwo.setActionCommand("Route Tow");
        mapTwo.addActionListener(this::mapTwoAction);
        jPanel1.add(mapTwo);
        mapTwo.setBounds(320, 370, 240, 50);

        mapThree.setFont(new java.awt.Font("Yu Gothic UI Semilight", 3, 24));
        mapThree.setText("Map Three");
        mapThree.addActionListener(this::mapThreeAction);
        jPanel1.add(mapThree);
        mapThree.setBounds(320, 520, 240, 50);

        back.setFont(new Font("Yu Gothic UI Semilight", 3, 14));
        back.setText("Back");
        back.addActionListener(this::backAction);
        jPanel1.add(back);
        back.setBounds(20, 650, 65, 29);

        background.setHorizontalAlignment(SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("BackGround.jpeg")));
        background.setText("background");
        jPanel1.add(background);
        background.setBounds(0, 0, 900, 900);

        getContentPane().add(jPanel1);

    }

    private void mapOneAction(ActionEvent evt) {
        setVisible(false);
        new OnePlayerMap1().setVisible(true);
    }

    private void mapTwoAction(ActionEvent evt) {
        flagMapTwoButton = true;
        setVisible(false);
        new OnePlayerMap2().setVisible(true);
    }

    private void mapThreeAction(ActionEvent evt) {
        flagMapThreeButton = true;
        setVisible(false);
        new OnePlayerMap3().setVisible(true);
    }

    private void backAction(ActionEvent evt) {
        //To revert onePlayer and twoPlayers buttons' flags to false
        setVisible(false);
        new MainMenu().setVisible(true);
    }

}
