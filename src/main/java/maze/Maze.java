package maze;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

public class Maze {
	
	private final Renderer renderer;	
	private final TextureLibrary textureLibrary = new TextureLibrary();
	private final List<Wall> walls = new ArrayList<>(); 
	private final List<Switch> switches = new ArrayList<>(); 
	private final Set<KeyPos> visitedSwitches = new HashSet<>();	
	private final InfoBar infoBar;
	
	private int totalSwitches = 0;	
	
	public Maze(Renderer renderer, GLCanvas canvas) {
		super();
		this.renderer = renderer;
		this.infoBar = new InfoBar(canvas, this);
	}
	
	public void init(GL2 gl) {
		try {			
			textureLibrary.init(gl);
			gl.glNewList(2, GL2.GL_COMPILE);
			
			int j = 0;	
			InputStream in = Maze.class.getResourceAsStream("/maze.cfg.2");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));		
			while(reader.ready()) {
			     String line1 = reader.readLine();			    
			     String[] line1Parts = line1.split("\\|");			     
			     String line2 = reader.readLine();
			     String[] line2Parts = line2.split("\\|");			     
			     //line separator
			     reader.readLine();
			     for (int i=0; i<line1Parts.length; i++) {
					//ceiling
					Ceiling ceiling = new Ceiling(i, j, textureLibrary);
					ceiling.init(gl);
			    	 
			    	 String def = line1Parts[i] + line2Parts[i];
			    	 if (isWall(def)) {
			    		 Wall wall = new Wall(i, j, def, textureLibrary);
			    		 walls.add(wall);
			    		 wall.init(gl);
			    	 }
			    	 if (isSwitch(def)) {
			    		totalSwitches++;
						Switch s = new Switch(this, renderer, i, j, textureLibrary);
						switches.add(s);
			    	 }
			    	 if (isFloor(def)) {
			    		 Floor f = new Floor(i, j, def.replaceAll("s", " "), textureLibrary);
			    		 f.init(gl);
			    	 }
			     }		
			     j++;
			}		
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
		gl.glEndList();
	}
	
	private boolean isSwitch(String def) {
		return 
			def.indexOf("s")>=0;
	} 
	
	private boolean isWall(String def) {
		return 
			def.indexOf("a")>=0 ||
			def.indexOf("b")>=0 ||
			def.indexOf("c")>=0 ||
			def.indexOf("d")>=0 ||
			def.indexOf("e")>=0 ||
			def.indexOf("f")>=0 ||
			def.indexOf("g")>=0;			
	}
	
	private boolean isFloor(String def) {
		return 
			def.indexOf("1")>=0 ||
			def.indexOf("2")>=0 ||
			def.indexOf("3")>=0 ||
			def.indexOf("4")>=0 ||
			def.indexOf("5")>=0 ||
			def.indexOf("6")>=0;			
	}
	
	
	public void display(GLAutoDrawable drawable) {		
		GL2 gl = drawable.getGL().getGL2();
		gl.glCallList(2);
		switches.forEach(s->s.display(gl));
		infoBar.display(drawable);
	}
	
	public Vec2D collide(float posX, float posY, float newPosX, float newPosY) {		
		return Intersect.collide(posX, posY, newPosX, newPosY, getSegments(posX, posY));
	}
	
	public List<Segment> getSegments(float posX, float posZ) {
		return walls.stream().map(w->w.getSegments()).flatMap(s->s.stream()).collect(Collectors.toList());
	}	
	
	public int getTotalSwitches() {
		return totalSwitches;
	}
	
	public int getVisitedSwitches() {
		return visitedSwitches.size();
	}
	
	public void onSwitchVisited(int coordX, int coordZ) {
		KeyPos pos = new KeyPos(coordX, coordZ);
		if (!visitedSwitches.contains(pos)) {
			visitedSwitches.add(pos);
		}
		if (visitedSwitches.size()==totalSwitches) {
			renderer.onMazeCompleted();
			visitedSwitches.clear();
		}
	}
	
	private static final class KeyPos {
		private final int coordX;
		private final int coordZ;	
		public KeyPos(int coordX, int coordZ) {
			super();
			this.coordX = coordX;
			this.coordZ = coordZ;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + coordX;
			result = prime * result + coordZ;
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
			KeyPos other = (KeyPos) obj;
			if (coordX != other.coordX)
				return false;
			if (coordZ != other.coordZ)
				return false;
			return true;
		}	
	}
}
