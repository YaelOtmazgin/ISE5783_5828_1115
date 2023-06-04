/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author Dell
 *
 */
public class PointLight extends Light implements LightSource {
	private Point position;
	private double kC = 1, kL = 0, kQ = 0;
	
	/**
	 * A Ctor who gets the color, power of light and point(source of light).
	 * the light source cannot be on a body surface
	 * @param iA    - Fill the light intensity according to RGB
	 * @param point - position of source light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient
	 */
	public PointLight(Color iA, Point position, double kC, double kL, double kQ) {
		super(iA, new Double3(1));
		this.position = position;
		this.kC=kC;
		this.kL=kL;
		this.kQ=kQ;
	}

	
	
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;

	}

	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;

	}

	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}



	@Override
	public Color getIntensity(Point p) {
		var distance = p.distance(position); 
		return super.getIntensity()
					.reduce(kC + kL*distance + kQ*distance*distance);
	}



	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
}
