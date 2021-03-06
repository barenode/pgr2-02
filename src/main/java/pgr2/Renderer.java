package pgr2;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JOptionPane;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Renderer  implements KeyListener, MouseMotionListener, GLEventListener, MouseListener {

    private static final int forward = KeyEvent.VK_W;
    private static final int backward = KeyEvent.VK_S;
    private static final int leftward = KeyEvent.VK_A;
    private static final int rightward = KeyEvent.VK_D;
    private static final int space = KeyEvent.VK_SPACE;
    private static final int shift = KeyEvent.VK_SHIFT;
    private static final int mouseSpeed = 1;
    private static final int keySpeed = 1;
    private static final int rotVMax = 1;
    private static final int rotVMin = -1;
    
    private final Map<Integer, Boolean> keys = new ConcurrentHashMap<Integer, Boolean>();
    private final float posY = -0.5f; 
    
    private float[] modelview = new float[16];
    private final GLCanvas canvas;
    private final Maze maze;
    private final Robot robot;
    private float rotX, rotY, rotZ, rotV;
    private float posX, posZ;
    private int centerX, centerY;
    private long lastTime = -1;   
    
    GLUT glut = new GLUT();
    GLU glu = new GLU();    

    public Renderer(final GLCanvas canvas) {
    	super();
    	this.canvas = canvas;
    	this.maze = new Maze(this, canvas);
    	init();
    	
    	Robot r = null;
        try {
            r = new Robot();
        } catch (final AWTException e) {
            throw new IllegalStateException(e);            
        }
        
        this.robot = r;
        canvas.addKeyListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(this);
        canvas.addGLEventListener(this);                
    }    
    
    private void init() {    	
    	keys.clear();
    	modelview = new float[16];
    	// setup the modelview matrix
        for (int i = 0; i < 4; i++)
            modelview[i * 5] = 1.0f;
        
        rotX = rotY = rotZ = rotV = 0f;
        posX = posZ = -5f;
    }

    public float getPosX() {
		return posX;
	}

	public float getPosZ() {
		return posZ;
	}

    public float[] getViewMatrix() {
        calculatePosition();
        calculateModelview();
        return modelview;
    }

    private void calculatePosition() {
    

        if (lastTime == -1) {
            lastTime = System.nanoTime();
        }
        double mul = 1.0;

        Boolean value = null;
        if ((value = keys.get(shift)) != null && value == true)
            mul = 0.1;

        final double speed = keySpeed * -((lastTime - (lastTime = System.nanoTime())) / 10E7) * mul;

        float newPosX = posX;
        float newPosZ = posZ;
        
        if ((value = keys.get(forward)) != null && value == true) {
        	newPosX -= Math.sin(rotY) * speed;
        	newPosZ += Math.cos(rotY) * speed;
        }

        if ((value = keys.get(backward)) != null && value == true) {
        	newPosX += Math.sin(rotY) * speed;
        	newPosZ -= Math.cos(rotY) * speed;
        }

        if ((value = keys.get(leftward)) != null && value == true) {
        	newPosX += Math.cos(rotY) * speed;
        	newPosZ += Math.sin(rotY) * speed;
        }

        if ((value = keys.get(rightward)) != null && value == true) {
        	newPosX -= Math.cos(rotY) * speed;
        	newPosZ -= Math.sin(rotY) * speed;
        }
        
        Vec2D dest = maze.collide(posX, posZ, newPosX, newPosZ);
        
        posX = dest.x;
        posZ = dest.y;
    }

    private void calculateModelview() {  
        final float sinX = (float) Math.sin(rotX);
        final float sinY = (float) Math.sin(rotY);
        final float sinZ = (float) Math.sin(rotZ);

        final float cosX = (float) Math.cos(rotX);
        final float cosY = (float) Math.cos(rotY);
        final float cosZ = (float) Math.cos(rotZ);

        modelview[0] = cosY * cosZ + sinY * sinX * sinZ;
        modelview[1] = cosX * sinZ;
        modelview[2] = -sinY * cosZ + cosY * sinX * sinZ;
        modelview[4] = -cosY * sinZ + sinY * sinX * cosZ;
        modelview[5] = cosX * cosZ;
        modelview[6] = sinY * sinZ + cosY * sinX * cosZ;
        modelview[8] = sinY * cosX;
        modelview[9] = -sinX;
        modelview[10] = cosY * cosX;

        modelview[12] = modelview[0] * posX + modelview[4] * posY + modelview[8] * posZ;
        modelview[13] = modelview[1] * posX + modelview[5] * posY + modelview[9] * posZ;
        modelview[14] = modelview[2] * posX + modelview[6] * posY + modelview[10] * posZ;
    }
    
    public void setPosition(float x, float z) {            	
    	posX = x;
        posZ = z;
    }    
    
    @Override
    public void init(final GLAutoDrawable drawable) {        	
		GL2 gl = drawable.getGL().getGL2();	
		gl.glEnable(GL2.GL_TEXTURE_2D);	
		gl.glMatrixMode(GL2.GL_MODELVIEW);		
		maze.init(gl);
    }
    
    @Override
    public void reshape(final GLAutoDrawable drawable, final int x, final int y, final int width, final int height) {    
    	rotV = 0;
        final Rectangle r = canvas.getParent().getBounds();
        final Point p = canvas.getParent().getLocationOnScreen();
        centerX = r.x + p.x + width / 2;
        centerY = r.y + p.y + height / 2;
        Boolean value = null;
        if ((value = keys.get(space)) != null && value == true)
            if (robot != null)
                robot.mouseMove(centerX, centerY);
    }
    
    @Override
    public void mouseMoved(final MouseEvent e) {
        if (pressed) {
            rotY -= (centerX - e.getXOnScreen()) / 1000.0 * mouseSpeed;
            rotV -= (centerY - e.getYOnScreen()) / 1000.0 * mouseSpeed;

            if (rotV > rotVMax)
                rotV = rotVMax;

            if (rotV < rotVMin)
                rotV = rotVMin;

            rotX = (float) Math.cos(rotY) * rotV;
            rotZ = (float) Math.sin(rotY) * rotV;

            if (robot != null)
                robot.mouseMove(centerX, centerY);
        }
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void keyPressed(final KeyEvent e) {    
        keys.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(final KeyEvent e) {    
        keys.put(e.getKeyCode(), false);
    }

    @Override public void keyTyped(final KeyEvent e){}    

    @Override public void display(final GLAutoDrawable drawable)
    {
		GL2 gl = drawable.getGL().getGL2();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glClearColor(0f, 0f, 0f, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		
		gl.glLoadIdentity();		

		final Rectangle r = canvas.getParent().getBounds();
		glu.gluPerspective(45, r.width / (float) r.height, 0.1f, 300.0f);		
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		
		gl.glMultMatrixf(getViewMatrix(), 0);
		
		//gl.glCallList(2);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		maze.display(drawable);
		
		
    	
    }

    public void dispose(GLAutoDrawable drawable)
    {
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private boolean pressed = false;
	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;		
	}
	
	public void onMazeCompleted() {
		 //JOptionPane.showConfirmDialog(null, "Mission Completed!", "Info", JOptionPane.DEFAULT_OPTION);		
		 init();
	}
}