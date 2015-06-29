import java.util.ArrayList;

/**
 * Allows a user to search through the AmusementParks by the rating of fun
 */
public class SearchMap {
	ArrayList<ArrayList<AmusementPark>> map = new ArrayList<ArrayList<AmusementPark>>();

	/**
	 * As there is only 10 ratings 1 - 10 the outer ArrayList is all initialized to be null
	 * The first index or the 0 index is not used
	 */	
	public SearchMap() {
		for (int i = 0; i < 11; i++) {
			map.add(i, null);
		}
	}

	/** 
	 * Inserts an AmusementPark by its levelOfFun into the ArrayList. If the outer ArrayList is null it 
	 * creates a new ArrayList. If not it adds the AmusementPark to the end of the current. 
	 * @param park
	 */	
	public void insertPark(AmusementPark park) {
		Integer temp = park.getLevelOfFun();
		if (map.get(temp) == null) {
			ArrayList<AmusementPark> adding = new ArrayList<AmusementPark>();
			adding.add(park);
			map.set(temp, adding);
		} else {
			ArrayList<AmusementPark> list = map.get(temp);
			list.add(park);
		}
	}

	/**
	 * Takes in the levelOfFun the user inputs and returns that level and
	 * every AmusementPark with a lower level of fun than that.
	 * @param level
	 * @return ArrayList<AmusementPark>
	 */
	public ArrayList<AmusementPark> getList(Integer level) {
		ArrayList<AmusementPark> temp = new ArrayList<AmusementPark>();
		for(int i = level; i >= 1; i--){
			temp.addAll(map.get(i));
		}
		return temp;
	}
}