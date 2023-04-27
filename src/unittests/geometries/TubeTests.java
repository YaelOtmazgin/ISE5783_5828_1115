package unittests.geometries;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

/** Unit tests for geometries.Tube class 
 * @author MENUHA and Yael */
class TubeTests {
	/** Test method for {@link geometries.Tube#getNormal(primitives.Point)}. */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:
		Tube tube = new Tube(new Ray(new Point(1, 2, 3), new Vector(0, 3, 0)), 5);
		Point p = new Point(3, 4, 6);
		Tube myTube = new Tube(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 5);
		Vector normal = new Vector(1, 0, 0);
		try {
			//check get normal of tube
			assertEquals(new Vector(2, 0, 3).normalize(), tube.getNormal(p), "tube doesn't calculate the normal right");
			// =============== Boundary Values Tests ==================
			// Check if the point is on the circumference of the circle, 
			//check if the distance between the point and the center point is the same as the radius. 
			//If so the point must be on the circumference of the circle.
			//TC11:
			double lengthNormal = Math.sqrt(21);
			normal = new Vector(4d / lengthNormal, 0, Math.sqrt(5) / lengthNormal);
			assertEquals(normal , myTube.getNormal(new Point(5, 4, Math.sqrt(5))), "Bad normal to tube");
		}
	catch(IllegalArgumentException e) {		
	} 
	}

}
