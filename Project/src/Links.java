import java.awt.geom.Point2D;

/**
 * Stores information about a Link between two AmusementParks
 */
public class Links {
	public Point2D p1;
	public Point2D p2;
	public int minutes;
	public double distance;
	public AmusementPark travelToLocation;
	public String directions;

	public Links(AmusementPark first, AmusementPark second, int minutesToTravel, String givenDirections) {
		p1 = first.getLocation();
		p2 = second.getLocation();
		this.distance = calcDistance();
		this.minutes = minutesToTravel;
		this.travelToLocation = second;
		this.directions = givenDirections;
	}

	/**
	 * calculates the distance between two AmusementParks
	 * @return double
	 */
	public double calcDistance() {
		return (double) p1.distance(p2);
	}
	
	/**
	 * Returns distance between two AmusementParks
	 * @return double
	 */	
	public double getDistance(){
		return this.distance;
	}
	
	/**
	 * Returns the time it takes two travel between two AmusementParks in minutes
	 * @return int
	 */	
	public int getTime(){
		return this.minutes;
	}
	
	/**
	 * Returns which direction the link is head in, IE the second AmusementPark is always the
	 * Destination of the link
	 * @return AmusementPark
	 */
	public AmusementPark getLinkLocation() {
		return this.travelToLocation;
	}
}
