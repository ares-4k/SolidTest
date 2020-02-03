//Created by Sergio Ingletto (http://ingletto.net). You can do what you want with this code.

package solidTest;

import java.util.ArrayList;

public class MainCalc {

	public static void main(String[] args) {
		//Create a new interface object and start the graphic
		SolidTest pan = new SolidTest();
		pan.start();
		
		ArrayList<SolidLine> lines3D = new ArrayList<SolidLine>();  //ArrayList of SolidLines that will be filled with lines representing shapes
		//Create 3 different cubes and add each one to the array of lines
		lines3D.addAll(createCube(10,10,0,100));
		lines3D.addAll(createCube(10,120,0,50));
		lines3D.addAll(createCube(70,120,0,20));
		//Create three axes in the origin representing the three coordinates
		lines3D.add(new SolidLine(0,0,0,50,0,0));
		lines3D.add(new SolidLine(0,0,0,0,50,0));
		lines3D.add(new SolidLine(0,0,0,0,0,50));
		//Send the generated lines to the SolidTest object which will project them in perspective and print them on screen
		pan.perspectiveProjection(lines3D);
	}
	
	//createCube gives the lines of a cube created in the desired x,y,z location, of size l 
	static private ArrayList<SolidLine> createCube(int x, int y, int z, int l){
		ArrayList<SolidLine> cube = new ArrayList<SolidLine>();
		cube.add(new SolidLine(x,y,z+l,x+l,y,z+l));
		cube.add(new SolidLine(x+l,y,z+l,x+l,y+l,z+l));
		cube.add(new SolidLine(x+l,y+l,z+l,x,y+l,z+l));
		cube.add(new SolidLine(x,y+l,z+l,x,y,z+l));
		cube.add(new SolidLine(x,y,z,x+l,y,z));
		cube.add(new SolidLine(x+l,y,z,x+l,y+l,z));
		cube.add(new SolidLine(x+l,y+l,z,x,y+l,z));
		cube.add(new SolidLine(x,y+l,z,x,y,z));
		cube.add(new SolidLine(x+l,y+l,z,x+l,y+l,z+l));
		cube.add(new SolidLine(x,y+l,z,x,y+l,z+l));
		cube.add(new SolidLine(x,y,z,x,y,z+l));
		cube.add(new SolidLine(x+l,y,z,x+l,y,z+l));
		return cube;
	}
	
}
