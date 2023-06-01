package geometries;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import primitives.Point;
import primitives.Ray;

/** A class for a collection of geometric shapes
 * @author Menuha and Yael */
public  class Geometries extends Intersectable {	
	private List<Intersectable> sceneGeometries;
	
	/** Default constructor */
	public Geometries() {
		sceneGeometries = new LinkedList<Intersectable>();
	}
	
	/** parameters constructor
	 * @param geometries types of geometries in the scene */
	public Geometries(Intersectable... geometries){
		sceneGeometries =  List.of(geometries);
	}

/*	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> points = null;
		if (sceneGeometries != null) {			
			for (var shape: sceneGeometries) {
				var result = shape.findIntersections(ray);
				if (result != null)
					if (points == null)
						points = new LinkedList<Point>(result);
					else
						points.addAll(result);
			}
		}
		return points;
	}
*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometries other = (Geometries) obj;
		return Objects.equals(sceneGeometries, other.sceneGeometries);
	}
	
	/** a function that adds a new geometry to the scene
	 * @param geometries list of geometries to add*/
	public void add(Intersectable... geometries){
		if (geometries != null)
			sceneGeometries.addAll(List.of(geometries));	
	}
	
	/** a function that returns the geometries
	 * @return scene geometries */
	public List<Intersectable> getSceneGeometries() {
		return sceneGeometries;
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> points = null;
		if (sceneGeometries != null) {			
			for (var shape: sceneGeometries) {
				var result = shape.findGeoIntersectionsHelper(ray);
				if (result != null)
					if (points == null)
						points = new LinkedList<GeoPoint>(result);
					else
						points.addAll(result);
			}
		}
		return points;
	}

	
}
