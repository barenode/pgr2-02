package maze;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Configuration {

	public void read() {
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
			//return maze;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
