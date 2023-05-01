package unittests.primitives;
import static java.lang.System.out;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import static primitives.Util.isZero;

/** Unit tests for primitives.Point class
 * @author Menuha and Yael */
class PointTests {
	//By the test.Main we needed to test: subtract, add. 
	Point p1 = new Point(1, 2, 3); 	
	/** Test method for {@link primitives.Point#subtract(primitives.Point)}. */
	@Test
	void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing subtraction between two Points
		assertTrue("ERROR: Point - Point does not work correctly", new Vector(-1, -2, -3).equals(new Point(0, 0, 0).subtract(p1)));	
	}

	/** Test method for {@link primitives.Point#add(primitives.Vector)}. */
	@Test
	void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing addition between Point and Vector
		assertTrue("ERROR: Point + Vector does not work correctly", (p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))));
	}

	/** Test method for {@link primitives.Point#distanceSquared(primitives.Point)}. */
	@Test
	void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		//the squared distance between two points
		Point p1 = new Point(1, 2, 3);
		Point p2 = new Point(2, 3, 4);
		assertTrue("ERROR: distanceSquared() wrong value", isZero(p1.distanceSquared(p2) - 3));
	}

	/** Test method for {@link primitives.Point#distance(primitives.Point)}. */
	@Test
	void testDistance() {
		// ============ Equivalence Partitions Tests ==============
		//the distance between two points
		Point p1 = new Point(0, 6, 8);
		Point p2 = new Point(0, 3, 4);
		assertTrue("ERROR: distance() wrong value", isZero(p1.distance(p2) - 5));
	}
}






