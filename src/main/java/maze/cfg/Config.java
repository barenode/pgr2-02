package maze.cfg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jogamp.opengl.GL2;

public class Config {
	
	public void init(GL2 gl) {
		char[][] cfg = read();
		for (int i=0; i<cfg.length; i++) {
			char[] row = cfg[i];
			for (int j=0; j<cfg.length; j++) {
				char col = row[j];
				if ('W'==col) {
					new Wall(i, j).init(gl);
				}
			}
		}
	}	 
	
	
	private char[][] read() {
		try {
			char[][] maze = new char[20][20];
			int row = 0;	
			InputStream in = Config.class.getResourceAsStream("/maze.cfg");
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
