package geometries;

import primitives.*;

/** An abstract class responsible for all common operations on geometric shape 
 * @author Menuha and Yael */
public abstract class Geometry extends Intersectable {
	/** emission color value */
	protected Color emission = Color.BLACK;
	/** material of the shape */
	private Material material =  new Material();
	
	/** Find the normal (vertical) vector to the geometric shape at point p.
	 * @param p is a one point-type parameter [across the geometric shape]
	 * @return the normal vector on the given point. */
	public abstract Vector getNormal(Point point);
	
	/** @return the emission color of the shape */
	public Color getEmission() {
		return emission;
	}

	/** ------ set the emission ------
	 * @param newColor - the new color to set
	 * @return the Geometry itself */
	public Geometry setEmission(Color newColor) {
		emission = newColor;
		return this;
	}

	/**  --------------- getter -------------------
	 * @return the material of the geometry */
	public Material getMaterial() {
		return material;
	}

	/** ------ set the material  ------
	 * @param material - the new material to set
	 * @return the Geometry itself */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
}