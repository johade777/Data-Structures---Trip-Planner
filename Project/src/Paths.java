import java.util.LinkedList;

/**
 * 
 * @author Team Bits Please (Josh Gayso, Aaron Mercier, Morgan Cook, David Patterson)
 *
 * Paths is data structure that holds the route traveled, the distance/time spent traveling and the cost
 * (based on whether or not you are traveling by distance or time). 
 */
public class Paths implements Comparable<Paths>{
	public LinkedList<AmusementPark> routeByDistance = new LinkedList<AmusementPark>();
	public LinkedList<AmusementPark> routeByTime = new LinkedList<AmusementPark>();
	public AmusementPark startLocation;
	public AmusementPark endLocation;
	public double distanceTraveled;
	public double distanceCost;
	public int timeSpentTraveling;
	public int timeCost;
	
	/**
	 * Constructs a path with the starting location as the route (no traveling has taken place in this instance)
	 * 
	 * @param startLocation
	 * @param endLocation
	 * @param distanceTraveled
	 * @param distanceCost
	 */
	public Paths(AmusementPark startLocation, AmusementPark endLocation, double distanceTraveled, double distanceCost) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.routeByDistance.add(startLocation);
		this.distanceTraveled = distanceTraveled;
		this.distanceCost = distanceCost;
	}
	
	/**
	 * Constructs a new path (updates) and updates the route traveled (travel by distance uses this to add onto the route)
	 * 
	 * @param startLocation
	 * @param endLocation
	 * @param distanceTraveled
	 * @param distanceCost
	 * @param route
	 */
	public Paths(AmusementPark startLocation, AmusementPark endLocation, double distanceTraveled, double distanceCost, LinkedList<AmusementPark> route) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.distanceTraveled = distanceTraveled;
		this.distanceCost = distanceCost;
		this.routeByDistance = route;
	}
	
	/**
	 * Constructs a path with the starting location as the route (no traveling has taken place in this instance)
	 * @param startLocation
	 * @param endLocation
	 * @param timeSpentTraveling
	 * @param timeCost
	 */
	public Paths(AmusementPark startLocation, AmusementPark endLocation, int timeSpentTraveling, int timeCost) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.routeByTime.add(startLocation);
		this.timeSpentTraveling = timeSpentTraveling;
		this.timeCost = timeCost;
	}
	
	/**
	 * Constructs a new path (updates) and updates the route traveled (travel by time uses this to add onto the route)
	 * 
	 * @param startLocation
	 * @param endLocation
	 * @param timeSpentTraveling
	 * @param timeCost
	 * @param route
	 */
	public Paths(AmusementPark startLocation, AmusementPark endLocation, int timeSpentTraveling, int timeCost, LinkedList<AmusementPark> route) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.timeSpentTraveling = timeSpentTraveling;
		this.timeCost = timeCost;
		this.routeByTime = route;
	}
	

	/**
	 * Compare method that the priority queue will use to find priority in the priority queue data structure,
	 * compares the distance/time cost
	 * 
	 */
	public int compareTo(Paths p) {
		if(this.distanceCost > p.distanceCost) return 1;
		if(this.distanceCost < p.distanceCost) return -1;
		if(this.timeCost > p.timeCost) return 1;
		if(this.timeCost < p.timeCost) return -1;
		return 0;
	}
}
