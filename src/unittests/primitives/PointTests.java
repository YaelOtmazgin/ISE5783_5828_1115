/**
 * 
 */
package unittests.primitives;

import static java.lang.System.out;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for primitives.Point class
 * Testing point
 * @author Yael 
 *
 */

class PointTests {
	//By the test.Main we needed to test: subtract, add. 

	Point p1 = new Point(1, 2, 3); 
	
	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing subtraction between two Points
		assertTrue("ERROR: Point - Point does not work correctly",new Vector(1,1,1).equals(new Point (0,0,0).subtract(p1)));	
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing addition between Point and Vector
		assertTrue("ERROR: Point + Vector does not work correctly",p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0)));
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		fail("Not yet implemented");
	}

}
