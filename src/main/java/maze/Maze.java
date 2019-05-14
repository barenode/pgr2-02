package maze;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jogamp.opengl.GL2;

public class Maze {
	
	private TextureLibrary textureLibrary = new TextureLibrary();
	private List<Wall> walls = new ArrayList<>(); 
	private List<Switch> switches = new ArrayList<>(); 
	
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
			     System.out.println(line1Parts.length);
			     
			     String line2 = reader.readLine();
			     String[] line2Parts = line2.split("\\|");
			     System.out.println(line2Parts.length);
			     
			     //line separator
			     reader.readLine();
			     //System.out.println(line3);
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
						Switch s = new Switch(i, j, textureLibrary);
						switches.add(s);
			    	 }
			    	 if (isFloor(def)) {
			    		 Floor f = new Floor(i, j, def.replaceAll("s", " "), textureLibrary);
			    		 f.init(gl);
			    	 }
			     }		
			     j++;
			}	
		
		
//		char[][] cfg = read();
//		for (int i=0; i<cfg.length; i++) {
//			char[] row = cfg[i];
//			for (int j=0; j<cfg.length; j++) {
//				//floor
//				Floor floor = new Floor(i, j, textureLibrary);
//				floor.init(gl);
//				//ceiling
//				Ceiling ceiling = new Ceiling(i, j, textureLibrary);
//				ceiling.init(gl);
//				//
//				char col = row[j];
//				if ('W'==col) {
//					Wall wall = new Wall(i, j, textureLibrary);
//					walls.add(wall);
//					wall.init(gl);
//				} else if ('S'==col) {
//					Switch s = new Switch(i, j, textureLibrary);
//					switches.add(s);
//				}
//			}
//		}
		
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
	
	
	public void display(GL2 gl) {		
		gl.glCallList(2);
		switches.forEach(s->s.display(gl));
	}
	
	public Vec2D collide(float posX, float posY, float newPosX, float newPosY) {		
		return Intersect.collide(posX, posY, newPosX, newPosY, getSegments(posX, posY));
	}
	
	public List<Segment> getSegments(float posX, float posZ) {
		return walls.stream().map(w->w.getSegments()).flatMap(s->s.stream()).collect(Collectors.toList());
	}	
	
	private char[][] read() {
		try {
			char[][] maze = new char[20][20];
			int row = 0;	
			InputStream in = Maze.class.getResourceAsStream("/maze.cfg");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));		
			while(reader.ready()) {
			     String line = reader.readLine();
			     System.out.println(row + " : " + line + "");
			     for (int col=0; col<20; col++) {
			    	 maze[row][col] = line.charAt(col);
			     }
			     row++;
			}			
			return maze;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
