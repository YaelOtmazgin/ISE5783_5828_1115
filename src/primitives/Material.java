package primitives;

/** The class represents the Phong Reflectance Model.
 * @author Menuha and Yael */
public class Material {
	/** Diffusive coefficient */
	public Double3 kD = Double3.ZERO;
	/** Transparency coefficient */
	public Double3 kT = Double3.ZERO;
	/** Specular coefficient - relating to or having the properties of a mirror.*/
	public Double3 kS = Double3.ZERO;
	/** Reflection coefficient - relating to or having the properties of a mirror.*/
	public Double3 kR = Double3.ZERO;	
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
	 * @param kT - the Transparency to set
	 * @return the material itself */
	public Material setKt(Double3 kT) {
		this.kT = kT;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kT the Transparency to set (type double)
	 * @return the material itself */
	public Material setKt(double kT) {
		this.kT = new Double3(kT);
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
	 * @param kR - the Reflection to set
	 * @return the material itself */
	public Material setKr(Double3 kR) {
		this.kR = kR;
		return this;
	}
	
	/** ------------- setter -----------------
	 * @param kR the Reflection to set (type double)
	 * @return the material itself */
	public Material setKr(double kR) {
		this.kR = new Double3(kR);
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
