package maze;

import java.util.Arrays;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2GL3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Wall {
	
	private static final float[][] NORMALS = {
		{-1.0f, 0.0f, 0.0f},
		{0.0f, 1.0f, 0.0f},
		{1.0f, 0.0f, 0.0f},
		{0.0f, -1.0f, 0.0f},
		{0.0f, 0.0f, 1.0f},
		{0.0f, 0.0f, -1.0f}
	};
	private static final int[][] FACES = {
		{0, 1, 2, 3},
		{3, 2, 6, 7},
		{7, 6, 5, 4},
		{4, 5, 1, 0},
		{5, 6, 2, 1},
		{7, 4, 0, 3}
	};
	private static final float[][] VERTICES;
	static {
		final float[][] v = new float[8][];
		for (int i = 0; i < 8; i++) {
			v[i] = new float[3];
		}
		v[0][0] = v[1][0] = v[2][0] = v[3][0] = 0.0f;
		v[4][0] = v[5][0] = v[6][0] = v[7][0] = 1.0f;
		v[0][1] = v[1][1] = v[4][1] = v[5][1] = 0.0f;
		v[2][1] = v[3][1] = v[6][1] = v[7][1] = 1.0f;
		v[0][2] = v[3][2] = v[4][2] = v[7][2] = 0.0f;
		v[1][2] = v[2][2] = v[5][2] = v[6][2] = 1.0f;
		VERTICES = v;
	}

	private final int coordX;
	private final int coordZ;
	private final List<Segment> segments;
	
	private int texture;
	
	public Wall(int coordX, int coordZ, int texture) {
		super();
		this.coordX = coordX;
		this.coordZ = coordZ;
		this.texture = texture;
		this.segments = Arrays.asList(
			new Segment(
				new Vec2D((float)(-coordX)+.1f, (float)(-coordZ)+.1f),
				new Vec2D((float)(-coordX-1)-1.f, (float)(-coordZ)+.1f)
			),
			new Segment(
				new Vec2D((float)(-coordX)+.1f, (float)(-coordZ)+.1f),
				new Vec2D((float)(-coordX)+.1f, (float)(-coordZ-1)-1.f)
			),
			new Segment(
				new Vec2D((float)(-coordX-1)-1.f, (float)(-coordZ)+.1f),
				new Vec2D((float)(-coordX-1)-1.f, (float)(-coordZ-1)-1.f)
			),
			new Segment(
				new Vec2D((float)(-coordX)+.1f, (float)(-coordZ-1)-1.f),
				new Vec2D((float)(-coordX-1)-1.f, (float)(-coordZ-1)-1.f)
			)
		);
	}
	
	public void init(GL2 gl) {
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glTranslatef((float)coordX, 0, (float)coordZ);
		drawBox(gl, 1, GL2GL3.GL_QUADS);
		gl.glPopMatrix();
//		try {
//			//texture
//			Texture t = TextureIO.newTexture(
//				Maze.class.getResourceAsStream("/texture/pk02_wall01_C.tga"), true, TextureIO.TGA);
//			texture = t.getTextureObject(gl);
//		} catch (Exception e) {
//			throw new IllegalStateException(e);
//		}
	}
	
	public List<Segment> getSegments() {
		return segments;
	}
	
	//GL2GL3.GL_QUADS
	private void drawBox(final GL2 gl, final float size, final int type) {
		final float[][] v = VERTICES;
		final float[][] n = NORMALS;
		final int[][] faces = FACES;
		for (int i = 5; i >= 0; i--) {
			gl.glBegin(type);
			gl.glNormal3fv(n[i], 0);
			float[] vt = v[faces[i][0]];
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(vt[0] * size, vt[1] * size, vt[2] * size);
			vt = v[faces[i][1]];
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(vt[0] * size, vt[1] * size, vt[2] * size);
			vt = v[faces[i][2]];
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(vt[0] * size, vt[1] * size, vt[2] * size);
			vt = v[faces[i][3]];
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(vt[0] * size, vt[1] * size, vt[2] * size);
			gl.glEnd();
		}
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordZ() {
		return coordZ;
	}	
}
