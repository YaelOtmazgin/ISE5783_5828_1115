package renderer;

import java.util.MissingResourceException;

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
	
	/** The function defines every pixel's color
	 * @throws MissingResourceException if one of the fields is empty */
	public void renderImage () {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all fields", "RayTracerBase", "rayTracer");
		//throw new UnsupportedOperationException();
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				Color rayColor = castRay(j, i);
				imageWriter.writePixel(j, i, rayColor); 
			}
		}
	}
	
	/** returns color of pixel in current tracing ray
	 * @param j  - the pixel's index in the column
	 * @param i  - the pixel's index in the row
	 * @return */
	private Color castRay(int j, int i) {
		Ray ray = this.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
		Color rayColor = rayTracer.traceRay(ray);
		return rayColor;
	}
	
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
	
}