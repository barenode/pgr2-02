package maze;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Configuration {

	public void read() {
		try {
			char[][] maze = new char[20][20];
			int row = 0;	
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
			     String line3 = reader.readLine();
			     //System.out.println(line3);
			     for (int i=0; i<line1Parts.length; i++) {
			    	 String def = "(" + line1Parts[i] + line2Parts[i] + ")";
			    	 System.out.println(def);
			     }		
			     row++;
			}			
			//return maze;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void main(String[] args) {
		new Configuration().read();
	}
}
