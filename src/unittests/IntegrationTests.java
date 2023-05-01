package unittests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import geometries.*;
import primitives.*;
import org.junit.Test;

import renderer.Camera;

/** tests for integration between rays from camera and geometry shape
 * @author Menuha and Yael */
public class IntegrationTests {
	/** test integration ray from camera with sphere */
	@Test
	public void testIntegrationWithSphere() {
		Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
		Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setVPDistance(1).setVPSize(3, 3);

		// TC01: Two intersection points
		assertEquals("First test case: should be 2 intersection points", 2, calcSumIntersection(camera, sphere, 3, 3));

		// TC02: 18 intersection points
		sphere = new Sphere(new Point(0, 0, -2.5), 2.5);
		camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setVPDistance(1).setVPSize(3, 3);
		assertEquals("Second test case: should be 18 intersection points", 18,
				calcSumIntersection(camera, sphere, 3, 3));

		// TC03: 10 intersection points
		sphere = new Sphere(new Point(0, 0, -2), 2);
		assertEquals("Third test case: should be 10 intersection points", 10,
				calcSumIntersection(camera, sphere, 3, 3));

		// TC04: 9 intersection points
		sphere = new Sphere(new Point(0, 0, -1), 4);
		assertEquals("Fourth test case: should be 9 intersection points", 9, calcSumIntersection(camera, sphere, 3, 3));

		// TC05: 0 intersection points
		sphere = new Sphere(new Point(0, 0, 1), 0.5);
		assertEquals("Fifth test case: should be 0 intersection points", 0, calcSumIntersection(camera, sphere, 3, 3));
	}

	/** test integration ray from camera with plane */
	@Test
	public void testIntegrationWithPlane() {
		Plane plane = new Plane(new Point(0, 0, -3), new Vector(0, 0, 1));
		Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setVPDistance(1).setVPSize(3, 3);

		// TC01: 9 intersection points
		assertEquals("First test case: should be 9 intersection points", 9, calcSumIntersection(camera, plane, 3, 3));

		// TC02: 9 intersection points
		plane = new Plane(new Point(0, 0, -2.5), new Vector(0, -0.9, 1));
		assertEquals("Second test case: should be 9 intersection points", 9, calcSumIntersection(camera, plane, 3, 3));

		// TC03: 6 intersection points
		plane = new Plane(new Point(0, 0, -3), new Vector(0, -1, 1));
		assertEquals("Third test case: should be 6 intersection points", 6, calcSumIntersection(camera, plane, 3, 3));

	}

	/** test integration ray from camera with triangle */
	@Test
	public void testIntegrationWithTriangle() {
		Triangle tri = new Triangle(new Point(1, -1, -2), new Point(0, 1, -2), new Point(-1, -1, -2));
		Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setVPDistance(1).setVPSize(3, 3);
		
		// TC01: 1 intersection point
		assertEquals("First test case: should be 1 intersection point", 1, calcSumIntersection(camera, tri, 3, 3));

		// TC02: 2 intersection points
		tri = new Triangle(new Point(1, -1, -2), new Point(0, 20, -2), new Point(-1, -1, -2));
		assertEquals("Second test case: should be 2 intersection points", 2, calcSumIntersection(camera, tri, 3, 3));
	}

	/** The function doing
	 * <li>Generate rays through all pixels of View Plane
	 * <li>Summarize amount of intersections of all the rays
	 * @param camera - the current camera
	 * @param shape - geometry shape that implements {@link geometries.Intersectable}
	 * @param nX - sum of columns in view plane
	 * @param nY - sum of lines in view plane
	 * @return sum of intersections between "shape" and every ray from "camera" */
	private int calcSumIntersection(Camera camera, Intersectable shape, int nX, int nY) {
		var rays = new LinkedList<Ray>();
		for (int i = 0; i < nX; i++)
			for (int j = 0; j < nY; j++) {
				rays.add(camera.constructRay(nX, nY, j, i));
			}
		var sumPoints = new LinkedList<Point>();
		for (var ray : rays) {
			var result = shape.findIntersections(ray);
			if (result != null)
				sumPoints.addAll(result);
		}
		return sumPoints.size();
	}
}