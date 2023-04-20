/**
 * 
 */
package unittests.primitives;

import static java.lang.System.out;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Vector;

/**
 * @author Yael
 *
 */
class VectorTest {
	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing addition between two vectors.
		assertTrue("ERROR: Point - Point does not work correctly", v1.add(v2).equals(new Vector(-1, -2, -3)));
		
		// =============== Boundary Values Tests ==================
		//TC11: Testing addition of zero vector result (Vector - itself).
		assertThrows(IllegalArgumentException.class, ()-> v1.add(new Vector(-1,-2,-3)), "ERROR: the result is (0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		//The test of scale does not exist in test.Main.
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing scaling a vector by a scalar.
		assertTrue("ERROR: Vector * scalar does not work correctly", v1.scale(2).equals(new Vector(2,4,6)));
		
		// =============== Boundary Values Tests ==================
		//TC11: Scaling Vector by Scalar = 0.
		assertThrows(IllegalArgumentException.class, ()->v1.scale(0),"ERROR: the result is (0,0,0)"); 
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing Dot-Product.
		assertTrue("ERROR: dotProduct() wrong value",!isZero(v1.dotProduct(v2) + 28));
		assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",!isZero(v1.dotProduct(v3)));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing Cross-Product, multiplication between two vectors.
		//Test zero vector.
		assertThrows(IllegalArgumentException.class,()->v1.crossProduct(v2),"ERROR: the result is (0,0,0)");
        Vector vr = v1.crossProduct(v3);
        assertTrue("ERROR: crossProduct() wrong result length",!isZero(vr.length() - v1.length() * v3.length()));
        assertTrue("ERROR: crossProduct() result is not orthogonal to its operands",!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing length Squared.
		assertTrue("ERROR: lengthSquared() wrong value",!isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {	
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing length.
		 assertTrue("ERROR: length() wrong value", !isZero(new Vector(0, 3, 4).length() - 5));
	}
	
	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		// ============ Equivalence Partitions Tests ==============
		Vector v = new Vector(1, 2, 3);
	    Vector u = v.normalize();
		//TC01: Testing vector normalization.
	    assertTrue("ERROR: the normalized vector is not a unit vector", !isZero(u.length() - 1));
	    assertThrows(IllegalArgumentException.class, ()->v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
	    assertTrue("ERROR: the normalized vector is opposite to the original one", v.dotProduct(u) < 0);	
	}
	
	
	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtract() {
		// The definition of subtract in Vector was transferred to Point
		//The test case was done by our guess.
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing subtracting between two Vectors.
		assertTrue("ERROR: Point - Point does not work correctly",v1.subtract(v2).equals(new Vector(3, 6, 9)));
		
		// =============== Boundary Values Tests ==================
        //TC11: Testing subtracting of zero vector result (Vector + itself).
		assertThrows(IllegalArgumentException.class, ()-> v1.subtract(v1), "The result is a zero vector: (0,0,0)");
	}

}
