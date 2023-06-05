package geometries;

import primitives.*;

/** An abstract class responsible for all common operations on geometric shape 
 * @author Menuha and Yael */
public abstract class Geometry extends Intersectable {
	protected Color emmission = Color.BLACK;
	
	private Material material =  new Material();
	
	/** Find the normal (vertical) vector to the geometric shape at point p.
	 * @param p is a one point-type parameter [across the geometric shape]
	 * @return the normal vector on the given point. */
	public abstract Vector getNormal(Point point);
	
	/** @return the color of the shape */
	public Color getEmmission() {
		return emmission;
	}

	/** ------ set the emission ------
	 * @param newColor - the new color to set
	 * @return the Geometry itself */
	public Geometry setEmission(Color newColor) {
		emmission = newColor;
		return this;
	}

	/**
	 *  --------------- getter -------------------
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * ------ set the material  ------
	 * @param material - set material to geometry
	 * @return itself geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
}