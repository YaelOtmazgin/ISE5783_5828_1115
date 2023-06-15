package primitives;

/** The class represents the Phong Reflectance Model.
 * @author Menuha and Yael */
public class Material {
	/** Diffusive */
	public Double3 kD = Double3.ZERO;
	/** Specular or Reflection */
	public Double3 kS = Double3.ZERO;
	/** Represents concentration of specular effect */
	public int nShininess = 0;
	
	/** ------------- setter -----------------
	 * @param kD - the Diffusive to set
	 * @return the material itself */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kD the Diffusive to set (type double)
	 * @return the material itself */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kS - the Specular to set
	 * @return the material itself */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kS - the Specular to set (type double)
	 * @return the material itself */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/** ------------- setter -----------------
	 * @param nShininess the nShininess to set
	 * @return the material itself */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
