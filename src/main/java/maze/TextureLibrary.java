package maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureLibrary {

	private int texture;
	
	public void init(GL2 gl) {
		try {
			//texture
			Texture t = TextureIO.newTexture(
				Maze.class.getResourceAsStream("/texture/pk02_wall01_C.tga"), true, TextureIO.TGA);
			texture = t.getTextureObject(gl);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public int getTexture() {
		return texture;
	}
}
