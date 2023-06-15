package lighting;

import primitives.*;

/** The class represents a point light source such as a simple lamp.
 * @author Menuha and Yael */
public class PointLight extends Light implements LightSource {
	
	private Point position;
	private double kC = 1, kL = 0, kQ = 0;
	
	/** A Ctor who gets the light source intensity and point(source of light).
	 * the light source cannot be on a body surface
	 * @param iL - light source intensity
	 * @param point - position of source light */
	public PointLight(Color iL, Point position) {
		super(iL);
		this.position = position;
	}

	/** sets kC value
	 * @param kC - new constant coefficient value
	 * @return the point light itself */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;

	}

	/** sets kL value
	 * @param kL - new Linear coefficient value
	 * @return the point light itself */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;

	}

	/** sets kQ value
	 * @param kQ - new Quadratic coefficient value
	 * @return the point light itself */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		var distance = getDistance(p); 
		return super.getIntensity()
					.reduce(kC + kL*distance + kQ*distance*distance);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
	
	@Override
	public double getDistance(Point p) {
		return position.distance(p);
	}
}
