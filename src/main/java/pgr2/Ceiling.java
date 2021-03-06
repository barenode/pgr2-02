package pgr2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Ceiling {

	private final int coordX;
	private final int coordZ;	
	private final TextureLibrary textureLibrary;
	
	public Ceiling(int coordX, int coordZ, TextureLibrary textureLibrary) {
		super();
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.textureLibrary = textureLibrary;				
	}
	
	public void init(GL2 gl) {		
		gl.glPushMatrix();		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTranslatef((float)coordX, 0, (float)coordZ);
		
		Texture texture = textureLibrary.getCeilingTexture(coordX, coordZ);
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		
		gl.glTexCoord2f(0f, 0f); gl.glVertex3f(0f, 1f, 0f);
		gl.glTexCoord2f(1f, 0f); gl.glVertex3f(1f, 1f, 0f);
		gl.glTexCoord2f(1f, 1f); gl.glVertex3f(1f, 1f, 1f);
		gl.glTexCoord2f(0f, 1f); gl.glVertex3f(0f, 1f, 1f);
		
		gl.glEnd();
		texture.disable(gl);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glPopMatrix();
		
	}
}
