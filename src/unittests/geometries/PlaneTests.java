package unittests.geometries;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

/** Unit tests for geometries.Plane class
 * @author Menuha and Yael */
class PlaneTests {
	/** Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}. */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: three points on the same line
		try {
		new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 4));
		fail("Failed constructing a correct plane");
		} catch (IllegalArgumentException e) {
		}
		
		// TC02: there are points are the same
		try {
			new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 2));
			fail("Failed constructing a correct plane");
			} catch (IllegalArgumentException e) {
			}
	}

	/** Test method for {@link geometries.Plane#getNormal()}. */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
	    // TC01:
		Plane plane = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
		try {
			assertEquals(new Vector(1, 1, 1).normalize(), plane.getNormal(),"Normal calculation is incorrect");
		}
		catch(IllegalArgumentException e) {				
		};
	}
	
	/** Test method for {@link geometries.Plane#getNormal(primitives.Point)}. */
	@Test
	public void testfindIntersections() {
		    Plane myPlane = new Plane(new Point(0, 5, 0), new Point(-5, 0, 0), new Point(0, 0, 3));
		    // =============== Boundary Values Tests ==================	
		    
		    // Ray is parallel to the plane
			// TC01: the ray included in the plane
			Ray myRay = new Ray(new Point(0,  5, 0), new Vector(-5, 0, 0)); // the plane include this ray
			assertNull(myPlane.findIntersections(myRay), "A included ray has zero intersection points");
			// TC02: the ray not included in the plane
			myRay = new Ray(new Point(0,-5, 0), new Vector(5, 0, 0)); // the plane un included this ray
			assertNull(myPlane.findIntersections(myRay), "An unincluded ray has zero intersection points" );
			
			// Ray is orthogonal to the plane
			// TC03: before the plane
			myRay = new Ray(new Point(2, 4, 0), new Vector(-3, 3, 5)); // the ray is orthogonal to the plane
			assertEquals(1, myPlane.findIntersections(myRay).size(), "Ray is orthogonal to the plane and starts before the plane");
			// TC04: at the plane
			myRay = new Ray(new Point(-5, 0, 0), new Vector(-3, 3, 5)); // the ray is orthogonal to the plane
			assertNull(myPlane.findIntersections(myRay), "Ray is orthogonal to the plane and starts at the plane");
			// TC05: after the plane
			myRay = new Ray(new Point(-7, 2, 4), new Vector(-3, 3, 5)); // the ray is orthogonal to the plane
			assertNull(myPlane.findIntersections(myRay), "Ray is orthogonal to the plane and starts after the plane");
			
			// Ray is neither orthogonal nor parallel to and begins at the plane
			// TC06:
			myRay = new Ray(new Point(-1, -1, 0), new Vector(1, 0, 0)); // the ray isn't orthogonal or parallel to the plane
			assertNull( myPlane.findIntersections(myRay), "Ray is neither orthogonal nor parallel to and begins at reference point in the plane");
			
			// Ray is neither orthogonal nor parallel to the plane and begins in
			// the same point which appears as reference point in the plane
			// TC07:
			myRay = new Ray(new Point(0, 0, 3), new Vector(-5, 4, -3)); // the ray isn't orthogonal or parallel to the plane but not intersects the plane
			assertNull(myPlane.findIntersections(myRay), "Ray is neither orthogonal nor parallel to and begins at the plane");
			
			// ============ Equivalence Partitions Tests ================
			
			// TC08: The Ray must be neither orthogonal nor parallel to the plane
			// Ray does not intersect the plane
			myRay = new Ray(new Point(1, 2, 0), new Vector(-3, -7, 0));
			assertNull(myPlane.findIntersections(myRay), "Ray is neither orthogonal nor parallel but doesnt intersects the plane");
			
			// TC09:
			// Ray intersects the plane
			myRay = new Ray(new Point(4, 3, 0), new Vector(-5.75, 3.57, 0)); // the ray isn't orthogonal or parallel to the plane and intersects the plane
			assertEquals(1, myPlane.findIntersections(myRay).size(), "Ray is neither orthogonal nor parallel and intersects the plane ");
	}
}
