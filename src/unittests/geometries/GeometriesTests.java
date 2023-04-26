package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/** Unit tests for geometries.Geometries class
 * @author MENUHA */
class GeometriesTests {
	/** Test method for {@link geometries.Geometries#add(geometries.Intersectable[])}. */
	@Test
	public void testAdd() {
			Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(1, 1, 0), new Point(1, 0, 1));
			Sphere sphere = new Sphere(new Point(1, 0, 0), 1);
			Plane plane = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));			
			Geometries collection = new Geometries(sphere, triangle, plane);
			//
			assertEquals(3, collection.getSceneGeometries().size(), "the length of the list is worng" );
			//
			Triangle t = new Triangle(new Point(1, 8, -6), new Point(1, 0, 0), new Point(1, 0, 2));
			collection.add(t);					
			assertEquals(4, collection.getSceneGeometries().size(), "the length of the list is worng");
	}

	/** Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}. */
	@Test
	public void testFindIntsersections() {
		
			//=====Empty body collection (BVA)=====//
			Geometries collection = new Geometries();
			assertEquals(new Geometries(), collection, "An empty body collection must return null");
			
			//=====No cut shape (BVA)=====//
			Sphere sphere = new Sphere(new Point(1, 0, 0), 1); 
			Triangle triangle = new Triangle(new Point(-4, 0, 0), new Point(0, 0, 5), new Point(0, -5, 0));
			Plane plane = new Plane (new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));
			
			collection.add(sphere, triangle, plane);
		
			assertNull(collection.findIntersections(new Ray(new Point(0, -8, 0), new Vector(-10, -1, 0))), "No cut shape must return 0");
			
		    //=====Some (but not all) shapes are cut (EP)=====//
		    //triangle and plane cut
		    assertEquals(2, collection.findIntersections(new Ray(new Point(-4, -3, 2), new Vector(9, 5, -1))).size(), "wrong number of intersactions");
		    
			//=====Only one shape is cut (BVA)=====//
			//the plane cut
			assertEquals(1, collection.findIntersections(new Ray(new Point(-0.8, -3, 1), new Vector(3.4, 3, 1.57))).size(), "wrong number of intersactions");

			
			//=====All shapes are cut (BVA)=====//
			assertEquals(4, collection.findIntersections(new Ray(new Point(-4, -3, 0), new Vector(6, 3, 0.5))).size(), "wrong number of intersactions");
	}

}

