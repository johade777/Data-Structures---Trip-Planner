import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 * @author Team Bits Please (Josh Gayso, Aaron Mercier, Morgan Cook, David Patterson)
 */
public class Main {

	/**
	 * Constructs Team Bits Please Window and initializes points
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		myFrame.setLayout(new MigLayout());
		myFrame.setTitle(" Bits Please Navigation System ");
		ArrayList<AmusementPark> parks = new ArrayList<AmusementPark>();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AmusementPark HolidayWorld = new AmusementPark("Holiday World", 579, 285, 10);
		parks.add(HolidayWorld);
		AmusementPark DisneyWorld = new AmusementPark("Disney World", 680, 460, 9);
		parks.add(DisneyWorld);
		AmusementPark DisneyLand = new AmusementPark("Disneyland", 105, 328, 8);
		parks.add(DisneyLand);
		AmusementPark CedarPoint = new AmusementPark("Cedar point", 627, 218, 4);
		parks.add(CedarPoint);
		AmusementPark ConeyIsland = new AmusementPark("Coney Island", 756, 208, 2);
		parks.add(ConeyIsland);
		AmusementPark SixFlagsOverTexas = new AmusementPark("Six Flags over Texas", 416, 395, 7);
		parks.add(SixFlagsOverTexas);
		AmusementPark WorldsOfFun = new AmusementPark("Worlds of Fun", 466, 277, 1);
		parks.add(WorldsOfFun);
		AmusementPark GlenwoodCavernsAdventurePark= new AmusementPark("Glenwood Caverns Adventure Park", 281, 253, 3);
		parks.add(GlenwoodCavernsAdventurePark);
		AmusementPark Knoebels = new AmusementPark("Knoebels", 707, 216, 6);
		parks.add(Knoebels);
		AmusementPark KnottsBerryFarm = new AmusementPark("Knotts Berry Farm", 95, 315, 5);
		parks.add(KnottsBerryFarm);
		
		//Add all created Parks to the SearchMap for later searching.
		
		SearchMap search = new SearchMap();
		search.insertPark(HolidayWorld);
		search.insertPark(DisneyWorld);
		search.insertPark(DisneyLand);
		search.insertPark(CedarPoint);
		search.insertPark(ConeyIsland);
		search.insertPark(SixFlagsOverTexas);
		search.insertPark(WorldsOfFun);
		search.insertPark(GlenwoodCavernsAdventurePark);
		search.insertPark(Knoebels);
		search.insertPark(KnottsBerryFarm);
		
		infoPanel info = new infoPanel();
		mapPanel map = new mapPanel(parks, info);
		ControlPanel controls = new ControlPanel(info,search);
		controls.setMap(map);
		map.setControl(controls);
				
		JScrollPane panel = new JScrollPane(info);
		myFrame.add(controls, "cell 0 0, growy, w 250");
		myFrame.add(map , "dock east");
		myFrame.add(panel , "cell 0 1, growy, w 250");
		
		myFrame.pack();
		myFrame.setVisible(true);
		
		//Creates all the Links between AmusementParks. Two links are required for each connection between AmusementParks, a Link going in each direction
		
		Links holDisW = new Links (HolidayWorld, DisneyWorld, 767, "Take US 231 South." + "\n" +  "At exit 17, take ramp right for William H Natcher Parkway South toward Bowling Green.  Keep right onto I-24 E. Take ramp left for I-75 S / GA-401 S. At exit 328, take ramp left for Florida's Turnpike South toward Orlando. Arrive at Seven Seas Dr on the left.");
		Links holCed = new Links(HolidayWorld, CedarPoint, 395, "Take I-64 East toward Louisville. Take ramp left for I-71 North toward Cincinnati. Keep left onto I-75 N. At exit 179, take ramp right for US-6 toward Fremon. Arrive at Cedar Point Dr.");
		Links holWor = new Links(HolidayWorld, WorldsOfFun, 393, "Take US-231 North. Take ramp left for I-64 W. Take ramp right for I-70 W. At exit 54, take ramp right for Parvin Rd toward 48th St. Arrive at Worlds of Fun Ave.");
		Links holSix = new Links(HolidayWorld, SixFlagsOverTexas, 728, "Take US-231 North. Take ramp left for I-64 W. Turn right onto IL-1 / S Court St. At exit 1A, take ramp right for I-55 South toward Memphis. At exit 8, take ramp right for I-40 West toward Little Rock. Keep straight onto I-30 W. Arrive at Six Flags Over Texas.");
		Links conKno = new Links(ConeyIsland, Knoebels, 195, "Take ramp for Belt Pkwy West toward Verrazona Br. At exit 3, take ramp right for Lower Level toward Staten Island. Take ramp left for I-95 North toward Cars Trucks-Buses. At exit 15W, take ramp right for I-280 toward Kearny / Newark. Take ramp left for I-80 West toward Del Water Gap. At exit 241, take ramp right for US-11 South toward Lime Ridge. Arrive at Knoebels Blvd on the left.");
		Links knoCed = new Links(Knoebels, CedarPoint, 351, "Turn right onto PA-54 / S Market St. Take ramp right for I-80 West toward Milton. Take ramp right for I-80 West toward Cleveland. Keep right onto OH-2 W. Turn left to stay on US-6 / Cleveland Rd W. Arrive at Cedar Point Dr.");
		Links knoDisW = new Links(Knoebels, DisneyWorld, 980, "Turn left onto PA-54 / S Market St. Take ramp right for I-81 S. At exit 5B, take ramp right for US-15 South toward Gettysburg. Keep straight onto I-270 S. At exit 57A, take ramp right for I-95 S toward Richmond. At exit 84A, take ramp left for I-295 South toward Richmond Int'l Airport / Rocky Mount NC. Take ramp left for I-95 South toward Rocky Mt NC / Emporia. At exit 352D, take ramp right for I-95 South toward JAX Beaches / Daytona Bch. At exit 260B, take ramp right for I-4 West toward Orlando. Arrive at Seven Seas Dr on the right.");
		Links worGlen = new Links(WorldsOfFun, GlenwoodCavernsAdventurePark, 654, "Turn right onto Worlds of Fun Ave. Take ramp left and follow signs for I-435 North. At exit 52B, take ramp right for I-35 South toward Kansas City. At exit 4B, take ramp right for US-24 West / I-70 West / US-40 West toward Topeka. At exit 116, take ramp right toward Aspen / Glenwood Spgs. Arrive at Devereux Rd.");
		Links sixGlen = new Links(SixFlagsOverTexas, GlenwoodCavernsAdventurePark, 883, "Turn left onto Wet N Wild Way. Take ramp left for I-30 West toward Ft Worth. At exit 21B, take ramp right and follow signs for I-820 North. At exit 60, take ramp right for US-287 North / US-81 North toward Decatur. Keep straight onto US-287 N. Bear right onto TX-335 W Loop. Take ramp left and follow signs for US-87 North / US-287 North. Take ramp right for I-25 North toward Trinidad. Turn right onto CO-69. Turn left onto US-50. Take ramp left for I-70 W. At exit 116, take ramp right toward Aspen / Glenwood Spgs. Arrive at Devereux Rd.");
		Links sixDisL = new Links(SixFlagsOverTexas, DisneyLand, 1185, "Turn left, and then immediately turn right onto Road To Six Flags E. Take ramp left for I-30 West toward Ft Worth. Keep straight onto I-20 W. Keep straight onto I-10 W. Take ramp left for CA-60 toward Riverside. At exit 34B, take ramp right for CA-91 West toward Beach Cities. At exit 3, take ramp right and follow signs for Ball Rd. Arrive.");
		Links glenKnot = new Links(GlenwoodCavernsAdventurePark, KnottsBerryFarm, 728, "Turn right onto US-6 / Linden St. Take ramp right for I-70 toward Grand Jct. Take ramp left for I-15 South toward Las Vegas. Take ramp right for CA-60 West toward Los Angeles. Keep right onto CA-57 S. Take ramp right for CA-91 West toward Los Angeles. Arrive at CA-39 S / Beach Blvd.");
		Links knotDisL = new Links(KnottsBerryFarm, DisneyLand, 10, "Take CA-39 S / Beach Blvd toward Grand Ave. Turn back on CA-39 N / Beach Blvd. Take ramp right and follow signs for CA-91 East. Take ramp right for I-5 toward Santa Ana. Bear left onto S Disneyland Dr. Arrive.");
		
		Links disWhol = new Links(DisneyWorld, HolidayWorld, 767, "Depart Seven Seas Dr toward TTC Westgate Service Rd. Turn right onto Bear Island Rd. Take ramp for SR-429 N Toll. At exit 22, take ramp right for Florida's Tpke North toward Ocala. Take ramp for I-75 N. At exit 156, take ramp left for I-475 North toward Atlanta. Take ramp left and follow signs for I-75 North / GA-401 North. At exit 2, take ramp left for I-24 West toward Nashville / Chattanooga. Keep right onto I-65 N. At exit 20B, take ramp right for William H Natcher Parkway North toward Owensboro. Arrive at IN-162 / IN-245 / E Christmas Blvd.");
		Links cedHol = new Links(CedarPoint, HolidayWorld, 395, "Depart Cedar Point Dr toward Cedar Point Rd. Turn left to stay on US-6 / Fremont Ave. Keep straight onto US-6 / Hayes Ave. Take ramp left for I-75 S. At exit 173, take ramp right for I-71 South toward Louisville. At exit 1A, take ramp right for I-64 West toward St. Louis. At exit 63, take ramp right for IN-162 toward Ferdinand / Santa Claus / Jasper. Arrive at IN-162 / IN-245 / E Christmas Blvd.");
		Links worHol = new Links(WorldsOfFun, HolidayWorld, 393, "Depart Worlds of Fun Ave toward NE 48th St. Take ramp for I-435 S. At exit 63B, take ramp left for I-70 East toward St Louis. Take ramp right for I-64 E. Keep left onto I-64 E. At exit 57A, take ramp right for US-231 South toward Dale. Turn left onto IN-162. Arrive at IN-162 / IN-245 / E Christmas Blvd.");
		Links sixhol = new Links(SixFlagsOverTexas, HolidayWorld, 728, "Turn right onto Road To Six Flags E. Take ramp right and follow signs for I-30 East. At exit 11, take ramp right for I-40 East toward Memphis. At exit 277, take ramp right for I-55 North toward Blytheville / St. Louis. At exit 66A, take ramp right for I-57 North / US-60 East toward Chicago. At exit 54A, take ramp right for IL-13 East toward Marion / Harrisburg. Turn left onto US-45 / N Commercial St. Take ramp left for I-64 E. At exit 57A, take ramp right for US-231 South toward Dale. Take ramp right for IN-162 toward Gentryville / Santa Claus. Arrive at IN-162 / IN-245 / E Christmas Blvd.");
		Links knoCon = new Links(Knoebels, ConeyIsland, 195, "Depart Knoebels Blvd toward Hidden Cottage Rd. Turn right onto PA-487. Take ramp left and follow signs for I-80 East. At exit 43, take ramp right for I-287 South toward Somerville / Morristown / New Jersey . At exit 37, take ramp right for RT-24 East toward Springfield. Take ramp right for I-78 East toward Garden State Parkway / Newark. At exit 13, take ramp right for I-278 East toward Verrazano Br / Goethals Br. Arrive at W 25th St.");
		Links cedKno = new Links(CedarPoint, Knoebels, 351, "Depart Cedar Point Dr toward Cedar Point Rd. Turn left onto US-6 / Cleveland Rd. Keep straight onto OH-2 E. Take ramp right for I-80 East toward Cleveland. At exit 218, take ramp right for I-80 East toward Youngstown. Turn left to stay on PA-54 / Elysburg Rd. Bear right onto Knoebels Blvd. Arrive at Knoebels Blvd on the left.");
		Links disWKno = new Links(DisneyWorld, Knoebels, 980, "Depart Seven Seas Dr toward TTC Westgate Service Rd. Take ramp right for Epcot Center Dr toward Downtown Disney. Take ramp right for I-4 East toward Orlando. Take ramp left for I-95 North toward Daytona Bch / Jacksonville. Take ramp right for I-95 North toward Savannah / Int'l Airport. At exit 43A, take ramp left for I-95 North toward Washington. At exit 170B, take ramp right for I-495 North toward Tysons Corner. Keep straight onto US-15 N / US-40 W. At exit 51B, take ramp right for I-81 North toward Hazleton / Allentown. At exit 116, take ramp right for PA-901 toward Minersville. Turn left onto PA-54 / PA-901. Arrive at Knoebels Blvd on the left.");
		Links glenWor = new Links(GlenwoodCavernsAdventurePark, WorldsOfFun, 654, "Turn right onto US-6 / Linden St. Take ramp left for I-70 E / US-24 E toward Denver. Keep left to stay on I-70 E / US-40 E / KS-4 E. Bear left onto I-29 N / I-35 N / US-71 N. Turn right onto MO-210 E / Armour Rd. Keep straight onto Worlds of Fun Ave. Arrive at Worlds of Fun Ave.");
		Links glenSix = new Links(GlenwoodCavernsAdventurePark, SixFlagsOverTexas, 883, "Turn right onto US-6 / Linden St. Take ramp left for I-70 E / US-24 E toward Denver. Bear right onto US-24. Turn left onto US-50. 	Turn right to stay on CO-69 / S 6th St. Take ramp right for I-25 South toward Trinidad. Turn left onto US-64 / US-87 / Clayton Rd. Keep straight onto US-87 / S 1st St. Take ramp for US-87 S / US-287 S. Keep straight onto US-287 S. At exit 57A, take ramp left and follow signs for I-820 East. At exit 22B, take ramp right for TX-121 N / TX-183 E toward Dallas / D/FW Airport / Airport. Take ramp right for TX-360 South toward FAA Rd / Arlington / Grand Prairie. Take ramp right for SH 360 / N Watson Rd toward Six Flags Dr / Ave H/Lamar Blvd. Arrive.");
		Links disLSix = new Links(DisneyLand, SixFlagsOverTexas, 1185, "Depart toward S Disneyland Dr. Take ramp right for I-5 South toward Santa Ana. Take ramp left for CA-55 North toward Riverside / Anaheim. Keep straight onto CA-91 E. Keep left onto CA-60 E. Take ramp left for I-10 E. Keep left to stay on I-10 E. Take ramp left for I-20 East toward Ft Worth / Dallas. Keep left onto I-30 E. At exit 28, take ramp right for E Copeland Rd toward Legends Way / Nolan Ryan Expy / Baird Farm Rd. Turn right onto Six Flags Dr. Arrive.");
		Links knotGlen = new Links(KnottsBerryFarm, GlenwoodCavernsAdventurePark, 728, "Depart CA-39 S / Beach Blvd toward Grand Ave. Take ramp right for CA-57 North toward Pomona. Keep left onto CA-60 E. Take ramp right for I-15 North toward Barstow. At exit 132, take ramp right for I-70 East toward Denver / Richfield. At exit 114, take ramp right toward West Glenwood. Turn right onto Devereux Rd. Arrive at Devereux Rd.");
		Links disLKnot = new Links(DisneyLand, KnottsBerryFarm, 10, "Depart toward S Disneyland Dr. Take ramp right for I-5 North toward Los Angeles. At exit 113C, take ramp right and follow signs for CA-91 West. At exit 23B, take ramp right for CA-39 toward Buena Park. Arrive at CA-39 S / Beach Blvd.");
		
		//Adds the Links to the AmusementParks
		
		HolidayWorld.addLink(holDisW);
		HolidayWorld.addLink(holCed);
		HolidayWorld.addLink(holWor);
		HolidayWorld.addLink(holSix);
		ConeyIsland.addLink(conKno);
		Knoebels.addLink(knoCon);
		Knoebels.addLink(knoCed);
		Knoebels.addLink(knoDisW);
		CedarPoint.addLink(cedHol);
		CedarPoint.addLink(cedKno);
		DisneyWorld.addLink(disWhol);
		DisneyWorld.addLink(disWKno);
		WorldsOfFun.addLink(worHol);
		WorldsOfFun.addLink(worGlen);
		SixFlagsOverTexas.addLink(sixhol);
		SixFlagsOverTexas.addLink(sixGlen);
		SixFlagsOverTexas.addLink(sixDisL);
		GlenwoodCavernsAdventurePark.addLink(glenWor);
		GlenwoodCavernsAdventurePark.addLink(glenSix);
		GlenwoodCavernsAdventurePark.addLink(glenKnot);
		KnottsBerryFarm.addLink(knotGlen);
		KnottsBerryFarm.addLink(knotDisL);
		DisneyLand.addLink(disLKnot);
		DisneyLand.addLink(disLSix);
		
	}
}