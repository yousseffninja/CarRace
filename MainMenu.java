package CarRace;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.swing.*;

/**
 *
 * @author Malek Mohamed
 */
public class MainMenu extends JFrame implements ActionListener {

    public JButton onePlayer;
    public JButton twoPlayers;
    public JButton howToPlay;
    public JButton scores;
    public JButton exit;
    public JLabel gameName;
    public JLabel background;
    public JPanel jPanel1;

    public boolean flag = false;

    boolean flagOnePlayerButton;
    boolean flagOnePlayerButton1;
    public boolean flagTwoPlayersButton = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainMenu();
    }

    public MainMenu() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setSize(900, 900);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public JButton getTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(JButton twoPlayers) {
        this.twoPlayers = twoPlayers;
    }

    public JButton getOnePlayer() {
        return onePlayer;
    }

    public void setOnePlayer(JButton onePlayer) {
        this.onePlayer = onePlayer;
    }

    private void initComponents() {

        
        jPanel1 = new JPanel();
        onePlayer = new JButton();
        twoPlayers = new JButton();
        howToPlay = new JButton();
        scores = new JButton();
        exit = new JButton();
        gameName = new JLabel();
        background = new JLabel();

        flagOnePlayerButton = false;
        flagTwoPlayersButton = false;
        
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setLayout(null);

        onePlayer.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        onePlayer.setText("1 Player");
        onePlayer.addActionListener(this::onePlayerAction);
        jPanel1.add(onePlayer);
        onePlayer.setBounds(320, 220, 254, 41);

        twoPlayers.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        twoPlayers.setText("2 Players");
        twoPlayers.addActionListener(this::twoPlayersAction);
        jPanel1.add(twoPlayers);
        twoPlayers.setBounds(320, 320, 254, 41);

        howToPlay.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        howToPlay.setText("How To Play");
        howToPlay.addActionListener(this::howToPlayAction);
        jPanel1.add(howToPlay);
        howToPlay.setBounds(320, 420, 254, 41);

        scores.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        scores.setText("Scores");
        scores.addActionListener(this::scoresAction);
        jPanel1.add(scores);
        scores.setBounds(320, 520, 254, 41);

        exit.setFont(new Font("Yu Gothic UI Semilight", 3, 24));
        exit.setText("Exit");
        exit.addActionListener(this::exitAction);
        jPanel1.add(exit);
        exit.setBounds(320, 620, 254, 41);

        gameName.setFont(new Font("Yu Gothic UI Semilight", 3, 48));
        gameName.setForeground(new Color(255, 255, 255));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setText("Car Race");
        jPanel1.add(gameName);
        gameName.setBounds(160, 70, 590, 64);

        getContentPane().add(jPanel1);
        background.setHorizontalAlignment(SwingConstants.CENTER);
        background.setIcon(new ImageIcon(getClass().getResource("BackGround.jpeg")));
        jPanel1.add(background);
        background.setBounds(0, 0, 900, 900);
    }

    public boolean isFlagTwoPlayersButton() {
        return flagTwoPlayersButton;
    }

    public void setFlagTwoPlayersButton(boolean flagTwoPlayersButton) {
        this.flagTwoPlayersButton = flagTwoPlayersButton;
        this.flagOnePlayerButton1 = flagTwoPlayersButton;
    }
//    SingleM1EasyCarListener m1 = new SingleM1EasyCarListener();

    public boolean isFlagOnePlayerButton() {
        return flagOnePlayerButton;
    }

    public void setFlagOnePlayerButton(boolean flagOnePlayerButton) {
        this.flagOnePlayerButton = flagOnePlayerButton;
    }

    private void onePlayerAction(ActionEvent e) {
        setFlagOnePlayerButton(true);
        Levels levels= new Levels();
        levels.flagOnePlayerButton = true;
        setVisible(false);
//        new Test1();
        //new SingleM1EasyCarListener().setVisible(true);
        new OnePlayerSelected().setVisible(true);

    }

    private boolean setTrue(boolean flag){
        return true;
    }

    private void twoPlayersAction(ActionEvent e) {
        flagTwoPlayersButton = true;
        //race r = new race(true
        setVisible(false);
        new TwoPlayerSelected().setVisible(true);
        flagTwoPlayersButton = true;
    }

    private void howToPlayAction(ActionEvent e) {
        setVisible(false);
        new HowToPlay().setVisible(true);
    }

    private void scoresAction(ActionEvent e) {
        setVisible(false);
        //Define scores JFrame Here
        //I didn't build scores JFrame since I don't know how should it appear
    }

    private void exitAction(ActionEvent e) {
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
