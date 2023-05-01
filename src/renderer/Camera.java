package renderer;

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
		var yi = (i - (nY - 1) / 2.0) * ry;
		var xj = (j - (nX - 1) / 2.0) * rx;

		Point pij = pc;
		if (xj != 0)
			pij = pij.add(vRight.scale(xj));
		if (yi != 0)
			pij = pij.add(vUp.scale(-yi));

		Vector vij = pij.subtract(p0);

		return new Ray(p0, vij);
	}
}