package renderer;

import java.util.MissingResourceException;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/** Shoot rays from the center of projection through the view plane pixels for
 * "see" objects in the this 3D world 
 * @author Menuha and Yael */
public class Camera {
	private Point p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	private int numOfRays = 1;

	/** Location of the camera lens
	 * @return the p0 a location of the camera lens */
	public Point getP0() {
		return p0;
	}

	/** The Vector starting at P0 and pointing upwards
	 *  @return the vUp */
	public Vector getvUp() {
		return vUp;
	}

	/** The Vector starting at P0 and pointing forward 
	 * @return the vTo */
	public Vector getvTo() {
		return vTo;
	}

	/** The Vector starting at P0 and pointing to the right
	 *  @return the vRight */
	public Vector getvRight() {
		return vRight;
	}
	
	/**
	 * A setter function for parameter numOfRays
	 * this function return the object - this for builder pattern
	 * 
	 * @param numOfRays int value
	 * */
	public Camera setNumOfRays(int numOfRays){
		if(numOfRays == 0)
			this.numOfRays=1;
		else
		 this.numOfRays = numOfRays;
		return this;
	}

	/** A camera constructor that receives two vectors in the direction of the
	 * camera(up,to) and point3d for the camera lens 
	 * @param p0  - location of the camera lens
	 * @param vTo - starting at P0 and pointing forward
	 * @param vUp - starting at P0 and pointing upwards
	 * @throws IllegalArgumentException if the vectors are not vertical*/
	public Camera(Point p0, Vector vTo, Vector vUp) {
		if (!Util.isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("Vectors are not vertical");
		this.p0 = p0;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		this.vRight = vTo.crossProduct(vUp).normalize();
	}

	/** setter for size of view plane
	 * @param width  - a width of plane view
	 * @param height - a height of plane view
	 * @return the camera itself */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/** setter for distance from camera to view plane
	 * @param distance - a distance from camera to view plane
	 * @return the camera itself */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/** The function builds a ray through a given pixel (j,i) within the grid of nX and nY 
	 * @param nX - the size of width
	 * @param nY - the size of height
	 * @param j  - the index in the column
	 * @param i  - the index in the row
	 * @return ray that passes in given pixel in the grid */
	public Ray constructRay(int nX, int nY, int j, int i) {
		// image center
		Point pc = p0.add(vTo.scale(distance));
		// ratio
		var ry = height / nY;
		var rx = width / nX;
		// pixel(i,j) center
		var yI = (i - (nY - 1) / 2.0) * ry;
		var xJ = (j - (nX - 1) / 2.0) * rx;

		Point pIJ = pc;
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(-yI));

		Vector vij = pIJ.subtract(p0);

		return new Ray(p0, vij);
	}
	
	/** sets imageWriter value
	 * @param imageWriter - new imageWriter value
	 * @return the camera itself */
	public Camera setImageWriter(ImageWriter imageWriter){
		this.imageWriter = imageWriter;
		return this;
	}
	
	/** sets rayTracer value
	 * @param rayTracer - new rayTracer value
	 * @return the camera itself */
	public Camera setRayTracer(RayTracerBase rayTracer){
		this.rayTracer = rayTracer;
		return this;
	}
	
	/** The function transfers beams from camera to pixel, tracks the beam
	 *  and receives the pixel color from the point of intersection.
	 * @throws MissingResourceException if one of the fields is empty */
	public void renderImage () {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all fields", "RayTracerBase", "rayTracer");
		//throw new UnsupportedOperationException();
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				/*Color rayColor = castRay(j, i);
				imageWriter.writePixel(j, i, rayColor); */
				if(numOfRays == 1 || numOfRays == 0){
					Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
					Color rayColor = rayTracer.traceRay(ray);
					imageWriter.writePixel(j, i, rayColor); 
					}
				else{	
					List<Ray> rays = constructBeamThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i,numOfRays);
					Color rayColor = rayTracer.traceRay(rays);
					imageWriter.writePixel(j, i, rayColor); 
					}
			}
		}		
	}		
		
	
	
	/** returns color of pixel in current tracing ray
	 * @param j  - the pixel's index in the column
	 * @param i  - the pixel's index in the row
	 * @return */
	/*private Color castRay(int j, int i) {
		Ray ray = this.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
		Color rayColor = rayTracer.traceRay(ray);
		return rayColor;
	}*/
	
	/** A function that creates a grid of lines
	 * @param interval int value
	 * @param color Color value */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				if(i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(i, j, color); 
			}
		}
	}
	
	/** A function that finally creates the image.
	 * This function delegates the function of a class imageWriter */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		
		imageWriter.writeToImage();
	}
	public List<Ray> constructBeamThroughPixel (int nX, int nY, int j, int i, int raysAmount){

		//The distance between the screen and the camera cannot be 0
        if (Util.isZero(distance)){
            throw new IllegalArgumentException("distance cannot be 0");
        }

		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //num of rays in each row or column

		if (numOfRays==1) 
			return List.of(constructRay(nX, nY, j, i));
//
		/*Point Pc;
		if (isZero(distance))
			Pc=p0;
		else
			Pc=p0.add(vTo.scale(distance));*/

		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;

//        
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray

        List<Ray> sample_rays = new LinkedList<>();
//
//        double Ry = height/nY; //The number of pixels on the y axis
//        double Rx = width/nX;  //The number of pixels on the x axis
//        double yi =  ((i - nY/2d)*Ry); //distance of original pixel from (0,0) on Y axis
////     double xj=   ((j - nX/2d)*Rx); //distance of original pixel from (0,0) on x axis
//        double pixel_Ry = Ry/num_of_sample_rays; //The height of each grid block we divided the parcel into
//        double pixel_Rx = Rx/num_of_sample_rays; //The width of each grid block we divided the parcel into

        for (int row = 0; row < numOfRays; ++row) {//foreach place in the pixel grid
            for (int column = 0; column < numOfRays; ++column) {
                sample_rays.add(constructRaysThroughPixel(PRy,PRx,Yi, Xj, row, column));//add the ray
            }
        }
        sample_rays.add(constructRay(nX, nY, j, i));//add the center screen ray
        return sample_rays;
	}

	 /**
     * In this function we treat each pixel like a little screen of its own and divide it to smaller "pixels".
     * Through each one we construct a ray. This function is similar to ConstructRayThroughPixel.
     * @param Ry height of each grid block we divided the pixel into
     * @param Rx width of each grid block we divided the pixel into
     * @param yi distance of original pixel from (0,0) on Y axis
     * @param xj distance of original pixel from (0,0) on X axis
     * @param j j coordinate of small "pixel"
     * @param i i coordinate of small "pixel"
     * @param distance distance of screen from camera
     * @return beam of rays through pixel
     */
    private Ray constructRaysThroughPixel(double Ry,double Rx, double yi, double xj, int j, int i){
        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

        double y_sample_i =  (i *Ry + Ry/2d); //The pixel starting point on the y axis
        double x_sample_j=   (j *Rx + Rx/2d); //The pixel starting point on the x axis

        Point Pij = Pc; //The point at the pixel through which a beam is fired
        //Moving the point through which a beam is fired on the x axis
        if (!Util.isZero(x_sample_j + xj))
        {
            Pij = Pij.add(vRight.scale(x_sample_j + xj));
        }
        //Moving the point through which a beam is fired on the y axis
        if (!Util.isZero(y_sample_i + yi)){
            Pij = Pij.add(vUp.scale(-y_sample_i -yi ));
        }
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0,Vij);//create the ray throw the point we calculate here
    }
	
}