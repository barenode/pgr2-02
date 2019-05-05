package maze;

import java.util.Arrays;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import maze.TextureLibrary.WallFace;

public class Wall {	

	private final int coordX;
	private final int coordZ;
	private final List<Segment> segments;
	
	private final TextureLibrary textureLibrary;
	
	public Wall(int coordX, int coordZ, TextureLibrary textureLibrary) {
		super();
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.textureLibrary = textureLibrary;
		
		float set = 0.2f;
		
		float x0 = -coordX;
		float z0 = -coordZ;
		Vec2D e0 = new Vec2D(x0+set, z0+set);		
		float x1 = x0-1f;
		Vec2D e1 = new Vec2D(x1-set, z0+set);		
		float z1 = z0-1f;
		Vec2D e2 = new Vec2D(x1-set, z1-set);
		Vec2D e3 = new Vec2D(x0+set, z1-set);
		
		this.segments = Arrays.asList(
			new Segment(e0, e1),				
			new Segment(e1, e2),
			new Segment(e2, e3),
			new Segment(e3, e0)
		);
	}
	
	public void init(GL2 gl) {
		gl.glPushMatrix();				
		gl.glTranslatef((float)coordX, 0, (float)coordZ);
		
		Texture texture;
		//front face
		texture = textureLibrary.getWallTexture(coordX, coordZ, WallFace.Front);
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
		texture = textureLibrary.getWallTexture(coordX, coordZ, WallFace.Back);
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
		texture = textureLibrary.getWallTexture(coordX, coordZ, WallFace.Right);
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
		texture = textureLibrary.getWallTexture(coordX, coordZ, WallFace.Left);
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
	
	public List<Segment> getSegments() {
		return segments;
	}	

	public int getCoordX() {
		return coordX;
	}

	public int getCoordZ() {
		return coordZ;
	}	
}
