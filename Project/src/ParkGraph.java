import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 
 * @author Team Bits Please (Josh Gayso, Aaron Mercier, Morgan Cook, David Patterson)
 *
 * Park Graph is a map of the locations. Clicking the Go button in the GUI, a new Park Graph is created with the starting and ending locations.
 * Distance and time spent traveling are tracked. The cost for traveling depending on distance or time are calculated during run time. Two priority
 * queues are made that keep track of the best path according to distance and time. 
 */
public class ParkGraph {
	AmusementPark starting;
	AmusementPark ending;
	double distanceTraveled;
	double distanceCost;
	int timeSpentTraveling;
	int timeCost;
	PriorityQueue<Paths> pathsByDistance = new PriorityQueue<Paths>();
	PriorityQueue<Paths> pathsByTime = new PriorityQueue<Paths>();
	ArrayList<Links> currentLocationLinks = new ArrayList<Links>(); //Neighbors of current
	
	/**
	 * A park graph is created with the starting and ending location, the initial distance and time spent traveling are initialized to 0
	 * and the cost is calculated based on the point locations.
	 * @param start
	 * @param stop
	 */
	public ParkGraph(AmusementPark start, AmusementPark stop) {
		this.starting = start;
		this.ending = stop;
		this.distanceTraveled = 0;
		this.distanceCost = (double) starting.getLocation().distance(ending.getLocation()); //Need to add links and the distances
		this.timeCost = (int) starting.getLocation().distance(ending.getLocation());
		this.timeSpentTraveling = 0;
		this.pathsByDistance.add(new Paths(starting, ending, distanceTraveled, distanceCost));
		this.pathsByTime.add(new Paths(starting, ending, timeSpentTraveling, timeCost));
	}
	
	/**
	 * Travel by distance populates the paths by distance priority queue, only populates based the first priority travel route
	 * @return paths by distance priority queue
	 */
	public PriorityQueue<Paths> travelByDistance() {
		Paths bestPath = pathsByDistance.poll();
		distanceTraveled = bestPath.distanceTraveled;
		AmusementPark currentLocation = bestPath.routeByDistance.getLast();
		currentLocationLinks = currentLocation.getLinks();
		if (currentLocationLinks.size() == 0) return this.pathsByDistance;
		for (int i = 0; i < currentLocationLinks.size(); i++) {
			AmusementPark placeToGo = currentLocationLinks.get(i).getLinkLocation();
			if (!bestPath.routeByDistance.contains(placeToGo)) {
				@SuppressWarnings("unchecked")
				LinkedList<AmusementPark> temp = (LinkedList<AmusementPark>) bestPath.routeByDistance.clone();
				temp.add(placeToGo);
				Paths newPath = new Paths(starting, ending, currentLocationLinks.get(i).getDistance() + distanceTraveled, (double) placeToGo.getLocation().distance(ending.getLocation()) + distanceTraveled, temp);
				pathsByDistance.add(newPath);
			}
		}
		return this.pathsByDistance; 
	}
	
	/**
	 * Travel by time populates the paths by time priority queue, only populates based on the first priority route
	 * @return paths by time priority queue
	 */
	public PriorityQueue<Paths> travelByTime() {
		Paths bestPath = pathsByTime.poll();
		timeSpentTraveling = bestPath.timeSpentTraveling;
		AmusementPark currentLocation = bestPath.routeByTime.getLast();
		currentLocationLinks = currentLocation.getLinks(); 
		if (currentLocationLinks.size() == 0) return this.pathsByTime;
		for (int i = 0; i < currentLocationLinks.size(); i++) {
			AmusementPark placeToGo = currentLocationLinks.get(i).getLinkLocation();
			if (!bestPath.routeByTime.contains(placeToGo)) {
				@SuppressWarnings("unchecked")
				LinkedList<AmusementPark> temp = (LinkedList<AmusementPark>) bestPath.routeByTime.clone();
				temp.add(placeToGo);
				Paths newPath = new Paths(starting, ending, currentLocationLinks.get(i).getTime() + timeSpentTraveling, (int) placeToGo.getLocation().distance(ending.getLocation())+ currentLocationLinks.get(i).getTime() + timeSpentTraveling, temp);
				pathsByTime.add(newPath);
			}
		}
		return this.pathsByTime;
	}
}
