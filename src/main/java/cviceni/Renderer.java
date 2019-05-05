package cviceni;

import java.io.IOException;
import java.io.InputStream;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Renderer implements GLEventListener {

	Texture texture;
	Texture texture2;
	
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glLineWidth(5f);
		
		System.out.println("Loading texture...");
		InputStream is = getClass().getResourceAsStream("/test_texture.jpg"); // vzhledem k adresari res v projektu 
		if (is == null)
			System.out.println("File not found");
		else
		try {
			texture = TextureIO.newTexture(is, true, "jpg");
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}
		
		is = getClass().getResourceAsStream("/ea.gif"); // vzhledem k adresari res v projektu 
		if (is == null)
			System.out.println("File not found");
		else
		try {
			texture2 = TextureIO.newTexture(is, true, "gif");
		} catch (GLException | IOException e) {
			e.printStackTrace();
		}
		int paramTexApp = GL2.GL_REPLACE;
		//gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, paramTexApp);
	}	

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
//		gl.glRotatef(10f, 0f, 0f, 1f);		
		
//		gl.glPushMatrix();
//		gl.glRotatef(10f, 0f, 0f, 1f);	
//		gl.glPopMatrix();
		
		//QUADS
		//gl.glPushMatrix();	
		gl.glTranslatef(.3f, .0f, 0);	
		
		texture2.enable(gl);
		texture2.bind(gl);
		gl.glBegin(GL2.GL_QUADS);		
		gl.glColor3f(1f, 1f, 1f);
		gl.glTexCoord2f(0f, 0f);
		gl.glVertex3f(0, 0, 0);
		gl.glTexCoord2f(0.0f, 1f);
		gl.glVertex3f(.1f, 0, 0);
		gl.glTexCoord2f(1f, 1f);
		gl.glVertex3f(.1f, .1f, 0);
		gl.glTexCoord2f(1f, 0f);
		gl.glVertex3f(0, .1f, 0);
		gl.glEnd();		
		texture2.disable(gl);
		//gl.glDisable(GL2.GL_TEXTURE_2D);
		//gl.glPopMatrix();
		
		
		//gl.glPushMatrix();	
		gl.glTranslatef(0, .3f, 0);
		//gl.glEnable(GL2.GL_TEXTURE_2D);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);		
		//gl.glColor3f(0f, 1f, 1f);		
		gl.glTexCoord2f(0f, 0f);
		gl.glVertex3f(0, 0, 0);
		gl.glTexCoord2f(0.0f, 1f);
		gl.glVertex3f(.1f, 0, 0);
		gl.glTexCoord2f(1f, 1f);
		gl.glVertex3f(.1f, .1f, 0);
		gl.glTexCoord2f(1f, 0f);
		gl.glVertex3f(0, .1f, 0);
		gl.glEnd();	
		texture.disable(gl);
		//gl.glDisable(GL2.GL_TEXTURE_2D);
		//gl.glPopMatrix();


		
		//AXES
		gl.glBegin(GL2.GL_LINES);		
		gl.glColor3f(1f, 0f, 0f);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(1, 0, 0);		
		gl.glColor3f(0f, 1f, 0f);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 1, 0);		
		gl.glColor3f(0f, 0f, 1f);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, 1);		
		gl.glEnd();		

		gl.glDisable(GL2.GL_TEXTURE_2D);
//		gl.glBegin(GL2.GL_TRIANGLES); // vykreslime trojuhelnik
//		gl.glColor3f(1.0f, 0.0f, 0.0f); // barva prvniho vrcholu CERVENA
//		gl.glVertex2f(-1.0f, -1.0f); // souradnice v 2D (levy dolni roh)
//		gl.glColor3f(0.0f, 1.0f, 0.0f); // barva prvniho vrcholu ZELENA
//		gl.glVertex2f(1.0f, 0.0f); // souradnice v 2D (stred prave strany)
//		gl.glColor3f(0.0f, 0.0f, 1.0f); // barva prvniho vrcholu MODRA
//		gl.glVertex2f(0.0f, 1.0f); // souradnice v 2D (stred horni strany)
//		gl.glEnd(); // ukoncime kresleni trojuhelniku
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		
		System.out.println("RESHAPE - x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);
		GL2 gl = drawable.getGL().getGL2();
		
		//gl.glViewport(100, 100 , width-100, height-100);
		
		//gl.glViewport(0, 0, 100, 100);
	}			

	@Override
	public void dispose(GLAutoDrawable drawable) {}			
}
