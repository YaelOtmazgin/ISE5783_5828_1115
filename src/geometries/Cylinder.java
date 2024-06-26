package geometries;

import primitives.Point;
import primitives.Ray;
import static primitives.Util.*;

import java.util.List;

import primitives.Vector;

/** Cylinder class represents a finite cylinder in 3D Cartesian coordinate system 
 * @author Menuha and Yael */
public class Cylinder extends Tube {
	// The cylinder's height
	private final double height;
	private final Plane bottomBase;
	private final Plane upperBase;

    private final Point bottomCenter;
    private final Point upperCenter;
    private final Vector dir;
	
	/** Cylinder constructor based on axis ray, radius and height
	 * @param axisRay Ray
	 * @param radius value
	 * @param height value */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
		this.dir = this.axisRay.getDir();
        this.bottomCenter = this.axisRay.getP0();
        this.upperCenter = this.axisRay.getPoint(this.height);
        this.bottomBase = new Plane(this.bottomCenter, this.dir);
        this.upperBase = new Plane(this.upperCenter, this.dir);
	}

	/** @return the cylinder's height */
	public double getHeight() {
		return height;
	}
	
	@Override
	public Vector getNormal(Point point) { 
		//if the Point coalesces with head of the axisRay (p0), then returns axisRay Vector in opposite direction
        //Or, if the Point is on both bottom base and surface, then the normal will be from bottom base
        if (point.equals(this.bottomCenter) 
        		|| isZero(point.subtract(this.bottomCenter).dotProduct(this.dir)))
            return this.dir.scale(-1);

        //if the Point is on the axisRay, then returns axisRay Vector
        //Or, if the Point is on both top base and surface, then the normal will be form top base
        else if ((this.bottomCenter.add(this.dir.scale(this.height)).equals(point)) 
        		|| isZero(point.subtract(this.bottomCenter.add(this.dir.scale(this.height))).dotProduct(this.dir)))
            return this.dir;

        //if the Point is on the surface itself, then calls Tube's function
        return super.getNormal(point);
	}

	@Override
	public String toString() {
		return "Cylinder [" + super.toString() + ", height=" + height + "]";
	}
	
	/** The function finds the intersections of the ray with the infinite cylinder and then checks whether the intersections
     * are within the cylinder's height
     * @param ray the ray we're checking for intersections with the cylinder
     * @return The intersection points of the ray with the cylinder. */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        //Step 1 - finding intersection Points with bases:
        GeoPoint gp1 = baseIntersection(this.bottomBase, ray, this.bottomCenter); //intersection Point with bottom base
        GeoPoint gp2 = baseIntersection(this.upperBase, ray, this.upperCenter); //intersection Point with upper base
        if (gp1 != null && gp2 != null) return twoPoints(ray, gp1, gp2);
        GeoPoint basePoint = gp1 != null ? gp1 : gp2;

        //Step 2 - checking if intersection Points with Tube are on Cylinder itself:
        List<GeoPoint> lst = super.findGeoIntersectionsHelper(ray, maxDistance); //intersection Points with Tube
        if (lst == null) return basePoint == null ? null : List.of(basePoint);

        gp1 = checkIntersection(lst.get(0).point);
        gp2 = lst.size() < 2 ? null : checkIntersection(lst.get(1).point);
        if (basePoint != null)
            return gp1 != null ? twoPoints(ray, basePoint, gp1)
                    : gp2 != null ? twoPoints(ray, basePoint, gp2) : List.of(basePoint);
        if (gp1 == null) return gp2 != null ? List.of(gp2) : null;
        return gp2 != null ? twoPoints(ray, gp1, gp2) : List.of(gp1);
    }
    
    /** If the distance between the intersection point and the center of the sphere is less than the radius of the sphere,
     * then the intersection point is on the sphere
     * @param base The plane that the cylinder is parallel to.
     * @param ray The ray that intersects the sphere
     * @param center the center of the sphere
     * @return The intersection point of the ray and the base of the cylinder. */
    private GeoPoint baseIntersection(Plane base, Ray ray, Point center) {
        List<GeoPoint> lst = base.findGeoIntersections(ray); //intersection Points with Plane
        if (lst == null) return null;
        Point p = lst.get(0).point;
        double radius2 = this.radius * this.radius;
        return alignZero(p.distanceSquared(center) - radius2) < 0 ? new GeoPoint(this, p) : null;
    }

    /** If the point is on the line, return a new GeoPoint object
     * @param p the point to check if it's on the line
     * @return A GeoPoint object. */
    private GeoPoint checkIntersection(Point p) {
        if (p == null) return null;
        return alignZero(this.dir.dotProduct(p.subtract(this.bottomCenter))) > 0
                && alignZero(this.dir.dotProduct(p.subtract(this.upperCenter))) < 0
                ? new GeoPoint(this, p) : null;
    }
}