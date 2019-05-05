package maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import maze.TextureLibrary.WallFace;

public class Switch  {
	
	private int angle = 0;
	private int change = 15;

	private final int coordX;
	private final int coordZ;	
	private final TextureLibrary textureLibrary;
	
	public Switch(int coordX, int coordZ, TextureLibrary textureLibrary) {
		super();
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.textureLibrary = textureLibrary;				
	}
	
	public void init(GL2 gl) {		
		gl.glPushMatrix();		
//		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		
		angle += change;
		System.out.println("ROTATE: " + angle);
		
		gl.glTranslatef((float)coordX, 0, (float)coordZ);
		gl.glRotatef(angle, 0.4f, 0.6f, 0.4f);
		//gl.glScalef(0.3f, 0.3f, 0.3f);
		
		
//		Texture texture = textureLibrary.getWallTexture(coordX, coordZ, WallFace.Front);
//		texture.enable(gl);
//		texture.bind(gl);	

		
		gl.glBegin(GL2.GL_QUADS);
		/*gl.glTexCoord2f(0.4f, 0.4f);*/ gl.glVertex3f(0.4f, 0.6f, 0.4f);
		/*gl.glTexCoord2f(0.6f, 0.4f);*/ gl.glVertex3f(0.6f, 0.6f, 0.4f);
		/*gl.glTexCoord2f(0.6f, 0.6f);*/ gl.glVertex3f(0.6f, 0.4f, 0.4f);
		/*gl.glTexCoord2f(0.4f, 0.6f);*/ gl.glVertex3f(0.4f, 0.4f, 0.4f);
		
		/*gl.glTexCoord2f(0.4f, 0.4f);*/ gl.glVertex3f(0.6f, 0.6f, 0.6f);
		/*gl.glTexCoord2f(0.6f, 0.4f);*/ gl.glVertex3f(0.4f, 0.6f, 0.6f);
		/*gl.glTexCoord2f(0.6f, 0.6f);*/ gl.glVertex3f(0.4f, 0.4f, 0.6f);
		/*gl.glTexCoord2f(0.4f, 0.6f);*/ gl.glVertex3f(0.6f, 0.4f, 0.6f);	
		
		/*gl.glTexCoord2f(0.4f, 0.4f);*/ gl.glVertex3f(0.6f, 0.6f, 0.4f);
		/*gl.glTexCoord2f(0.6f, 0.4f);*/ gl.glVertex3f(0.6f, 0.6f, 0.6f);
		/*gl.glTexCoord2f(0.6f, 0.6f);*/ gl.glVertex3f(0.6f, 0.4f, 0.6f);
		/*gl.glTexCoord2f(0.4f, 0.6f);*/ gl.glVertex3f(0.6f, 0.4f, 0.4f);
		
		/*gl.glTexCoord2f(0.4f, 0.4f);*/ gl.glVertex3f(0.4f, 0.6f, 0.6f);
		/*gl.glTexCoord2f(0.6f, 0.4f);*/ gl.glVertex3f(0.4f, 0.6f, 0.4f);
		/*gl.glTexCoord2f(0.6f, 0.6f);*/ gl.glVertex3f(0.4f, 0.4f, 0.4f);
		/*gl.glTexCoord2f(0.4f, 0.6f);*/ gl.glVertex3f(0.4f, 0.4f, 0.6f);		
		gl.glEnd();
		
//		texture.disable(gl);
//		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glPopMatrix();
		
	}	
}
