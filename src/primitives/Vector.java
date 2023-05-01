
package primitives;

/** Vector class represents a fundamental object in geometry with direction and size, 
 * defined by the end point (when the starting point - the beginning of the axes).
 * @author Menuha and Yael */
public class Vector extends Point {
	/** Constructor to initialize Vector based object with its three number values
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value 
	 * @throws IllegalArgumentException if the user is trying to define Zero Vector*/
	public Vector(double d1, double d2, double d3) {
		super(d1, d2, d3);
	    if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("You can't define Zero Vector by 3 values");		
	}
	   
	/** Constructor to initialize Vector based object with Double3 object
     * @param xyz contains 3 numbers 
     * @throws IllegalArgumentException if the user is trying to define Zero Vector*/
	Vector(Double3 xyz) {
		super(xyz);
		if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("You can't define Zero Vector by Double3");		
	}
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	return (obj instanceof Vector other) 
		    && super.equals(other);
	}
	
	@Override
	public String toString() { return "->" + super.toString(); }
	
	/** Vector addition
	 * @param v second vector
	 * @return a new vector */
	public Vector add(Vector v) {
		return new Vector(this.xyz.add(v.xyz));
	}
	
	/** Vector Multiplier - Scalar
	 * @param scalar scalar value
	 * @return New Vector */
	public Vector scale(double scalar) {
		return new Vector(this.xyz.scale(scalar));
	}
	
	/** Scalar product between vectors
	 * @param v second vector
	 * @return scalar value */
	public double dotProduct(Vector v) {
		return this.xyz.d1 * v.xyz.d1 +
			   this.xyz.d2 * v.xyz.d2 +
			   this.xyz.d3 * v.xyz.d3;
	}
	
	/** Vector multiplication
	 * @param v second vector
	 * @return new vector that stands for the two existing vectors */
	public Vector crossProduct(Vector v) {
		return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
						  this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
						  this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
	}
	
	/** Calculate the length of the vector squared
	 * @return the length of the vector squared */
	public double lengthSquared() {
		/** v.dotProduct(v) = v^2 = v.lengthSquard() */
		return this.dotProduct(this);
	}
	
	/** Calculate the length of the vector
	 * @return the length of the vector */
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	/** Normalization operation.
	 * @return a normalized new vector in the same direction as the original vector */
	public Vector normalize() {
		return new Vector(this.xyz.scale(1/this.length()));
	}	
}
