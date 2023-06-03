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
	double kC, kL, kQ;
	
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

}
