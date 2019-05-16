package pgr2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    	canvas.setSize(1920, 1017);		
		new Renderer(canvas);
		Frame frame = new Frame("Stand Alone Complex v.1.0");
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
		SwingUtilities.invokeLater(() -> new Application().init());
	}
}
