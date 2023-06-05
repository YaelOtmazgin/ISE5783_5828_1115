/**
 * 
 */
package primitives;

/**The class represents the Phong Reflectance Model.
 * @author Dell
 *
 */
public class Material {
	public Double3 kD = new Double3(0), kS = new Double3(0);
	public int nShininess = 0;
	
	/**
	 * ------------- setter -----------------
	 * @param kD the kD to set
	 * @return itself material
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	/**
	 * ------------- setter -----------------
	 * @param kD the kD to set (type double)
	 * @return itself material
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	
	/**
	 * ------------- setter -----------------
	 * @param kS the kS to set
	 * @return itself material
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	
	/**
	 * ------------- setter -----------------
	 * @param kS the kS to set (type double)
	 * @return itself material
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * ------------- setter -----------------
	 * @param nShininess the nShininess to set
	 * @return itself material
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
