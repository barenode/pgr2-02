package maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class MazeRenderer implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {
	
	private final GLU glu = new GLU();
	private final GLUT glut = new GLUT();
	
	int width, height;
	int dx, dy;
	int ox, oy;
	
	double azimut = 180, zenit = 0;
	
	float xpos = 0;
	float ypos = 0;
	
	
	float x, y, z = 0;
	
	public MazeRenderer() {
		super();
	}

	/**
	 * {@see GLEventListener#init(GLAutoDrawable)}.
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();	
//		
		gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_LINE);
		gl.glPolygonMode(GL2.GL_BACK, GL2.GL_LINE);
//		
		gl.glNewList(1, GL2.GL_COMPILE);		
		gl.glMatrixMode(GL2.GL_MODELVIEW);		
		gl.glPushMatrix();
		glut.glutSolidCube(1);		
		//gl.glScalef(10, 10, 100);
		//glut.glutSolidCube(1);				
		gl.glPopMatrix();		
		gl.glEndList();	
		
		gl.glNewList(2, GL2.GL_COMPILE);
		for (int x=-5; x<=5; x++) {
			//int x = 0;
			//int z = 0;
			//int y = 0;
			for (int y=-5; y<=5; y++) {
				for (int z=-5; z<=5; z++) {
					gl.glPushMatrix();
					gl.glTranslatef(x*1, y*1, z*1);
					gl.glCallList(1);
					gl.glPopMatrix();
				}
			}
		}
		gl.glEndList();	
		
//		gl.glNewList(2, GL2.GL_COMPILE);
//		gl.glPushMatrix();
//		gl.glBegin(GL2.GL_LINES);
//		
//		gl.glColor3f(1, 0, 0); 
//		gl.glVertex3f(0, 0, 0);
//		gl.glVertex3f(1, 0, 0);
//		
//		gl.glColor3f(0, 1, 0); 
//		gl.glVertex3f(0, 0, 0);
//		gl.glVertex3f(0, 1, 0);
//		
//		gl.glColor3f(0, 0, 1); 
//		gl.glVertex3f(0, 0, 0);
//		gl.glVertex3f(0, 0, 1);
//		
//		gl.glEnd();
//		gl.glPopMatrix();
//		gl.glEndList();	
	}

	/**
	 * {@see GLEventListener#init(GLAutoDrawable)}.
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glClearColor(0f, 0f, 0f, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		
		gl.glLoadIdentity();		

		glu.gluPerspective(45, width / (float) height, 0.1f, 300.0f);		
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		
//		gl.glMultMatrixf(m, m_offset);
		
//		zenit += dy;
//		dy = 0;
//		if (zenit > 90)
//			zenit = 90;
//		if (zenit < -90)
//			zenit = -90;
//		
//		
//		azimut += dx;
//		dx = 0;
//		azimut = azimut % 360;
//		
//		gl.glTranslatef(x, y, z);
//		
//		gl.glRotated(zenit - 90, 1, 0, 0);
//		gl.glRotated(azimut, 0, 0, 1);
		
		glu.gluLookAt(0, 0, 20, 0, 10, 0, 0, 1, 0);
		
		gl.glCallList(2);
		
		
	}

	/**
	 * {@see GLEventListener#init(GLAutoDrawable)}.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		this.width = width;
		this.height = height;
		gl.glViewport(0, 0, this.width, this.height);
		
	}	
	
	/**
	 * {@see GLEventListener#init(GLAutoDrawable)}.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {}
	
	
	
	// --------------------------------------------------
	// MouseListener
	// --------------------------------------------------
	/**
	 * {@see MouseListener#mouseClicked(MouseEvent)}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}	

	@Override
	public void mouseEntered(MouseEvent e) {}	

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		ox = e.getX();
		oy = e.getY();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	
	
	// --------------------------------------------------
	// MouseMotionListener
	// --------------------------------------------------	
	@Override
	public void mouseDragged(MouseEvent e) {
		dx = e.getX() - ox;
		dy = e.getY() - oy;
		ox = e.getX();
		oy = e.getY();			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	// --------------------------------------------------
	// KeyListener
	// --------------------------------------------------	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				System.out.println("WWW");
				ypos += 1;
				break;
			case KeyEvent.VK_S:
				ypos -= 1;
				break;
			case KeyEvent.VK_A:
				xpos += 1;
				break;
			case KeyEvent.VK_D:
				xpos -= 1;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
