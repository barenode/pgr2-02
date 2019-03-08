package custom;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class App {
	private static final int FPS = 60;
	
	private Frame testFrame;
	
	public void start() {
		try {
			testFrame = new Frame("TestFrame");
			testFrame.setSize(512, 384);			
			
			// setup OpenGL Version 2
			GLProfile profile = GLProfile.get(GLProfile.GL2);
			GLCapabilities capabilities = new GLCapabilities(profile);
			capabilities.setRedBits(8);
			capabilities.setBlueBits(8);
			capabilities.setGreenBits(8);
			capabilities.setAlphaBits(8);
			capabilities.setDepthBits(24);
			
			GLCanvas canvas = new GLCanvas(capabilities);
			canvas.setSize(new Dimension(600, 400));
			testFrame.add(canvas);
			
			//renderer
			Renderer renderer = new Renderer();
			canvas.addGLEventListener(renderer);
			
			final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
			testFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					new Thread() {
						@Override
						public void run() {
							if (animator.isStarted())
								animator.stop();
							System.exit(0);
						}
					}.start();
				}
			});
			testFrame.setTitle("TITLE");
			testFrame.pack();
			testFrame.setVisible(true);
			animator.start(); // start the animation loop
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new App().start());
	}
}
