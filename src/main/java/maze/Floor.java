package maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Floor {

	private final int coordX;
	private final int coordZ;	
	private final String def;
	private final TextureLibrary textureLibrary;
	
	public Floor(int coordX, int coordZ, String def, TextureLibrary textureLibrary) {
		super();
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.def = def;
		this.textureLibrary = textureLibrary;				
	}
	
	public void init(GL2 gl) {		
		gl.glPushMatrix();		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTranslatef((float)coordX, 0, (float)coordZ);
		
		Texture texture = textureLibrary.getFloorTexture(getType());
		texture.enable(gl);
		texture.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
		
		int orientation = getOrientation();
		switch (orientation) {
			case 0 :
				gl.glTexCoord2f(0f, 0f); gl.glVertex3f(0f, 0f, 1f);
				gl.glTexCoord2f(1f, 0f); gl.glVertex3f(1f, 0f, 1f);
				gl.glTexCoord2f(1f, 1f); gl.glVertex3f(1f, 0f, 0f);
				gl.glTexCoord2f(0f, 1f); gl.glVertex3f(0f, 0f, 0f);
			break;
			case 1 :
				gl.glTexCoord2f(0f, 1f); gl.glVertex3f(0f, 0f, 1f);
				gl.glTexCoord2f(0f, 0f); gl.glVertex3f(1f, 0f, 1f);
				gl.glTexCoord2f(1f, 0f); gl.glVertex3f(1f, 0f, 0f);
				gl.glTexCoord2f(1f, 1f); gl.glVertex3f(0f, 0f, 0f);		
			break;
			case 2 :
				gl.glTexCoord2f(1f, 1f); gl.glVertex3f(0f, 0f, 1f);
				gl.glTexCoord2f(0f, 1f); gl.glVertex3f(1f, 0f, 1f);
				gl.glTexCoord2f(0f, 0f); gl.glVertex3f(1f, 0f, 0f);
				gl.glTexCoord2f(1f, 0f); gl.glVertex3f(0f, 0f, 0f);
			break;
			case 3 :
				gl.glTexCoord2f(1f, 0f); gl.glVertex3f(0f, 0f, 1f);
				gl.glTexCoord2f(1f, 1f); gl.glVertex3f(1f, 0f, 1f);
				gl.glTexCoord2f(0f, 1f); gl.glVertex3f(1f, 0f, 0f);
				gl.glTexCoord2f(0f, 0f); gl.glVertex3f(0f, 0f, 0f);
			break;
				
		}
		gl.glEnd();
		texture.disable(gl);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glPopMatrix();		
	}
	
	private Integer getType() {
		return Integer.valueOf(def.trim());
	}
	
	private Integer getOrientation() {
		return def.indexOf(def.trim());
	}
}
