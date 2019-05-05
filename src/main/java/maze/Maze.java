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
	
	public void init(GL2 gl) {
		textureLibrary.init(gl);
		gl.glNewList(2, GL2.GL_COMPILE);
		char[][] cfg = read();
		for (int i=0; i<cfg.length; i++) {
			char[] row = cfg[i];
			for (int j=0; j<cfg.length; j++) {
				//floor
				Floor floor = new Floor(i, j, textureLibrary);
				floor.init(gl);
				//ceiling
				Ceiling ceiling = new Ceiling(i, j, textureLibrary);
				ceiling.init(gl);
				//
				char col = row[j];
				if ('W'==col) {
					Wall wall = new Wall(i, j, textureLibrary);
					walls.add(wall);
					wall.init(gl);
				} else if ('S'==col) {
					Switch s = new Switch(i, j, textureLibrary);
					//walls.add(wall);
					s.init(gl);
				}
			}
		}
		gl.glEndList();
	}
	
	public void display(GL2 gl) {
		gl.glCallList(2);
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
