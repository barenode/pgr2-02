package pgr2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Application {

	public void init() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
    	GLCapabilities capabilities = new GLCapabilities(profile);
    	GLCanvas canvas = new GLCanvas(capabilities);    	
    	
		new Renderer(canvas);
		Frame frame = new Frame("Stand Alone Complex v.1.0");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
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
		canvas.setSize(frame.getWidth(), frame.getHeight());		
		frame.pack();
		frame.setVisible(true);
        animator.start();	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Application().init());
	}
}
