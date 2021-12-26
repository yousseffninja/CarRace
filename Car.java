package CarRace;

import CarRace.AnimListener;
import CarRace.Example1.Anim;
import CarRace.Example1.AnimGLEventListener3;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Car extends JFrame implements KeyListener {

    public GLCanvas glcanvas;
    public Animator animator;

    public static void main(String[] args) {
        new Car();
    }

    public Car() {

        MainMenu menu = new MainMenu();
        
        Maps maps = new Maps();
        maps.setVisible(false);
        
        Levels levels = new Levels();
        levels.setVisible(false);
        
        SingleM1EasyCarListener LSE1 = new SingleM1EasyCarListener();
        SingleM2EasyCarListener LSE2 = new SingleM2EasyCarListener();
        SingleM3EasyCarListener LSE3 = new SingleM3EasyCarListener();

        SingleM1MediumCarListener LSM1 = new SingleM1MediumCarListener();
        AnimListener LSM2 = new SingleM2MediumCarListener();
        AnimListener LSM3 = new SingleM3MediumCarListener();

        SingleM1HardCarListener LSH1 = new SingleM1HardCarListener();
        SingleM2HardCarListener LSH2 = new SingleM2HardCarListener();
        SingleM3HardCarListener LSH3 = new SingleM3HardCarListener();

        MultiM1EasyCarListener LME1 = new MultiM1EasyCarListener();
        MultiM2EasyCarListener LME2 = new MultiM2EasyCarListener();
        MultiM3EasyCarListener LME3 = new MultiM3EasyCarListener();

        MultiM1MediumCarListener LMM1 = new MultiM1MediumCarListener();
        MultiM1MediumCarListener LMM2 = new MultiM1MediumCarListener();
        MultiM1MediumCarListener LMM3 = new MultiM1MediumCarListener();

        MultiM1HardCarListener LMH1 = new MultiM1HardCarListener();
        MultiM1HardCarListener LMH2 = new MultiM1HardCarListener();
        MultiM1HardCarListener LMH3 = new MultiM1HardCarListener();


        SingleM3HardCarListener listener = new SingleM3HardCarListener();
        glcanvas = new GLCanvas();
        new SingleM1EasyCarListener();
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            
                    new SingleM1EasyCarListener();
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagMediumButton == true){
            glcanvas.addGLEventListener(LSM1);            
            glcanvas.addKeyListener(LSM1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagHardButton == true){
            glcanvas.addGLEventListener(LSH1);            
            glcanvas.addKeyListener(LSH1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
        
        if(menu.flagOnePlayerButton == true && maps.flagMapOneButton == true && levels.flagEasyButton == true){
            glcanvas.addGLEventListener(LSE1);            
            glcanvas.addKeyListener(LSE1);
        }
//        AnimListener listener = new SingleM3MediumCarListener();
//        glcanvas = new GLCanvas();
//        glcanvas.addGLEventListener(listener);
//        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();

        setTitle("Car Race");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    public void handleKeyPress(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {
            animator.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
