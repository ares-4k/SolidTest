//The class SolidLine represents a line which coordinates may be expressed in 2D or 3D
//Created by Sergio Ingletto (http://ingletto.net). You can do what you want with this code.

package solidTest;

//this class represents a line. It is used for both 2D and 3D

public class SolidLine {
	private int x1; //point 1 coordinate X
	private int y1; //point 1 coordinate Y
	private int z1; //point 1 coordinate Z
	private int x2; //point 2 coordinate X
	private int y2; //point 2 coordinate Y
	private int z2; //point 2 coordinate Z
	
	//constructor for 2D coordinates system
	public SolidLine(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	//constructor for 3D coordinates system
	public SolidLine(int x1, int y1, int z1, int x2, int y2, int z2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getZ1() {
		return z1;
	}
	public void setZ1(int z1) {
		this.z1 = z1;
	}
	public int getZ2() {
		return z2;
	}
	public void setZ2(int z2) {
		this.z2 = z2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
}
