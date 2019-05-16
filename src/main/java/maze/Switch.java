package maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import maze.TextureLibrary.WallFace;

public class Switch  {
	
	private int angle = 0;
	private int change = 1;

	private final int coordX;
	private final int coordZ;	
	private final TextureLibrary textureLibrary;
	
	private final Maze maze;
	private final Renderer renderer;
	
	public Switch(Maze maze, Renderer renderer, int coordX, int coordZ, TextureLibrary textureLibrary) {
		super();
		this.maze = maze;
		this.renderer = renderer;
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.textureLibrary = textureLibrary;				
	}
	
	public void display(GL2 gl) {		
		int x = (int)Math.abs(renderer.getPosX());
		int z = (int)Math.abs(renderer.getPosZ());
		System.out.println("x: " + x + ", z: " + z + " coordX: " + coordX + ", coordZ: " + coordZ) ;
		if (x==coordX && z==coordZ) {
			maze.onSwitchVisited(coordX, coordZ);
		}
		
		gl.glPushMatrix();		
		
		angle += change;		
		gl.glTranslatef(((float)coordX)+0.5f, 0.3f, ((float)coordZ)+0.5f);
		gl.glRotatef(angle, 0f, 1f, 0f);
		gl.glScalef(0.15f, 0.3f, 0.15f);
		gl.glTranslatef(-0.5f, 0f, -0.5f);		

		Texture texture;
		//front face
		texture = textureLibrary.getSwitchTexture(WallFace.Front);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0f, 0f); gl.glVertex3f(1f, 0f, 0f); 
		gl.glTexCoord2f(1f, 0f); gl.glVertex3f(0f, 0f, 0f); 
		gl.glTexCoord2f(1f, 1f); gl.glVertex3f(0f, 1f, 0f);
		gl.glTexCoord2f(0f, 1f); gl.glVertex3f(1f, 1f, 0f);
		gl.glEnd();		
		texture.disable(gl);
		
		//back face
		texture = textureLibrary.getSwitchTexture(WallFace.Back);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0f, 0f); gl.glVertex3f(0f, 0f, 1f);
		gl.glTexCoord2f(1f, 0f); gl.glVertex3f(1f, 0f, 1f);		
		gl.glTexCoord2f(1f, 1f); gl.glVertex3f(1f, 1f, 1f);
		gl.glTexCoord2f(0f, 1f); gl.glVertex3f(0f, 1f, 1f);
		gl.glEnd();
		texture.disable(gl);		
		
		//right face
		texture = textureLibrary.getSwitchTexture(WallFace.Right);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0f, 0f); gl.glVertex3f(1f, 0f, 1f);
		gl.glTexCoord2f(1f, 0f); gl.glVertex3f(1f, 0f, 0f);
		gl.glTexCoord2f(1f, 1f); gl.glVertex3f(1f, 1f, 0f);
		gl.glTexCoord2f(0f, 1f); gl.glVertex3f(1f, 1f, 1f);
		gl.glEnd();
		texture.disable(gl);

		//left face
		texture = textureLibrary.getSwitchTexture(WallFace.Left);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0f, 0f); gl.glVertex3f(0f, 0f, 0f);
		gl.glTexCoord2f(1f, 0f); gl.glVertex3f(0f, 0f, 1f);
		gl.glTexCoord2f(1f, 1f); gl.glVertex3f(0f, 1f, 1f);
		gl.glTexCoord2f(0f, 1f); gl.glVertex3f(0f, 1f, 0f);
		gl.glEnd();
		texture.disable(gl);		

		gl.glPopMatrix();		
	}	
}
