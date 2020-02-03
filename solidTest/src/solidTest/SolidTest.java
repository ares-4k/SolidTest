// This class projects in Perspective an ArrayList of SolidLines given with perspectiveProjection
// and shows the projection on screen. 
//Created by Sergio Ingletto (http://ingletto.net). You can do what you want with this code.

package solidTest;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class SolidTest extends JFrame {
	
	private static final int WINDOW_HEIGHT=500;
	private static final int WINDOW_WIDTH=1000;
	private int CANVAS_HEIGHT=500;
	private int CANVAS_WIDTH=500;
	
	ArrayList<SolidLine> lines = new ArrayList<SolidLine>();  	//ArrayLinst containing the final projected lines
	ArrayList<SolidLine> lines3D = new ArrayList<SolidLine>(); 	//ArrayList containing the starting 3D lines
	
	//Sliders declaration:
	//Camera position
	JSlider slidCameraX = new JSlider(JSlider.HORIZONTAL, -400, 400, 250);
    JSlider slidCameraY = new JSlider(JSlider.HORIZONTAL, -400, 400, 150);  
    JSlider slidCameraZ = new JSlider(JSlider.HORIZONTAL, -400, 400, 120); 
    //Camera rotation
    JSlider slidCameraRX = new JSlider(JSlider.HORIZONTAL, -180, 180, -80);  
    JSlider slidCameraRY = new JSlider(JSlider.HORIZONTAL, -180, 180, 0);  
    JSlider slidCameraRZ = new JSlider(JSlider.HORIZONTAL, -180, 180, -70); 
    //Display surface position respect to camera
    JSlider slidCameraEX = new JSlider(JSlider.HORIZONTAL, -400, 400, 250);  
    JSlider slidCameraEY = new JSlider(JSlider.HORIZONTAL, -400, 400, 250);  
    JSlider slidCameraEZ = new JSlider(JSlider.HORIZONTAL, -400, 400, 250);
	
    //Declare and assign the sliders initial values to the corresponding variables:
	//Camera position
    private double cx=slidCameraX.getValue();
    private double cy=slidCameraY.getValue();
    private double cz=slidCameraZ.getValue();
	//Camera rotation
    private double tx=Math.toRadians(slidCameraRX.getValue());
    private double ty=Math.toRadians(slidCameraRY.getValue());
    private double tz=Math.toRadians(slidCameraRZ.getValue());
	//Display surface position respect to camera
    private double ex=slidCameraEX.getValue();
    private double ey=slidCameraEY.getValue();
    private double ez=slidCameraEZ.getValue();
	 
	public SolidTest() {
        super("SolidTest");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel=new JPanel();				//JPanel to keep the sliders on the right
        panel.setLayout(new GridLayout(1, 2));	//Setting layout manager to GridLayout (1 row, 2 columns)
        
        //Telling the program to execute sliderChanged() when detecting sliders movement
        slidCameraX.addChangeListener(e -> sliderChanged() );
        slidCameraY.addChangeListener(e -> sliderChanged() );
        slidCameraZ.addChangeListener(e -> sliderChanged() );
        slidCameraRX.addChangeListener(e -> sliderChanged() );
        slidCameraRY.addChangeListener(e -> sliderChanged() );
        slidCameraRZ.addChangeListener(e -> sliderChanged() );
        slidCameraEX.addChangeListener(e -> sliderChanged() );
        slidCameraEY.addChangeListener(e -> sliderChanged() );
        slidCameraEZ.addChangeListener(e -> sliderChanged() );
        //Configuring camera position sliders style
        slidCameraX.setMajorTickSpacing(200);
        slidCameraX.setMinorTickSpacing(10);
        slidCameraX.setPaintLabels(true);
        slidCameraY.setMajorTickSpacing(200);
        slidCameraY.setMinorTickSpacing(10);
        slidCameraY.setPaintLabels(true);
        slidCameraZ.setMajorTickSpacing(200);
        slidCameraZ.setMinorTickSpacing(10);
        slidCameraZ.setPaintLabels(true);
        //Configuring camera rotation sliders style
        slidCameraRX.setMajorTickSpacing(90);
        slidCameraRX.setMinorTickSpacing(10);
        slidCameraRX.setPaintLabels(true);
        slidCameraRY.setMajorTickSpacing(90);
        slidCameraRY.setMinorTickSpacing(10);
        slidCameraRY.setPaintLabels(true);
        slidCameraRZ.setMajorTickSpacing(90);
        slidCameraRZ.setMinorTickSpacing(10);
        slidCameraRZ.setPaintLabels(true);
        //Configuring display surface position sliders style
        slidCameraEX.setMajorTickSpacing(200);
        slidCameraEX.setMinorTickSpacing(10);
        slidCameraEX.setPaintLabels(true);
        slidCameraEY.setMajorTickSpacing(200);
        slidCameraEY.setMinorTickSpacing(10);
        slidCameraEY.setPaintLabels(true);
        slidCameraEZ.setMajorTickSpacing(200);
        slidCameraEZ.setMinorTickSpacing(10);
        slidCameraEZ.setPaintLabels(true);
        
        panel.add(new JPanel());   		//add an empty JPanel to occupy the left section of panel
        JPanel sliders=new JPanel();	//JPanel to distribute the sliders properly
        sliders.setLayout(new GridLayout(9,2));
        
        //Adding labels and sliders to sliders JPanel
        sliders.add(new JLabel("Camera X position"));
        sliders.add(slidCameraX);
        sliders.add(new JLabel("Camera Y position"));
        sliders.add(slidCameraY);
        sliders.add(new JLabel("Camera Z position"));
        sliders.add(slidCameraZ);
        sliders.add(new JLabel("Camera X rotation"));
        sliders.add(slidCameraRX);
        sliders.add(new JLabel("Camera Y rotation"));
        sliders.add(slidCameraRY);
        sliders.add(new JLabel("Camera Z rotation"));
        sliders.add(slidCameraRZ);
        sliders.add(new JLabel("Display surface  X position"));
        sliders.add(slidCameraEX);
        sliders.add(new JLabel("Display surface  Y position"));
        sliders.add(slidCameraEY);
        sliders.add(new JLabel("Display surface  Z position (FOV)"));
        sliders.add(slidCameraEZ);
        
        panel.add(sliders); //adding sliders to panel
        add(panel);			//adding panel to the JFrame
    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        //draw all the current lines contained in ArrayList lines
        for(int i=0; i<lines.size(); i++) {
        	g2d.drawLine(lines.get(i).getX1(), CANVAS_HEIGHT-lines.get(i).getY1(), lines.get(i).getX2(), CANVAS_HEIGHT-lines.get(i).getY2());
        }
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
 
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
        System.out.println("graphic initialized");
    }
    
    public ArrayList<SolidLine> perspectiveProjection(ArrayList<SolidLine> lines3D) {   //calculate the Perspective projection of 3D lines array
    	this.lines3D=new ArrayList<SolidLine>(lines3D);
    	lines.clear();	//clear any previously projected lines
    	for(int i=0; i<lines3D.size(); i++) {
    		SolidLine raw;
    		raw=projectLine(lines3D.get(i));  //temporary variable to contain the projected line
    		//check if any point of the projected line exceeds the selected canvas limits or else bad things happen
    		if((raw.getX2()<CANVAS_WIDTH&&raw.getX1()<CANVAS_WIDTH)&&(raw.getX2()>0&&raw.getX1()>0)&&(raw.getY2()<CANVAS_HEIGHT&&raw.getY1()<CANVAS_HEIGHT)&&(raw.getY2()>0&&raw.getY1()>0)) {
    			//add the projected line to the ArrayList
	        	this.lines.add(raw);
    		}
    	}
    	if(this.isVisible()) {
    		this.repaint();  //invoke the repaint() method to calculate the new projections and draw them if the graphic was initialized
    		Toolkit.getDefaultToolkit().sync();  //needed to avoid lag when not moving mouse over window during animations
    	}
    	return lines;
    }
    
    //method to project a given line in 3D coordinates onto the 2D plane
    public SolidLine projectLine(SolidLine lineIn) {
		int[] point2D1=projectPoint(lineIn.getX1(),lineIn.getY1(),lineIn.getZ1());
		int[] point2D2=projectPoint(lineIn.getX2(),lineIn.getY2(),lineIn.getZ2());
		SolidLine lineOut=new SolidLine(point2D1[0],point2D1[1],point2D2[0],point2D2[1]);
		return lineOut;
	}
    
    //method to project a given point in 3D coordinates onto the 2D plane
	public int[] projectPoint(int ax, int ay, int az) {
		//some variables to simplify things
		double x=ax-cx;
		double y=ay-cy;
		double z=az-cz;
		double cox=Math.cos(tx);
		double coy=Math.cos(ty);
		double coz=Math.cos(tz);
		double sx=Math.sin(tx);
		double sy=Math.sin(ty);
		double sz=Math.sin(tz);
		//calculate the projection
		double dx=coy*(sz*y+coz*x)-sy*z;
		double dy=sx*(coy*z+sy*(sz*y+coz*x))+cox*(coz*y-sz*x);
		double dz=cox*(coy*z+sy*(sz*y+coz*x))-sx*(coz*y-sz*x);
		double bx=(ez/dz)*dx+ex;	//projected point X coordinate
		double by=(ez/dz)*dy+ey;	//projected point Y coordinate
		int[] ret=new int[2];
		ret[0]=(int) bx;
		ret[1]=(int) by;
		return ret.clone();
	}
	
	//update the values of the variables when detecting slider movement
	void sliderChanged() {
		cx=slidCameraX.getValue();
		cy=slidCameraY.getValue();
		cz=slidCameraZ.getValue();
		tx=Math.toRadians(slidCameraRX.getValue());
		ty=Math.toRadians(slidCameraRY.getValue());
		tz=Math.toRadians(slidCameraRZ.getValue());
		ex=slidCameraEX.getValue();
		ey=slidCameraEY.getValue();
		ez=slidCameraEZ.getValue();
		//recalculate the projection with the new settings
		perspectiveProjection(lines3D);
	}

	public double getCameraX() {
		return cx;
	}

	public void setCameraX(double cx) {
		this.cx = cx;
	}

	public double getCameraY() {
		return cy;
	}

	public void setCameraY(double cy) {
		this.cy = cy;
	}

	public double getCameraZ() {
		return cz;
	}

	public void setCameraZ(double cz) {
		this.cz = cz;
	}

	public double getCameraRotationX() {    	 //Get the current camera X-axis rotation in radians
		return tx;
	}

	public void setCameraRotationX(double tx) {  //Set the camera X-axis rotation in radians
		this.tx = tx;
	}

	public double getCameraRotationY() {   		//Get the current Y camera Y-axis rotation in radians
		return ty;
	}

	public void setCameraRotationY(double ty) {	//Set the camera X-axis rotation in radians
		this.ty = ty;
	}

	public double getCameraRotationZ() {   		//Get the current camera Z-axis rotation in radians
		return tz;
	}

	public void setCameraRotationZ(double tz) {	//Set the camera X-axis rotation in radians
		this.tz = tz;
	}

	public double getDisplaySurfaceX() {				//Get the Display surface X position relative to camera
		return ex;
	}

	public void setDisplaySurfaceX(double ex) {		//Set the Display surface X position relative to camera
		this.ex = ex;
	}

	public double getDisplaySurfaceY() {				//Get the Display surface Y position relative to camera
		return ey;
	}

	public void setDisplaySurfaceT(double ey) {		//Set the Display surface Y position relative to camera
		this.ey = ey;
	}

	public double getDisplaySurfaceZ() {				//Get the Display surface Z position relative to camera
		return ez;
	}

	public void setDisplaySurfaceZ(double ez) {		//Set the Display surface Z position relative to camera
		this.ez = ez;
	}

	public ArrayList<SolidLine> getLines() {   	//get the projected lines in 2D
		return lines;
	}

	public ArrayList<SolidLine> getLines3D() {	//get the input lines in 3D
		return lines3D;
	}

	public int getCANVAS_HEIGHT() {
		return CANVAS_HEIGHT;
	}

	public void setCANVAS_HEIGHT(int cANVAS_HEIGHT) {
		CANVAS_HEIGHT = cANVAS_HEIGHT;
	}

	public int getCANVAS_WIDTH() {
		return CANVAS_WIDTH;
	}

	public void setCANVAS_WIDTH(int cANVAS_WIDTH) {
		CANVAS_WIDTH = cANVAS_WIDTH;
	}
	
	
	

}
