package CarRace;

import CarRace.AnimListener;
import com.cs304.lab9.Texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;

public class SingleM3MediumCarListener extends AnimListener {

    int maxWidth = 900;
    int maxHeight = 900;
    private static final int X_MIN = 0;
    private static final int X_MAX = 900;



    int x = maxWidth/2, y = 100;
    int yEnemy1 = 900;
    int yEnemy2 = 650;
    int yEnemy3 = 500;
    int yEnemy4 = 350;
    int x1 = maxWidth/3, y1 = maxHeight/3;
    int x2 = 2*maxWidth/3, y2 = maxHeight/2;
    int y3 = 2*maxHeight/3, y4 = maxHeight;

    private int Xgenerate1 = 190;
    private int Xgenerate2 = 190;
    private int Xgenerate3 = 190;
    private int Xgenerate4 = 190;

    boolean flag = true;
//


    private static final int DI = 10;
    private static final int DJ = 10;

    private int[][] enemy = new int[DI][DJ];

    private static final int MAX_ENEMIES = 2;

    String textureNames[] = {"maincar_1.png","line.png","anmy_1.png","Man4.png","bg3s.png"};
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
            Xgenerate1 = generateX(0);
//            while(Xgenerate2 >= Xgenerate1 - 20 && Xgenerate2 <= Xgenerate1 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy2 < -60){
            yEnemy2 = maxHeight;
            Xgenerate2 = generateX(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy3 < -60){
            yEnemy3 = maxHeight;
            Xgenerate3 = generateX(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }
        if(yEnemy4 < -60){
            yEnemy4 = maxHeight;
            Xgenerate4 = generateX(0);
//            while(Xgenerate4 >= Xgenerate3 - 20 && Xgenerate4 <= Xgenerate3 + 20)
//                Xgenerate2 = generateX(0);
        }







        DrawLine(gl, x1 +10, y1, 1, 0.3f);
        DrawLine(gl, x2 -100, y1, 1, 0.3f);
        DrawLine(gl, x1 +10, y3, 1, 0.3f);
        DrawLine(gl, x2 - 100, y3, 1, 0.3f);
        DrawLine(gl, x1 +10, y4, 1, 0.3f);
        DrawLine(gl, x2 - 100, y4, 1, 0.3f);

//
        yEnemy1 -= 10;
        yEnemy2 -= 10;
        yEnemy3 -= 10;
        yEnemy4 -= 10;

        GameOver(x, y ,Xgenerate1, yEnemy1);
        GameOver(x, y ,Xgenerate2, yEnemy2);
        GameOver(x, y ,Xgenerate3, yEnemy3);
        GameOver(x, y ,Xgenerate4, yEnemy4);

        DrawEnemy(gl, Xgenerate1, yEnemy1, 2, 0.4f);
        DrawEnemy(gl, Xgenerate2, yEnemy2, 2, 0.4f);
        DrawEnemy(gl, Xgenerate3, yEnemy3, 2, 0.4f);
        DrawEnemy(gl, Xgenerate4, yEnemy4, 2, 0.4f);

        DrawMainCar(gl, x, y, 0, 1.1f);


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
        gl.glScaled(0.1*scale, 0.15*scale, 1);
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




    public void DrawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length - 1]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void GameOver(int x, int y , int Xgenerate1, int yEnemy1){
//        if((x == Xgenerate1 && y == yEnemy1) || (x == Xgenerate2 && y == yEnemy2) || (x == Xgenerate3 && y == yEnemy3) || (x == Xgenerate4 && y == yEnemy4)){
        if(x - 65 <= Xgenerate1 && x + 65 >= Xgenerate1  && y - 128 <= yEnemy1 && y + 128 >= yEnemy1){
            System.out.println("GameOver");
            JOptionPane.showMessageDialog(null, "GameOver.", "GameOver",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        //
    }

    public int generateX(int x){
        x = 130 + (int)(Math.random() * 470);
        return x;
    }

    /*
     * KeyListener
     */

    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 115) {
                x -= 6;
            }
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth-200) {
                x += 6;
            }
        }
//        if (isKeyPressed(KeyEvent.VK_ESCAPE)) {
//            if(flag){
//                animator.stop();
//                flag = false;
//            }
//        }
//        if (isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (y > 0) {
//                y--;
//            }
//        }
//        if (isKeyPressed(KeyEvent.VK_UP)) {
//            if (y < maxHeight-10) {
//                y++;
//            }
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


