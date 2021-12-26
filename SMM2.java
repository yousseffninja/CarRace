package CarRace;

/**
 *
 * @author Malek Mohamed
 */
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

public class SMM2 extends JFrame implements KeyListener{
    public GLCanvas glcanvas;
    public Animator animator;
    
    public static void main(String[] args) {
        new SEM2();
    }
    
    public SMM2(){
        SingleM2MediumCarListener listener = new SingleM2MediumCarListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
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
