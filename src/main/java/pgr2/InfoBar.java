package pgr2;

import java.awt.Font;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.awt.TextRenderer;

public class InfoBar {

	private final GLCanvas canvas;	
	private final Maze maze;
	
	public InfoBar(GLCanvas canvas, Maze maze) {
		super();
		this.canvas = canvas;
		this.maze = maze;
	}
	
	public void display(GLAutoDrawable drawable) {				
		if (drawable == null) {
			return;
		}
		GL2 gl = drawable.getGL().getGL2();
		//push all parameters
		int shaderProgram = pushAll(drawable);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glFrontFace(GL2.GL_CCW);
		gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL); 
		gl.glPolygonMode(GL2.GL_BACK, GL2.GL_LINE);
		
		gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL);
		gl.glViewport(0, 0, drawable.getSurfaceWidth(),
				drawable.getSurfaceHeight());
		TextRenderer renderer;
		renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));
		renderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		renderer.beginRendering(drawable.getSurfaceWidth(),
				drawable.getSurfaceHeight());
		
		renderer.draw("MISSION OBJECTIVE: Find switches " + maze.getVisitedSwitches() + "/" + maze.getTotalSwitches(), 30, 40);
		renderer.draw("[WSAD], myš - pohyb, pohled", canvas.getWidth()-200, 40);
		renderer.draw("František Hylmar KPGR2 2019", canvas.getWidth()-200, 20);
		
		renderer.endRendering();
		
		//pop all parameters
		popAll(drawable, shaderProgram);
	}
	
	private int pushAll(GLAutoDrawable glDrawable){
	 	if (glDrawable == null) {
			return 0;
	 	}
		GL2 gl = glDrawable.getGL().getGL2();
		//push all parameters
		int[] shaderProgram = new int[1];
		gl.glUseProgram(0);
		gl.glGetIntegerv(GL2.GL_CURRENT_PROGRAM, shaderProgram, 0);
		gl.glPushAttrib(GL2.GL_ENABLE_BIT);
		gl.glPushAttrib(GL2.GL_DEPTH_BUFFER_BIT);
		gl.glPushAttrib(GL2.GL_VIEWPORT_BIT);
		gl.glPushAttrib(GL2.GL_TEXTURE_BIT);
		gl.glPushAttrib(GL2.GL_POLYGON_BIT);
		gl.glDisable(GL2.GL_DEPTH_TEST);
		gl.glDisableVertexAttribArray(0);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glDepthMask(false);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		
		return shaderProgram[0];
	}
 
	private void popAll(GLAutoDrawable glDrawable, int shaderProgram){
		 if (glDrawable == null) {
			return;
		 }
		 GL2 gl = glDrawable.getGL().getGL2();		
		 //pop all parameters
		 gl.glPopMatrix();
		 gl.glMatrixMode(GL2.GL_PROJECTION);
		 gl.glPopMatrix();
		 gl.glPopAttrib();
		 gl.glPopAttrib();
		 gl.glPopAttrib();
		 gl.glPopAttrib();
		 gl.glPopAttrib();
		 gl.glUseProgram(shaderProgram);
	}
}
