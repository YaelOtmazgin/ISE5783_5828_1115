package primitives;

/** The class represents the Phong Reflectance Model.
 * @author Menuha and Yael */
public class Material {
	/** refraction */
	public Double3 kD = Double3.ZERO;
	/** specular */
	public Double3 kS = Double3.ZERO;
	/** shine */
	public int nShininess = 0;
	
	/** ------------- setter -----------------
	 * @param kD - the kD to set
	 * @return the material itself */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kD the kD to set (type double)
	 * @return the material itself */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kS the kS to set
	 * @return the material itself */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kS the kS to set (type double)
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
