package maze;

import java.util.HashMap;
import java.util.Map;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureLibrary {
	
	Map<WallTexturePointer, Texture> wallTextures = new HashMap<>();
	Map<CeilingTexturePointer, Texture> floorTextures = new HashMap<>();
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
			//1
			Texture w01a = loadTexture("pk02_wall01a_C", gl);
			wallTextures.put(new WallTexturePointer(1, WallFace.Front), w01a);
			wallTextures.put(new WallTexturePointer(1, WallFace.Right), w01a);
			wallTextures.put(new WallTexturePointer(1, WallFace.Back), w01a);
			wallTextures.put(new WallTexturePointer(1, WallFace.Left), w01a);
			//2
			Texture w02a = loadTexture("pk02_wall02a_C", gl);
			Texture w02b = loadTexture("pk02_wall02b_C", gl);
			Texture w02c = loadTexture("pk02_wall02c_C", gl);
			wallTextures.put(new WallTexturePointer(2, WallFace.Front), w02a);
			wallTextures.put(new WallTexturePointer(2, WallFace.Right), w02b);
			wallTextures.put(new WallTexturePointer(2, WallFace.Back), w02c);
			wallTextures.put(new WallTexturePointer(2, WallFace.Left), w02b);
			//3			
			Texture w03a = loadTexture("pk02_wall03a_C", gl);
			Texture w03b = loadTexture("pk02_wall03b_C", gl);			
			wallTextures.put(new WallTexturePointer(3, WallFace.Front), w03a);
			wallTextures.put(new WallTexturePointer(3, WallFace.Right), w03b);
			wallTextures.put(new WallTexturePointer(3, WallFace.Back), w03a);
			wallTextures.put(new WallTexturePointer(3, WallFace.Left), w03b);
			//4
			Texture w04a = loadTexture("pk02_wall04a_C", gl);
			Texture w04b = loadTexture("pk02_wall04b_C", gl);
			wallTextures.put(new WallTexturePointer(4, WallFace.Front), w04a);
			wallTextures.put(new WallTexturePointer(4, WallFace.Right), w04b);
			wallTextures.put(new WallTexturePointer(4, WallFace.Back), w04a);
			wallTextures.put(new WallTexturePointer(4, WallFace.Left), w04b);
			//5
			Texture w05a = loadTexture("pk02_wall05a_C", gl);
			Texture w05b = loadTexture("pk02_wall05b_C", gl);
			Texture w05c = loadTexture("pk02_wall05c_C", gl);
			wallTextures.put(new WallTexturePointer(5, WallFace.Front), w05a);
			wallTextures.put(new WallTexturePointer(5, WallFace.Right), w05b);
			wallTextures.put(new WallTexturePointer(5, WallFace.Back), w05c);
			wallTextures.put(new WallTexturePointer(5, WallFace.Left), w05b);
			//6
			Texture w06a = loadTexture("pk02_wall06a_C", gl);
			Texture w06b = loadTexture("pk02_wall06b_C", gl);
			wallTextures.put(new WallTexturePointer(6, WallFace.Front), w06a);
			wallTextures.put(new WallTexturePointer(6, WallFace.Right), w06b);
			wallTextures.put(new WallTexturePointer(6, WallFace.Back), w06a);
			wallTextures.put(new WallTexturePointer(6, WallFace.Left), w06b);
			//7
			Texture w07a = loadTexture("pk02_wall07a_C", gl);
			Texture w07b = loadTexture("pk02_wall07b_C", gl);
			wallTextures.put(new WallTexturePointer(7, WallFace.Front), w07a);
			wallTextures.put(new WallTexturePointer(7, WallFace.Right), w07b);
			wallTextures.put(new WallTexturePointer(7, WallFace.Back), w07a);
			wallTextures.put(new WallTexturePointer(7, WallFace.Left), w07b);
			
			//FLOOR
			Texture f08a = loadTexture("pk02_floor08a_C", gl);
			floorTextures.put(new CeilingTexturePointer(1), f08a);
			Texture f08b = loadTexture("pk02_floor08b_C", gl);
			floorTextures.put(new CeilingTexturePointer(2), f08b);
			Texture f09a = loadTexture("pk02_floor09a_C", gl);
			floorTextures.put(new CeilingTexturePointer(3), f09a);
//			Texture f13a = loadTexture("pk02_floor13a_C", gl);
//			floorTextures.put(new CeilingTexturePointer(4), f13a);
			Texture fcyl02 = loadTexture("pk02_floor_cyl02_C", gl);
			floorTextures.put(new CeilingTexturePointer(4), fcyl02);
			
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
	
	public Texture getWallTexture(int coordX, int coordZ, WallFace face) {
		int tp = (coordX + coordZ)%7+1;
		WallTexturePointer p = new WallTexturePointer(tp, face);
		if (!wallTextures.containsKey(p)) {
			throw new IllegalArgumentException("No wall texture for " + tp + "/" + face);
		}	
		return wallTextures.get(p);
	}
	
	public Texture getCeilingTexture(int coordX, int coordZ) {
		int tp = coordX%2+1;
		CeilingTexturePointer p = new CeilingTexturePointer(tp);
		if (!ceilingTextures.containsKey(p)) {
			throw new IllegalArgumentException("No ceiling texture for " + tp);
		}	
		return ceilingTextures.get(p);
	}
	
	public Texture getFloorTexture(int coordX, int coordZ) {
		int tp = (coordX + coordZ)%4+1;
		CeilingTexturePointer p = new CeilingTexturePointer(tp);
		if (!floorTextures.containsKey(p)) {
			throw new IllegalArgumentException("No floor texture for " + tp);
		}	
		return floorTextures.get(p);
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
