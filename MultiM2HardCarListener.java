package CarRace;

import com.cs304.lab9.AnimListener;
import com.cs304.lab9.Texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;

public class MultiM2HardCarListener extends AnimListener {


    int maxWidth = 900;
    int maxHeight = 900;

    private double now = java.time.LocalTime.now().getSecond();

    int speed = 15;
    int levelup = 1;

    int yEnemy1 = 900;
    int yEnemy2 = 650;
    int yEnemy3 = 900;
    int yEnemy4 = 650;

    private int Xgenerate1 = -generateXLeft(0);
    private int Xgenerate2 = -generateXLeft(0);
    private int Xgenerate3 = -generateXRight(0);
    private int Xgenerate4 = -generateXRight(0);

    int x = maxWidth/2, y = 100;
    int xc = 500, yc = 100;
    int xc2 = 150, yc2 = 100;
    int x1 = maxWidth/3, y1 = maxHeight/3;
    int x2 = 2*maxWidth/3, y2 = maxHeight/2;
    int y3 = 2*maxHeight/3, y4 = maxHeight;


    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"maincar_1.png","line.png","car_left_22.png", "anmy_1.png","bg2-m.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for(int i = 0; i < textureNames.length; i++){
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch( IOException e ) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackground(gl);
        handleKeyPress();


        if(y1 < -60)
            y1 = maxHeight;
        y1 -= 20;

        if(y3 < -60)
            y3 = maxHeight;
        y3 -= 20;

        if(y4 < -60)
            y4 = maxHeight;
        y4 -= 20;

        if(yEnemy1 < -60){
            yEnemy1 = maxHeight;
            Xgenerate1 = generateXLeft(0);
//            while(Xgenerate2 >= Xgenerate1 - 20 && Xgenerate2 <= Xgenerate1 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy2 < -60){
            yEnemy2 = maxHeight;
            Xgenerate2 = generateXLeft(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy3 < -60){
            yEnemy3 = maxHeight;
            Xgenerate3 = generateXRight(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy4 < -60){
            yEnemy4 = maxHeight;
            Xgenerate4 = generateXRight(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }
//
        //left
        DrawLine(gl, x2 , y1, 1, 0.3f);
        DrawLine(gl, x2 , y3, 1, 0.3f);
        DrawLine(gl, x2 , y4, 1, 0.3f);

        //right
        DrawLine(gl, x1-60 , y1, 1, 0.3f);
        DrawLine(gl, x1-60 , y3, 1, 0.3f);
        DrawLine(gl, x1-60 , y4, 1, 0.3f);

        levelup++;

        if(levelup % 103 == 0)
            speed++;

        yEnemy1 -= speed;
        yEnemy2 -= speed;
        yEnemy3 -= speed;
        yEnemy4 -= speed;

        DrawEnemy(gl, Xgenerate1, yEnemy1, 3, 0.4f);
        DrawEnemy(gl, Xgenerate2, yEnemy2, 3, 0.4f);
        DrawEnemy(gl, Xgenerate3, yEnemy3, 3, 0.4f);
        DrawEnemy(gl, Xgenerate4, yEnemy4, 3, 0.4f);

        GameOver1(xc2, yc2 ,Xgenerate1, yEnemy1, now);
        GameOver1(xc2, yc2 ,Xgenerate2, yEnemy2, now);
        GameOver2(xc, yc ,Xgenerate3, yEnemy3, now);
        GameOver2(xc, yc ,Xgenerate4, yEnemy4, now);

        DrawMainCar(gl, xc, yc, 0, 1);
        DrawMainCar(gl, xc2, yc2, 2, 1);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawMainCar(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
        gl.glScaled(0.1*scale, 0.1*scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawLine(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
        gl.glScaled(0.1*scale, 0.4*scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textureNames.length-1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1f, -1f, -1f);//left-botton
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1f, -1f, -1f);//right-button
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1f, 1f, -1f); //right-top
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1f, 1f, -1f);//left-top
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawEnemy(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
        gl.glScaled(0.2*scale, 0.4*scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }


    public void GameOver1(int x, int y , int Xgenerate1, int yEnemy1, double now){
//        if((x == Xgenerate1 && y == yEnemy1) || (x == Xgenerate2 && y == yEnemy2) || (x == Xgenerate3 && y == yEnemy3) || (x == Xgenerate4 && y == yEnemy4)){
        if(x - 60 <= Xgenerate1 && x + 60 >= Xgenerate1  && y - 120 <= yEnemy1 && y + 120 >= yEnemy1){
            System.out.println("Player 1 won");
            double end = java.time.LocalTime.now().getSecond();
            JOptionPane.showMessageDialog(null, "Player 1 won. \nScore: " + (end - now), "Player 1 won",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        //
    }

    public void GameOver2(int x, int y , int Xgenerate1, int yEnemy1, double now){
//        if((x == Xgenerate1 && y == yEnemy1) || (x == Xgenerate2 && y == yEnemy2) || (x == Xgenerate3 && y == yEnemy3) || (x == Xgenerate4 && y == yEnemy4)){
        if(x - 60 <= Xgenerate1 && x + 60 >= Xgenerate1  && y - 120 <= yEnemy1 && y + 120 >= yEnemy1){
            System.out.println("Player 2 won");
            double end = java.time.LocalTime.now().getSecond();
            JOptionPane.showMessageDialog(null, "Player 2 won. \nScore: " + (end - now), "Player 2 won",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        //
    }



    public int generateXLeft(int x){
        x = 100 + (int)(Math.random() * 260);
        return x;
    }

    public int generateXRight(int x){
        x = 490 + (int)(Math.random() * 230);
        return x;
    }


    /*
     * KeyListener
     */

    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (xc > 500) {
                xc-=8;
            }

        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (xc < maxWidth-200) {
                xc+=8;
            }

        }
        if (isKeyPressed(KeyEvent.VK_A)) {
            if (xc2 >120) {
                xc2-=8;
            }

        }
        if (isKeyPressed(KeyEvent.VK_D)) {
            if (xc2 < maxWidth-550) {
                xc2+=8;
            }

        }
//        if (isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (y > 0) {
//                y--;
//            }
//            animationIndex++;
//        }
//        if (isKeyPressed(KeyEvent.VK_UP)) {
//            if (y < maxHeight-10) {
//                y++;
//            }
//            animationIndex++;
//        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}