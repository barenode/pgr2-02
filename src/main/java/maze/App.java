package maze;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class App {

	public void init() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
    	GLCapabilities capabilities = new GLCapabilities(profile);
    	GLCanvas canvas = new GLCanvas(capabilities);    	
    	canvas.setSize(1920, 1017);
    	
    	//SolidRenderer renderer = new SolidRenderer();//new jogl06pushpopclip.TestRenderer();
    	
//    	MazeRenderer renderer = new MazeRenderer();
//		canvas.addGLEventListener(renderer);
//		canvas.addMouseListener(renderer);
//		canvas.addMouseMotionListener(renderer);
//		canvas.addKeyListener(renderer);
		
		
		Renderer cam = new Renderer(canvas);
//		canvas.addGLEventListener(cam);
//		canvas.addMouseMotionListener(cam);
//		canvas.addKeyListener(cam);
		
		
		Frame frame = new Frame("Maze");
		//frame.setSize(1920, 1017);
		frame.add(canvas);
		
		FPSAnimator animator = new FPSAnimator(canvas, 60, true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted()) {
							animator.stop();
						}
						System.exit(0);
					}
				}.start();
			}
		});		
		
		frame.pack();
		frame.setVisible(true);
        animator.start();	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new App().init());
	}
}
