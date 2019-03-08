package custom;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Renderer implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {


	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// nulujeme misto pro kresleni
		gl.glClearColor(0f, 0f, 0f, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		

		// nastaveni modelovaci transformace
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity(); // inicializace na jednotkovou matici

		// nastaveni projekce
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity(); // inicializace na jednotkovou matici

		gl.glBegin(GL2.GL_TRIANGLES); // vykreslime trojuhelnik
		gl.glColor3f(1.0f, 0.0f, 0.0f); // barva prvniho vrcholu CERVENA
		gl.glVertex2f(-1.0f, -1.0f); // souradnice v 2D (levy dolni roh)
		gl.glColor3f(0.0f, 1.0f, 0.0f); // barva prvniho vrcholu ZELENA
		gl.glVertex2f(1.0f, 0.0f); // souradnice v 2D (stred prave strany)
		gl.glColor3f(0.0f, 0.0f, 1.0f); // barva prvniho vrcholu MODRA
		gl.glVertex2f(0.0f, 1.0f); // souradnice v 2D (stred horni strany)
		gl.glEnd(); // ukoncime kresleni trojuhelniku
		
		System.out.println("ROTATE");
		gl.glRotatef(1, 0, 0, 1);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
