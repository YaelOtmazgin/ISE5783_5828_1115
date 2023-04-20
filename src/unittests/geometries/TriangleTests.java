/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

/** Unit tests for geometries.Triangle class
 * @author Yael */
class TriangleTests {

	/** Test method for {@link geometries.Triangle#getNormal(primitives.Point)}. */
	@Test
	void testGetNormal() {
		//Inherits from the polygon
		 // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
		try
		{
			Triangle myTriangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
			double sqrt3 = Math.sqrt(1d / 3);
			Vector normal=new Vector(sqrt3, sqrt3, sqrt3);
		    assertEquals(normal , myTriangle.getNormal(new Point(0, 0, 1)),"Bad normal to trinagle" );
	
		}
		catch(Exception ex) 
		{
			fail("for vectors that not zero vector does not need throw an exception");
		}
	}

}
