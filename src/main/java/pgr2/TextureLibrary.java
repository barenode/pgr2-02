package pgr2;

import java.util.HashMap;
import java.util.Map;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureLibrary {
	
	Map<Character, Texture> wallTextures = new HashMap<>();
	Map<Integer, Texture> floorTextures = new HashMap<>();
	Map<CeilingTexturePointer, Texture> ceilingTextures = new HashMap<>();
	Map<WallTexturePointer, Texture> switchTextures = new HashMap<>();
	
	
	//WALL
	/**
	 * type 1-7
	 * mode a,b,c
	 * 
	pk02_wall01a_C.tga
	pk02_wall02a_C.tga
	pk02_wall02b_C.tga
	pk02_wall02c_C.tga
	pk02_wall03a_C.tga
	pk02_wall03b_C.tga
	pk02_wall04a_C.tga
	pk02_wall04b_C.tga
	pk02_wall05a_C.tga
	pk02_wall05b_C.tga
	pk02_wall05c_C.tga
	pk02_wall06a_C.tga
	pk02_wall06b_C.tga
	pk02_wall07a_C.tga
	pk02_wall07b_C.tga
	*/
	
	public void init(GL2 gl) {
		try {
			//WALL		
			wallTextures.put('a', loadTexture("pk02_wall_a", gl));
			wallTextures.put('b', loadTexture("pk02_wall_b", gl));
			wallTextures.put('c', loadTexture("pk02_wall_c", gl));
			wallTextures.put('d', loadTexture("pk02_wall_d", gl));
			wallTextures.put('e', loadTexture("pk02_wall_e", gl));
			wallTextures.put('f', loadTexture("pk02_wall_f", gl));
			wallTextures.put('g', loadTexture("pk02_wall_g", gl));			
			
			//FLOOR
			floorTextures.put(1, loadTexture("pk02_floor_1", gl));
			floorTextures.put(2, loadTexture("pk02_floor_2", gl));
			floorTextures.put(3, loadTexture("pk02_floor_3", gl));
			floorTextures.put(4, loadTexture("pk02_floor_4", gl));
			floorTextures.put(5, loadTexture("pk02_floor_5", gl));
			floorTextures.put(6, loadTexture("pk02_floor_6", gl));
			
			//CEILING
			Texture c02 = loadTexture("pk02_ceiling02_C", gl);
			ceilingTextures.put(new CeilingTexturePointer(1), c02);
			Texture c03 = loadTexture("pk02_ceiling03_C", gl);
			ceilingTextures.put(new CeilingTexturePointer(2), c03);			
			
			//SWITCH
			Texture s01 = loadTexture("pk02_switches01a_C", gl);
			switchTextures.put(new WallTexturePointer(1, WallFace.Right), s01);
			switchTextures.put(new WallTexturePointer(1, WallFace.Left), s01);						
			Texture s02 = loadTexture("pk02_switches01b_C", gl);
			switchTextures.put(new WallTexturePointer(1, WallFace.Front), s02);		
			Texture s03 = loadTexture("pk02_switches01c_C", gl);
			switchTextures.put(new WallTexturePointer(1, WallFace.Back), s03);
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	private Texture loadTexture(String name, GL2 gl) throws Exception {
		Texture t = TextureIO.newTexture(
			Maze.class.getResourceAsStream("/texture/" + name + ".tga"), true, TextureIO.TGA);
		//return t.getTextureObject(gl);
		return t;
	}
	
	public Texture getWallTexture(char type) {
		if (!wallTextures.containsKey(type)) {
			throw new IllegalArgumentException("No wall texture [" + type + "]");
		}	
		return wallTextures.get(type);
	}
	
	public Texture getCeilingTexture(int coordX, int coordZ) {
		int tp = coordX%2+1;
		CeilingTexturePointer p = new CeilingTexturePointer(tp);
		if (!ceilingTextures.containsKey(p)) {
			throw new IllegalArgumentException("No ceiling texture for " + tp);
		}	
		return ceilingTextures.get(p);
	}
	
	public Texture getFloorTexture(int type) {
		if (!floorTextures.containsKey(type)) {
			throw new IllegalArgumentException("No floor texture for " + type);
		}	
		return floorTextures.get(type);
	}
	
	public Texture getSwitchTexture(WallFace face) {
		WallTexturePointer p = new WallTexturePointer(1, face);
		if (!switchTextures.containsKey(p)) {
			throw new IllegalArgumentException("No switch texture for " + p + "/" + face);
		}
		return switchTextures.get(p);
	}
	
	public enum WallFace {
		Front,
		Back,
		Right,
		Left		
	}
	
	private static final class WallTexturePointer {
		
		private int type;
		private WallFace face;
		
		WallTexturePointer(int type, WallFace face) {
			super();
			this.type = type;
			this.face = face;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((face == null) ? 0 : face.hashCode());
			result = prime * result + type;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			WallTexturePointer other = (WallTexturePointer) obj;
			if (face != other.face)
				return false;
			if (type != other.type)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "WallTexturePointer [type=" + type + ", face=" + face + "]";
		}				
	}
	
	
	private static final class CeilingTexturePointer {
		
		private int type;
		
		CeilingTexturePointer(int type) {
			super();
			this.type = type;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + type;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CeilingTexturePointer other = (CeilingTexturePointer) obj;
			if (type != other.type)
				return false;
			return true;
		}		
	}
}
